<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% String appPath = request.getContextPath();
    String path = request.getContextPath();
    System.out.print(path);
    String basePath = request.getScheme() + "://"

            + request.getServerName() + ":" + request.getServerPort()

            + path + "/";

%>

<html>

<head>

    <title>emp列表</title>

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

                    <small>显示所有员工</small>

                </h1>

            </div>

        </div>

    </div>

    <div class="row">

        <div class="col-md-4 column">
        <a class="btn btn-primary" href="${path}toAddemp">新增</a>
        </div>
        <div class="col-xs-9">
        <form class="col" action="" name = "userForm" >
        <input type="text" name="name">
        <input class="btn btn-default" type="button" value="查询" onclick="selectemp()">
        </form>
        </div>


        <div class="col-xs-6 col-sm-4"> <input type="button"  class="btn btn-default" value="导出" onclick="OutputExce()">
            <input class="btn btn-default" type="button" value="导入" onclick="uploadFile()">
        </div>
        <div class="col-xs-6 col-sm-4"><input type="file" id="upload" name="upload" value="" /></div>







    </div>



    <div class="row clearfix">

        <div class="col-md-12 column">

            <table class="table table-hover table-striped">

                <thead>

                <tr>

                    <th>姓名</th>

                    <th>年龄</th>

                    <th>性别</th>

                    <th>email</th>

                    <th>手机</th>

                    <th>操作</th>

                </tr>

                </thead>

                <tbody>

                <c:forEach var="emp" items="${pageInfo.list}" varStatus="status">

                    <tr>

                        <td>${emp.name}</td>

                        <td>${emp.age}</td>

                        <td>${emp.sex}</td>

                        <td>${emp.email}</td>

                        <td>${emp.phone}</td>

                        <td>

                            <a href="${path}toUpdateemp?id=${emp.id}">更改</a> |

                            <a href="<%=appPath%>/emp/del/${emp.id}">删除</a>

                        </td>

                    </tr>

                </c:forEach>

                </tbody>


            </table>
            <p>当前 ${pageInfo.pageNum }页,总${pageInfo.pages }
                页,总 ${pageInfo.total } 条记录</p>
        </div>

            <div style="text-align: center;">
                <a href="allemp?pageNo=${pageInfo.firstPage}">第一页</a>

                <c:if test="${pageInfo.hasPreviousPage }">
                    <a href="allemp?pageNo=${pageInfo.pageNum-1}">上一页</a>
                </c:if>
                <c:if test="${pageInfo.hasNextPage }">
                    <a href="allemp?pageNo=${pageInfo.pageNum+1}">下一页</a>
                </c:if>

                <a href="allemp?pageNo=${pageInfo.lastPage}">最后页</a>
            </div>
    </div>

    <script type="text/javascript"src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript">

        function selectemp() {

            var form = document.forms[0];

            form.action = "<%=basePath %>emp/selectemp";

            form.method = "post";

            form.submit();

        }

        function uploadFile() {
            var file = $("#upload").val();
            file = file.substring(file.lastIndexOf('.'), file.length);
            if (file === '') {
                alert("上传文件不能为空！");
            } else if (file !== '.xlsx' && file !== '.xls') {
                alert("请选择正确的excel类型文件！");
            } else {
                ajaxFileUpload();
            }
        }
        function ajaxFileUpload() {

            var formData = new FormData();
            var name = $("#upload").val();
            formData.append("file", $("#upload")[0].files[0]);
            formData.append("name", name);
            $.ajax({
                url : "<%=basePath %>excel/input",
                type : "POST",
                async : false,
                data : formData,
                processData : false,
                contentType : false,
                beforeSend : function() {
                    console.log("正在进行，请稍候");
                },
                success : function(e) {
                    if (e == "01") {
                       alert("导入成功");
                        window.location.reload();
                    } else {
                        alert("导入失败");
                        window.location.reload();
                    }
                }
            });
        }



        function OutputExce() {
            window.location.href = "<%=basePath %>/excel/output";
        }
    </script>
</div>


