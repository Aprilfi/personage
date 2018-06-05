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
                <div class="sign-in-htm">
                    <div class="form-group group">
                        <label for="login_user" class="label">用户名</label>
                        <input id="login_user" type="text" class="input">
                    </div>
                    <div class="form-group group">
                        <label for="login_password" class="label">密码</label>
                        <input id="login_password" type="password" class="input" data-type="password">
                    </div>
                    <div class="form-group group">
                        <input id="check" type="checkbox" class="check" checked>
                        <label for="check"><span class="icon"></span>&nbsp;&nbsp; 保&nbsp;持&nbsp;登&nbsp;录&nbsp; </label>
                    </div>
                    <div class="form-group group">
                        <input type="submit" class="button" value="登 录">
                    </div>
                    <div class="hr"></div>
                    <div class="foot-lnk">
                        <a href="#forgot">忘记密码？</a>
                    </div>
                </div>
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

<script type="text/javascript">
    $("#form_signup").bootstrapValidator({
        live: 'enabled',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
        excluded: [':disabled', ':hidden', ':not(:visible)'],//排除无需验证的控件，比如被禁用的或者被隐藏的
        submitButtons: '#register_submit',//指定提交按钮，如果验证失败则变成disabled，但我没试成功，反而加了这句话非submit按钮也会提交到action指定页面
        feedbackIcons: {//根据验证结果显示的各种图标
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            userNickname: {
                message: '用户名不符合要求',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {//检测长度
                        min: 4,
                        max: 12,
                        message: '长度必须在4-12之间'
                    }
                }
            },
            userPassword: {
                message: '密码不符合要求',
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {//检测长度
                                 min: 6,
                                 max: 30,
                                 message: '长度必须在6-30之间'
                    }
                }
            },
            userEmail: {
                message: '邮箱格式错误',
                validators: {
                    emailAddress: {
                        //验证email地址
                        message: '不是正确的email地址'
                    }
                }
            }
        }
    });
    $("#register_submit").click(function () {
        if ($("#form_signup").data('bootstrapValidator').isValid()) {
            ready();
            //获取验证结果，如果成功，提交数据到后台进行验证
            $.ajax({
                type: "POST",
                dataType: "text",
                url: "/user/register.do" ,//url
                data: $('#form_signup').serializeArray(),
                beforeSend: function () {
                    var overlay = iosOverlay({
                        text: "你等一下",
                        spinner: spinner
                    });
                    window.setTimeout(function() {
                        overlay.hide();
                    }, 2e3);
                },
                success: function (result) {
                    var overlay = iosOverlay({
                        icon: "img/check.png",
                        text: "好了,请进入邮箱激活"
                    });
                    window.setTimeout(function() {
                        overlay.hide();
                    }, 2e3);
                    window.open(result,'blank');
                },
                error : function() {
                    alert("异常！");
                }
            });
        }
    });
    function ready() {
        var opts = {
            lines: 13, // The number of lines to draw
            length: 11, // The length of each line
            width: 5, // The line thickness
            radius: 17, // The radius of the inner circle
            corners: 1, // Corner roundness (0..1)
            rotate: 0, // The rotation offset
            color: '#FFF', // #rgb or #rrggbb
            speed: 1, // Rounds per second
            trail: 60, // Afterglow percentage
            shadow: false, // Whether to render a shadow
            hwaccel: false, // Whether to use hardware acceleration
            className: 'spinner', // The CSS class to assign to the spinner
            zIndex: 2e9, // The z-index (defaults to 2000000000)
            top: 'auto', // Top position relative to parent in px
            left: 'auto' // Left position relative to parent in px
        };
        var target = document.createElement("div");
        document.body.appendChild(target);
        var spinner = new Spinner(opts).spin(target);
    }

























    // //邮箱去做后台唯一性校验
    // function registerForEmail (validator, form, submitButton) {
    //     var $email = $("#userEmail").val();
    //     console.log($email);
    //     $.ajax({
    //         url: path + "/user/validateEmail.do",
    //         type: "post",
    //         async: false,
    //         data: {
    //             "userEmail": $email
    //         },
    //         success: function (responseText) {
    //             if (responseText == "noEmail") {
    //                 validator.defaultSubmit();
    //                 alert("请到您指定的邮箱完成激活");
    //             } else {
    //                 alert("您的邮箱已注册过了");
    //             }
    //         },
    //         error: function () {
    //             alert("系统错误！");
    //         }
    //     });
    // }


    /////////////////////////////////////////
    //bootstrapvalidator 常用验证
    /////////////////////////////////////////
    // validators: {
    //     notEmpty: {//检测非空,radio也可用
    //         message: '文本框必须输入'
    //     },
    //     stringLength: {//检测长度
    //         min: 6,
    //         max: 30,
    //         message: '长度必须在6-30之间'
    //     },
    //     regexp: {//正则验证
    //         regexp: /^[a-zA-Z0-9_\.]+$/,
    //         message: '所输入的字符不符要求'
    //     },
    //     remote: {//将内容发送至指定页面验证，返回验证结果，比如查询用户名是否存在
    //         url: '指定页面',
    //         message: 'The username is not available'
    //     },
    //     different: {//与指定文本框比较内容相同
    //         field: '指定文本框name',
    //         message: '不能与指定文本框内容相同'
    //     },
    //     emailAddress: {//验证email地址
    //         message: '不是正确的email地址'
    //     },
    //     identical: {//与指定控件内容比较是否相同，比如两次密码不一致
    //         field: 'register_password',//指定控件name
    //         message: '输入的内容不一致'
    //     },
    //     date: {//验证指定的日期格式
    //         format: 'YYYY/MM/DD',
    //         message: '日期格式不正确'
    //     },
    //     choice: {//check控件选择的数量
    //         min: 2,
    //         max: 4,
    //         message: '必须选择2-4个选项'
    //     }
    // }
</script>

</body>
</html>

