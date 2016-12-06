<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.jfinal.plugin.activerecord.Page" %>
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

        <title>待审查——加班</title>

        <link href="/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="/css/signin.css" rel="stylesheet">
        <script src="/js/ie-emulation-modes-warning.js"></script>
        </head>

        <body>
        <div class="table-responsive">
        <%
	 		Page<Record> cpage = (Page<Record>)request.getAttribute("list");
    		List<Record> list= cpage.getList();
    		if(!list.isEmpty()){%>
        <table class="table table-hover ">
        <caption>待审核加班请求</caption>
        <thead>
        <tr>
            <th>id</th>
            <th>工号</th>
            <th>姓名</th>
            <th>加班事由</th>
            <th>处理</th>
        </tr>
        </thead>
        <tbody>
                    <%
        		Iterator<Record> iter=list.iterator();
        		while(iter.hasNext()){
            		Record re = iter.next();
                %>

            <tr class="active">
                <td><% out.println(re.get("id"));%></td>
                <td><% out.println(re.get("uid")); %></td>
                <td><% out.println(re.getStr("name")); %></td>
                <td><% out.println(re.getStr("cause")); %></td>
                <td>&nbsp;&nbsp;<a href="/topMger/overtimeDetail/<%out.print(re.get("id"));%>">审查</a></td>
            </tr>
            <%
        		}
    		%>
        </tbody>
        </table>
    <%
            }else{
            %>
            <h3>无未审核加班申请</h3>
        <%
            }
        %>
        </div>
    <div id="nav">
    <ul>
        <%
			int pagenumber = cpage.getTotalPage();
			int currentpage = cpage.getPageNumber();
			for(int i=1; i<=pagenumber;i++){
				if(i==currentpage){
			%>	<li><b><%=i %>	&nbsp;</b></li>	<%
				}
				else{%>
    <li><a href="/topMger/checkOvertime/<%=i %>"><b><%=i %>	&nbsp;</b></a></li>
        <%} }%>
    <li>
    <a href="/toMain">
    <input class="btn" type="button" value="返 回"/>
    </a>
    </li>
    </ul>
    </div>
        <script src="/js/ie10-viewport-bug-workaround.js"></script>
        </body>
        </html>