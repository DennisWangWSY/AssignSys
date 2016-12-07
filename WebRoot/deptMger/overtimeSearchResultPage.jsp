<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="model.Overtimesheet" %>
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

        <title>部门信息——加班筛选结果</title>

        <link href="/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="/css/signin.css" rel="stylesheet">
        <script src="/js/ie-emulation-modes-warning.js"></script>
        </head>

        <body>
        <div class="table-responsive">
        <%
    		List<Overtimesheet> list= (List<Overtimesheet>) request.getAttribute("list");
    		if(!list.isEmpty()){%>
        <table class="table table-hover ">
        <caption>筛选结果</caption>

    <thead>
        <tr>
            <th>ID</th>
            <th>工号</th>
            <th>开始</th>
            <th>结束</th>
            <th>加班原因</th>
            <th>状态</th>
        </tr>
        </thead>
        <tbody><%
            	Iterator<Overtimesheet> iter=list.iterator();
        		while(iter.hasNext()){%>
            <tr class=" <%
            		Overtimesheet re = iter.next();
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
                <td><%out.print(re.getStarttime().toString().substring(0, 10));%></td>
                <td><%out.print(re.getEndtime().toString().substring(0, 10));%></td>
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
    <a href="/deptMger/leaveRules">
    <input class="btn" type="button" value="返 回"/>
    </a>
    </div>
        <script src="/js/ie10-viewport-bug-workaround.js"></script>
        </body>
        </html>