<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="model.Users" %>
<!DOCTYPE html >
<html>
<head>
    <title id="title">员工详情</title>
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
<%
    Users ots = (Users)request.getAttribute("user");
%>
<div class="wrap">
    <form class="main form-horizontal" method="post" action="#">

        <fieldset>
            <legend>修改员工基本信息</legend>

            <div class="control-group">
                <label class="control-label">
                    工号</label>
                <div class="controls">
                    <input class="input-xlarge" value="<%out.println(ots.get("uid")); %>" readonly>
                </div>
            </div>
    <div class="control-group">
    <label class="control-label">
    密码</label>
    <div class="controls">
    <input id="pwd" name="pwd" type="password" placeholder="必填项" class="input-xlarge"
    required onchange="checkPasswords()">
    </div>
    </div>
    <div class="control-group">
    <label class="control-label">
    确认密码</label>
    <div class="controls">
    <input id="pwd1" name="pwd1" type="password" placeholder="必填项" class="input-xlarge"
    required onchange="checkPasswords()">
    </div>
    </div>
    <div class="control-group">
    <label class="control-label">
    职位</label>
    <div class="controls">
    <input class="input-xlarge" value="<%
                String type = ots.getStr("type");
                if(type.equals("staff"))
                	out.println("员工");
                else if(type.equals("deptMger"))
                	out.println("部门经理");
                else if(type.equals("topMger"))
                	out.println("总经理");
                else if(type.equals("ogzer"))
                	out.println("管理员");
                %>" readonly>
    </div>
    </div>
    <div class="control-group">
    <label class="control-label">
    部门</label>
    <div class="controls">
    <input class="input-xlarge" value="<%
                String type = ots.getStr("dept");
                if(type.equals("sales"))
                	out.println("销售部");
                else if(type.equals("deptMger"))
                	out.println("部门经理");
                %>" readonly>
    </div>
    </div>

            <div class="control-group">
                <label class="control-label">
                    开始日期</label>
                <div class="controls">
                    <input class="input-xlarge" value="<%out.println(ots.getStarttime().toString().substring(0, 10)); %>" readonly>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">
                    结束日期</label>
                <div class="controls">
                    <input class="input-xlarge" value="<%out.println(ots.getEndtime().toString().substring(0, 10)); %>" readonly>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">
                    请假事由</label>

                <div class="controls">
                    <textarea type="text"  readonly><%out.println(ots.getCause()); %></textarea>
                </div>
            </div>
    <div class="control-group">
    <label class="control-label">
    相关证明</label>
    <div class="controls">
    <%
        if(ots.getStr("evidence")==null)
        {%><label>无</label>
        <%}else{
        %>
    <img src="/evidence/<%out.print(ots.getStr("evidence")); %>"></img><%}%>
    </div>
    </div>
        </fieldset>
        <div class="control-group">
            <label class="control-label">
                审批结果
            </label>

            <div class="controls">
                <label class="radio">
                    <input type="radio" value="agreed" name="choice" checked="checked" onclick="agree()"/>
                    同 意
                </label>
                <label class="radio">
                    <input type="radio" value="denied" name="choice" onclick="deny()">
                    拒 绝
                </label>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label">
                审批意见</label>

            <div class="controls">
                <textarea id="comment" name="comment" type="text" required>批准</textarea>
                </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <button class="btn" id="ok" type="submit">
                    提 交
                </button>
                <a href="/deptMger/checkLeave/1">
                    <input class="btn" type="button" value="返 回"/>
                </a>
            </div>
        </div>
    </form>

    </div>
</div>
<script type="text/javascript" src="/jquery/jquery-1.8.3.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
    function  agree() {
        document.getElementById("comment").value = "批准";
    }
    function deny() {
        document.getElementById("comment").value="驳回";
    }
    function checkPasswords() {
    var passl = document.getElementById("pwd");
    var pass2 = document.getElementById("pwd1");
    if (passl.value != pass2.value)
    passl.setCustomValidity("两次密码必须输入一致！");
    else
    passl.setCustomValidity('');
    }
</script>
<script>

</script>
</body>
</html>
