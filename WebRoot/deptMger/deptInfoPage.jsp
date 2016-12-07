<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.jfinal.plugin.activerecord.Page" %>
<%@page import="com.jfinal.plugin.activerecord.Record" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>部门信息</title>

    <link href="/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="/js/ie-emulation-modes-warning.js"></script>
    <link href="/css/carousel.css" rel="stylesheet">
</head>
<body>
    <div class="empty" data-ride="carousel">

    </div><!-- /.carousel -->
<div class="container marketing">

    <div class="row">
        <div class="col-lg-4">
            <img class="img-circle" src="/img/vacation.png" alt="Generic placeholder image" style="width: 140px; height: 140px;">
            <h2>该部门员工今年已经休假<% out.print(request.getAttribute("total")); %>天</h2>
            <p>包括年假<% out.print(request.getAttribute("year")); %>天，
                病假<% out.print(request.getAttribute("sick")); %>天，
                产假<% out.print(request.getAttribute("birth")); %>天，
                婚假<% out.print(request.getAttribute("marriage")); %>天，
                事假<% out.print(request.getAttribute("other")); %>天，
                丧假<% out.print(request.getAttribute("dead")); %>天。</p>
        </div>
        <div class="col-lg-4">
            <img class="img-circle" src="/img/overtime.png" alt="Generic placeholder image" style="width: 140px; height: 140px;">
            <h2>当年部门共加班<% out.print(request.getAttribute("overtime")); %>天</h2>
        </div>
        <div class="col-lg-4">
            <img class="img-circle" src="/img/details.png" alt="Generic placeholder image" style="width: 140px; height: 140px;">
            <h2>详细记录</h2>
            <p>筛选部门员工的各种请假、加班记录</p>
            <p><a class="btn btn-default" href="/deptMger/leaveRules" role="button">选择筛选条件 &raquo;</a></p>
        </div>
    </div>


    <hr class="featurette-divider">

    <footer>
        <p class="pull-right">
            <a href="/toMain">
            <input class="btn" type="button" value="返 回"/></a>
        </p>
        <p>2016.12 &middot; </p>
    </footer>

</div>
<script src="/dist/js/jquery.min.js"></script>
<script src="/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
