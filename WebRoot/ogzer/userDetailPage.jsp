<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="model.Users" %>
    <!DOCTYPE html >
        <html>
        <head>
        <title id="title">修改员工基本信息</title>
        <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="/css/bootstrap2.3.1.min.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="/dist/js/jquery.min.js"></script>
        <script src="/dist/js/bootstrap.min.js"></script>
        <style>
        html, body {
        height: 100%;
        width: 100%;
        margin: 0;
        padding: 0;
        }

        .form-horizontal .control-group {
        margin-bottom: 16px;
        }

        .wrap {
        height: 100%;
        display: -webkit-box;
        -webkit-box-align: center;
        -webkit-box-pack: center;
        overflow: auto;
        }

        .main {
        width: 600px;
        }

        .info {
        margin-bottom: 20px;
        font-size: 21px;
        line-height: 40px;
        color: #333;
        border: 0;
        border-bottom: 1px solid #e5e5e5;
        }
        </style>
        </head>
        <body>
        <%
        Users user = (Users)request.getAttribute("user");
        %>
        <div class="wrap">
        <form class="main form-horizontal" method="post" action="/ogzer/updateUser">

        <fieldset>
        <legend>修改员工信息</legend>
                <div class="control-group">
        <label class="control-label">
        工号</label>
        <div class="controls">
        <input name="uid" type="number" value="<%out.print(user.getUid()); %>" class="input-xlarge" readonly>
        </div>
        </div>
        <div class="control-group">
        <label class="control-label">
        姓名</label>
        <div class="controls">
        <input type="text" value="<%out.println(user.getName()); %>" class="input-xlarge" readonly>
        </div>
        </div>
        <div class="control-group">
        <label class="control-label">
        新密码</label>
        <div class="controls">
        <input id="password" name="password" type="password" placeholder="非必填项" class="input-xlarge"
         onchange="checkPasswords()">
        </div>
        </div>
        <div class="control-group">
        <label class="control-label">
        确认密码</label>
        <div class="controls">
        <input id="password1" name="password1" type="password" class="input-xlarge"
         onchange="checkPasswords()">
        </div>
        </div>
        <div class="control-group">
        <label class="control-label">
        年龄</label>
        <div class="controls">
        <input name="age" id="age" type="number" value="<%out.print(user.getAge()); %>" class="input-xlarge" required>
        </div>
        </div>
        <div class="control-group">
        <label class="control-label">
        工龄</label>
        <div class="controls">
        <input name="workedyears" id="workedyears" type="number" value="<%out.print(user.getWorkedyears()); %>" class="input-xlarge" required>
        </div>
        </div>
        <div class="control-group">
        <label class="control-label">
        性别</label>
        <div class="controls">
        <input type="text" value="<%out.println(user.getGender()); %>" class="input-xlarge" readonly>
        </div>
        </div>
        
        <div class="control-group">
        <label class="control-label">
        职位
        </label>
		<%
    	String type = user.getType();
    	String dept = user.getDept();%>
        <div class="controls">
        <label class="radio">
        <input type="radio" value="staff" name="type" <%
        if(type.equals("staff"))
        		%> checked="<%out.print("checked");%>" onclick="defaultDept()"/>
        员工
        </label>
        <label class="radio">
        <input type="radio" value="deptMger" name="type" <%
        if(type.equals("deptMger"))
        		%> checked="<%out.print("checked");%>" onclick="defaultDept()"/>
        部门经理
        </label>
        <label class="radio">
        <input type="radio" value="topMger" name="type" <%
        if(type.equals("topMger"))
        		%> checked="<%out.print("checked");%>" onclick="topMger()"/>
        总经理
        </label>
        <label class="radio">
        <input type="radio" value="ogzer" name="type" <%
        if(type.equals("ogzer"))
        		%> checked="<%out.print("checked");%>" onclick="ogzer()"/>
        管理员
        </label>
        </div>
        </div>
        <div id="dept" class="control-group"<%
        	if(type.equals("topMger")||type.equals("ogzer"))
        		%> style="display: none;">
        <label class="control-label">
        部门
        </label>

        <div class="controls">
        <label class="radio">
        <input type="radio" value="sales" id="sales" name="dept" <%
        if(dept.equals("sales"))
        		%> checked="<%out.print("checked");%>"/>
        销售部
        </label>
        <label class="radio">
        <input type="radio" value="purchase" name="dept" <%
        if(dept.equals("purchase"))
        		%> checked="<%out.print("checked");%>"//>
        采购部
        </label>
        <div style="display: none;">
        <input type="radio" value="all" id="all" name="dept" <%
        if(dept.equals("all"))
        		%> checked="<%out.print("checked");%>"/>
        <input type="radio" value="none" id="none" name="dept" <%
        if(dept.equals("none"))
        		%> checked="<%out.print("checked");%>"//>
        </div>
        </div>
        </div>
        <div class="control-group">
        <label class="control-label">
        </label>

        <div class="controls">
        <button class="btn" id="ok" onclick="go(this)">
        提 交
        </button>
        <a href="/ogzer/userList/1">
        <input class="btn" type="button" value="返 回"/>
        </a>
        </div>
        </div>
        </fieldset>
        </form>
        </div>

        <script type="text/javascript" src="/jquery/jquery-1.8.3.min.js" charset="UTF-8"></script>
        <script type="text/javascript" src="/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript">
        function checkPasswords() {
        var pass1 = document.getElementById("password");
        var pass2 = document.getElementById("password1");
        if (pass1.value != pass2.value)
        pass1.setCustomValidity("两次密码必须输入一致！");
        else
        pass1.setCustomValidity('');
        }
        function topMger(){
        var dept = document.getElementById("dept");
        var all = document.getElementById("all");
        all.checked="checked";
        dept.hidden="hidden";
        }
        function ogzer(){
        var dept = document.getElementById("dept");
        var none = document.getElementById("none");
        none.checked="checked";

        dept.hidden="hidden";
        }
        function defaultDept(){
        var dept = document.getElementById("dept");
        var none = document.getElementById("none");
        var all = document.getElementById("all");
        var sales = document.getElementById("sales");
        if(none.checked||all.checked)
        sales.checked="checked";
        dept.hidden="";
        dept.style="";
        }
        function go(form) {
        form.submit();
        }
        </script>

        </body>
        </html>
