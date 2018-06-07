$(function () {
    /////////////////////////////
    //登陆
    /////////////////////////////
    //点击图片换一张验证码
    $("#captcha").click(function () {
        $(this).attr("src", "/user/getGifCode.do?time=" + new Date().getTime());
    });


    /////////////////////////////
    //注册
    /////////////////////////////
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
            },

        }
    });
    $("#register_submit").click(function () {
        if ($("#form_signup").data('bootstrapValidator').isValid()) {

            //提示层准备开始
            var opts = {
                lines: 13, // The number of lines to draw
                length: 6, // The length of each line
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
            //提示层准备结束

            //邮箱去做后台唯一性校验
            var $email = $("#register_email").val();
            $.ajax({
                url: "/user/validateEmail.do",
                type: "post",
                async: false,
                data: {
                    "userEmail": $email
                },
                success: function (responseText) {
                    if (responseText == "noEmail") {
                        //获取验证结果，如果成功，提交数据到后台进行验证
                        $.ajax({
                            type: "POST",
                            dataType: "text",
                            url: "/user/register.do" ,//url
                            data: $('#form_signup').serializeArray(),
                            beforeSend: function () {
                                //清空表单
                                $("#form_signup")[0].reset();
                                tips("你等一下");
                            },
                            success: function (result) {
                                tipsOK("请进入邮箱激活","img/check.png");
                                //window.location.href='${path}/index.jsp';
                                //window.open(result,'blank');
                            },
                            error : function() {
                                alert("异常！");
                            }
                        });
                    } else {
                        tipsOK("邮箱已被注册","img/cross.png");
                    }
                },
                error: function () {
                    tips("system errors")
                }
            });
        }

        function tipsOK (content,src) {
            var overlay = iosOverlay({
                icon: src,
                text: content,
            });
            window.setTimeout(function() {
                overlay.hide();
            }, 2e3);
        }

        function tips (content) {
            var overlay = iosOverlay({
                text: content,
                spinner: spinner
            });
            window.setTimeout(function() {
                overlay.hide();
            }, 2e3);
        }

    });



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
});