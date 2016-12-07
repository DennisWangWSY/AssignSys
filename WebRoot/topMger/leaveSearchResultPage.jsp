<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.jfinal.plugin.activerecord.Record" %>
<!DOCTYPE html>
        <html lang="en">
        <head>
    <style>
    li{
    float: left;list-style:none;
    }
    #nav{ margin:0 auto; width:400px; height:100px;}
    </style>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>公司信息——假期筛选结果</title>

        <link href="/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="/css/signin.css" rel="stylesheet">
        <script src="/js/ie-emulation-modes-warning.js"></script>
        </head>

        <body>
        <div class="table-responsive">
        <%
    		List<Record> list= (List<Record>) request.getAttribute("list");
    		if(!list.isEmpty()){%>
        <table class="table table-hover ">
        <caption>筛选结果</caption>

    <thead>
        <tr>
            <th>ID</th>
            <th>工号</th>
            <th>部门</th>
            <th>类型</th>
            <th>开始</th>
            <th>结束</th>
            <th>请假事由</th>
            <th>状态</th>
        </tr>
        </thead>
        <tbody><%
            	Iterator<Record> iter=list.iterator();
        		while(iter.hasNext()){%>
            <tr class=" <%
            		Record re = iter.next();
            		String status = re.getStr("status");
            		if(status.equals("unproved")){
                        out.print("active");}
            		else if(status.equals("proving")){
            		out.print("warning");}
            		else if(status.equals("denied")){
            		out.print("danger");}
            		else if(status.equals("proved")){
            		out.print("success");}%>">

                <td><% out.println(re.get("id")); %></td>
                <td><% out.println(re.get("uid")); %></td>
                <td><%
                String dept = re.getStr("dept");
                if(dept.equals("sales"))
                    out.println("销售");
                else if(dept.equals("purchase"))
                    out.println("采购");
                %></td>
                <td><%
                String type = re.getStr("type");
                if(type.equals("year"))
                	out.println("年假");
                else if(type.equals("sick"))
                	out.println("病假");
                else if(type.equals("marriage"))
                	out.println("婚假");
                else if(type.equals("birth"))
                	out.println("产假");
                else if(type.equals("dead"))
                	out.println("丧假");
                else if(type.equals("other"))
                	out.println("事假");
                %></td>
                <td><%out.print(re.get("starttime").toString().substring(0, 10));%></td>
                <td><%out.print(re.get("endtime").toString().substring(0, 10));%></td>
                <td><% out.println(re.getStr("cause")); %></td>
                <td><%
                if(status.equals("unproved"))
                	out.println("待审核");
                else if(status.equals("proving"))
                	out.println("正在审核中");
                else if(status.equals("denied"))
                	out.println("驳回");
                else if(status.equals("proved"))
                	out.println("准许");
                %></td>
            </tr>
            <%
        		}%>
        </tbody>
        </table>
        <%
    		}else{
            %>
    <h3>没有符合条件的请假申请</h3>
        <%
            }
        %>
        </div>
    <div>
    <a href="/topMger/leaveRules">
    <input class="btn" type="button" value="返 回"/>
    </a>
    </div>
        <script src="/js/ie10-viewport-bug-workaround.js"></script>
        </body>
        </html>