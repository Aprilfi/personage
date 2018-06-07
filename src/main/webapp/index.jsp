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
    <title>T  o u  c h</title>
    <link rel="shortcut icon" href="img/bitbug_favicon.ico" />
    <!--弹出层-->
    <link rel="stylesheet" href="css/popup/bootstrap.min.css">
    <link rel="stylesheet" href="css/popup/custom.css">
    <link rel="stylesheet" href="css/popup/iosOverlay.css">
    <link rel="stylesheet" href="css/popup/prettify.css">
    <script src="js/popup/modernizr-2.0.6.min.js"></script>
    <!--bootstrap与validation样式和JS-->
    <script src="bootstrap-3.3.7-dist/js/jquery-2.1.4.min.js"></script>
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
                <form action="${path}/user/login.do" method="post" id="form_signin">
                <div class="sign-in-htm">
                    <div class="form-group group">
                        <label for="login_user" class="label">邮箱</label>
                        <input id="login_user" name="userEmail" type="text" class="input">
                    </div>
                    <div class="form-group group">
                        <label for="login_password" class="label">密码</label>
                        <input id="login_password" name="password" type="password" class="input" data-type="password">
                    </div>
                    <div class="form-group group">
                        <input id="login_captcha" name="captcha" type="text" class="input" style="width: 230px; display:inline;"/>
                        <img src="${path}/user/getGifCode.do" id="captcha" style=" display:inline;-webkit-border-radius: 15px;-moz-border-radius: 15px;border-radius: 15px;"/>
                    </div>
                    <div class="form-group group">
                        <input id="check" type="checkbox" class="check" checked>
                        <label for="check"><span class="icon"></span>&nbsp;&nbsp; 保&nbsp;持&nbsp;登&nbsp;录&nbsp; </label>
                    </div>
                    <div class="form-group group">
                        <input type="submit" id="login_submit" class="button" value="登 录">
                    </div>
                    <div class="hr"></div>
                    <div class="foot-lnk">
                        <a href="">忘记密码？</a>
                    </div>
                </div>
                </form>
                <form class="form_signup" action="user/register.do" method="post" role="form" id="form_signup">
                    <div class="sign-up-htm">
                        <div class="form-group group">
                            <label for="register_user" class="label">用户名</label>
                            <input id="register_user" name="userNickname" type="text" class="input">
                        </div>
                        <div class="form-group group">
                            <label for="register_password" class="label">密码</label>
                            <input id="register_password" name="userPassword" type="password" class="input" data-type="password">
                        </div>
                        <div class="form-group group">
                            <label for="register_email" class="label">邮箱</label>
                            <input id="register_email" name="userEmail" type="text" class="input">
                        </div>
                        <div class="form-group group">
                            <input type="button" id="register_submit" class="button" value="注 册">
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

<script src="js/popup/iosOverlay.js"></script>

<script src="js/popup/spin.min.js"></script>

<script src="js/popup/prettify.js"></script>

<script src="js/popup/custom.js"></script>

<script src="js/register/toregister.js"></script>

</body>
</html>

