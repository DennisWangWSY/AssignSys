<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="model.Overtimesheet" %>
<%@page import="com.jfinal.plugin.activerecord.Record" %>
<!DOCTYPE html >
<html>
<head>
    <title id="title">加班单详情</title>
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
    min-width: 300px;
    max-width: 600px;
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
    String name = (String)request.getAttribute("name");
    Overtimesheet ots = (Overtimesheet)request.getAttribute("ots");
%>
<div class="wrap">
    <form class="main form-horizontal" method="post" action="/topMger/agreeOvertime">

        <fieldset>
            <legend>审核加班申请——详细信息</legend>

            <div class="control-group">
                <label class="control-label">
                    ID</label>
                <div class="controls">
                    <input name="id" class="input-xlarge" value="<%out.println(ots.get("id"));%>" readonly>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">
                    工号</label>
                <div class="controls">
                    <input class="input-xlarge" value="<%out.println(ots.get("uid")); %>" readonly>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">
                    姓名</label>
                <div class="controls">
                    <input class="input-xlarge" value="<%out.println(name); %>" readonly>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">
                    开始日期</label>
                <div class="controls">
                    <input name="startDate" class="input-xlarge" value="<%out.println(ots.getStarttime().toString().substring(0, 10)); %>" readonly>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label">
                    结束日期</label>
                <div class="controls">
                    <input name="endDate" class="input-xlarge" value="<%out.println(ots.getEndtime().toString().substring(0, 10)); %>" readonly>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label">
                    加班原因</label>

                <div class="controls">
                    <textarea type="text"  readonly><%out.println(ots.getCause()); %></textarea>
                </div>
            </div>
                        <div class="control-group">
                <label class="control-label">
                    部门经理意见</label>

                <div class="controls">
                    <textarea type="text"  readonly><%out.println(ots.getComment1()); %></textarea>
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
                <button class="btn" id="ok" onclick="">
                    提 交
                </button>
                <a href="/topMger/checkOvertime/1">
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
</script>
<script>

</script>
</body>
</html>
