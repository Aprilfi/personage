<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Touch</title>
    <!--弹出层-->
    <link rel="stylesheet" href="css/popup/bootstrap.min.css">
    <link rel="stylesheet" href="css/popup/custom.css">
    <link rel="stylesheet" href="css/popup/iosOverlay.css">
    <link rel="stylesheet" href="css/popup/prettify.css">
    <script src="js/popup/modernizr-2.0.6.min.js"></script>
    <!--bootstrap与validation样式和JS-->
    <link href="bootstrap-3.3.7-dist/css/bootstrapValidator.min.css" rel="stylesheet">
    <link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="bootstrap-3.3.7-dist/js/bootstrapValidator.min.js"></script>
    <!--主板样式-->
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <script src="js/html5shiv.min.js"></script>
</head>
<body>
<video autoplay loop id="bgvid">

    <source src="video/moving-cloud.mp4" type="video/mp4">

</video>
<div class="jq22-container">
    <div class="login-wrap">
        <div>
            T&nbsp;&nbsp;o&nbsp;&nbsp;&nbsp;u&nbsp;&nbsp;c&nbsp;h
        </div>
        <div class="login-html">
            <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
            <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
            <div class="login-form">
                <div class="sign-in-htm">
                    <div class="group">
                        <label for="login_user" class="label">用户名</label>
                        <input id="login_user" type="text" class="input">
                    </div>
                    <div class="group">
                        <label for="login_password" class="label">密码</label>
                        <input id="login_password" type="password" class="input" data-type="password">
                    </div>
                    <div class="group">
                        <input id="check" type="checkbox" class="check" checked>
                        <label for="check"><span class="icon"></span>&nbsp;&nbsp; 保&nbsp;持&nbsp;登&nbsp;录&nbsp; </label>
                    </div>
                    <div class="group">
                        <input type="submit" class="button" value="登 录">
                    </div>
                    <div class="hr"></div>
                    <div class="foot-lnk">
                        <a href="#forgot">忘记密码？</a>
                    </div>
                </div>
                <form class="form_signup" action="${path}/user/register.do" method="post">
                    <div class="sign-up-htm">
                        <div class="group">
                            <label for="register_user" class="label">用户名</label>
                            <input id="register_user" type="text" class="input">
                        </div>
                        <div class="group">
                            <label for="register_password" class="label">密码</label>
                            <input id="register_password" type="password" class="input" data-type="password">
                        </div>
                        <div class="group">
                            <label for="register_inspectpassword" class="label">确认密码</label>
                            <input id="register_inspectpassword" type="password" class="input" data-type="password">
                        </div>
                        <div class="group">
                            <label for="register_email" class="label">邮箱</label>
                            <input id="register_email" type="text" class="input">
                        </div>
                        <div class="group">
                            <input type="submit" class="button" value="注 册">
                        </div>
                        <div class="hr"></div>
                        <div class="foot-lnk">
                            <label for="tab-1">touch me,oh shit</label>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!--弹出层-->
<script src="js/popup/jquery.min.js"></script>

<script src="js/popup/iosOverlay.js"></script>

<script src="js/popup/spin.min.js"></script>

<script src="js/popup/prettify.js"></script>

<script src="js/popup/custom.js"></script>

<script type="text/javascript">
    //邮箱去做后台唯一性校验
    function registerForEmail (validator, form, submitButton) {
        var $email = $("#userEmail").val();
        console.log($email);
        $.ajax({
            url: path + "/user/validateEmail.do",
            type: "post",
            async: false,
            data: {
                "userEmail": $email
            },
            success: function (responseText) {
                if (responseText == "noEmail") {
                    validator.defaultSubmit();
                    alert("请到您指定的邮箱完成激活");
                } else {
                    alert("您的邮箱已注册过了");
                }
            },
            error: function () {
                alert("系统错误！");
            }
        });
    }
</script>
</body>
</html>