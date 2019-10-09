
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

    String path = request.getContextPath();


    String basePath = request.getScheme() + "://"

            + request.getServerName() + ":" + request.getServerPort()

            + path + "/";

%>

<html>

<head>

    <title>新增员工</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- 引入 Bootstrap -->

    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

</head>

<body>

<div class="container">

    <div class="row clearfix">

        <div class="col-md-12 column">

            <div class="page-header">

                <h1>

                    员工管理

                </h1>

            </div>

        </div>

    </div>



    <div class="row clearfix">

        <div class="col-md-12 column">

            <div class="page-header">

                <h1>

                    <small>新增员工</small>

                </h1>

            </div>

        </div>

    </div>

    <form action="" name="userForm">

        <div class="form-group has-success">
        姓名：<input type="text"  id="name" name="name">
            <span  id="msg" class="help-block"></span>
        </div>
        <br>
        <div class="form-group has-success">
        年龄：<input type="text"  id="age" name="age" >
            <span id="msg1" class="help-block"></span>
        </div>
        <br>
        <div class="form-group has-success">
        性别：<input type="text" id="sex" name="sex">
            <span id="msg2" class="help-block"></span>
        </div>
        <br>
        <div class="form-group has-success">
        email：<input type="text" id="email" name="email">
            <span id="msg3" class="help-block"></span>
        </div>
        <br>
        <div class="form-group has-success">
        手机：<input type="text" id="phone" name="phone">
            <span id="msg4" class="help-block"></span>
        </div>
        <br><br><br>
        <input type="button" value="添加" onclick="checkText()">

    </form>



    <script type="text/javascript">

        function addemp() {

            var form = document.forms[0];

            form.action = "<%=basePath %>emp/addemp";

            form.method = "post";

            form.submit();

        }
        
        function isNaN() {
                var reNum=/^\d*$/;
                return(reNum.test(num));
        }
        
        
        function checkText() {
            var name =document.getElementById("name").value;
            var age = document.getElementById("age").value;
            var sex = document.getElementById("sex").value;
            var email = document.getElementById("email").value;
            var phone = document.getElementById("phone").value;
            var regex = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
            var phoneReg = /^1[3-578]\d{9}$/;
            if(name == ""){
                         document.getElementById("msg").innerText = "不能为空";
            }
            else if(age == ""){
                         document.getElementById("msg").innerText = "" ;
                         document.getElementById("msg1").innerText = "不能为空";
            }
            else if(!(/(^[1-9]\d*$)/.test(age))){
                         document.getElementById("msg1").innerText = "不能为非正整数";
            }
            else if(sex == ""){
                         document.getElementById("msg1").innerText = "" ;
                         document.getElementById("msg2").innerText = "不能为空";
            }
            else if(email == ""){
                         document.getElementById("msg2").innerText = "" ;
                         document.getElementById("msg3").innerText = "不能为空";
            }
            else if(!regex.test(email)){
                         document.getElementById("msg3").innerText = "邮箱格式不对";
            }
            else if(phone == ""){
                         document.getElementById("msg3").innerText = "";
                         document.getElementById("msg4").innerText = "不能为空";
            }
            else if(phoneReg.test(phone)){
                         document.getElementById("msg4").innerText = "号码位数不为11";
            }
            else {
                alert("添加成功！");
                addemp()
            }
        }

    </script>

</div>
