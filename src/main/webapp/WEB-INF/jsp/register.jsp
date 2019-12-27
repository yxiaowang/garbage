<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache,must-revalidate">
    <title>注册-个人用户</title>
    <link type="text/css" rel="stylesheet" href="/css/regist.personal.css"/>
    <link type="text/css" rel="stylesheet" href="/css/passport.base.css"/>
    <script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
</head>
<body>

<div class="w" id="regist">
    <div class="mt">
        <div class="extra">
        <span>我已经注册，现在就&nbsp;
        	<a href="/user/showLogin" class="flk13">登录</a>
        </span>
        </div>
    </div>
    <div class="mc">
        <form id="personRegForm" method="post" style="margin-left:230px;" onsubmit="return false;">
            <div class="form" onselectstart="return false;">
                <div class="item" id="select-regName">
                    <span class="label"><b class="ftx04">*</b>用户名：</span>

                    <div class="fl item-ifo">
                        <div class="o-intelligent-regName">
                            <input type="text" id="regName" name="username" class="text" tabindex="1" autoComplete="off"
                                   onpaste="return false;"
                                   value=""
                                   onfocus="if(this.value=='') this.value='';this.style.color='#333'"
                                   onblur="if(this.value=='') {this.value='';this.style.color='#999999'}"/>
                            <i class="i-name"></i>
                            <ul id="intelligent-regName" class="hide"></ul>
                            <label id="regName_succeed" class="blank"></label>
                            <label id="regName_error" class="hide"></label>
                        </div>
                    </div>
                </div>
                <div id="o-password">
                    <div class="item">
                        <span class="label"><b class="ftx04">*</b>请设置密码：</span>

                        <div class="fl item-ifo">
                            <input type="password" id="pwd" name="passwd" class="text" tabindex="2"
                                   style="ime-mode:disabled;"
                                   onpaste="return  false" autocomplete="off"/>
                            <i class="i-pass"></i>
                            <label id="pwd_succeed" class="blank"></label>
                            <label id="pwd_error"></label>
                            <span class="clr"></span>
                        </div>
                    </div>

                    <div class="item">
                        <span class="label"><b class="ftx04">*</b>请确认密码：</span>

                        <div class="fl item-ifo">
                            <input type="password" id="pwdRepeat" name="pwdRepeat" class="text" tabindex="3"
                                   onpaste="return  false" autocomplete="off"/>
                            <i class="i-pass"></i>
                            <label id="pwdRepeat_succeed" class="blank"></label>
                            <label id="pwdRepeat_error"></label>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <span class="label">&nbsp;</span>
                    <input type="button" class="btn-img btn-regist" id="registsubmit" value="立即注册" tabindex="8"
                           clstag="regist|keycount|personalreg|07"
                           onclick="REGISTER.reg();"/>
                </div>
            </div>
            <span class="clr"></span>
        </form>
    </div>
    <script type="text/javascript">
        var REGISTER = { //相当于一个类
            param: {
                //单点登录系统的url,当前系统的根路径
                surl: ""
            },
            inputcheck: function () {
                //不能为空检查
                if ($("#regName").val() == "") {
                    alert("用户名不能为空");
                    $("#regName").focus();
                    return false;
                }
                if ($("#pwd").val() == "") {
                    alert("密码不能为空");
                    $("#pwd").focus();
                    return false;
                }
                if ($("#phone").val() == "") {
                    alert("手机号不能为空");
                    $("#phone").focus();
                    return false;
                }
                //密码检查
                if ($("#pwd").val() != $("#pwdRepeat").val()) {
                    alert("确认密码和密码不一致，请重新输入！");
                    $("#pwdRepeat").select();
                    $("#pwdRepeat").focus();
                    return false;
                }
                return true;
            },
            beforeSubmit: function () {
                //检查用户是否已经被占用
                $.ajax({
                    //这里的url后面加入一个随机参数是为了防止浏览器get缓存
                    url: REGISTER.param.surl + "/user/check/" + escape($("#regName").val()) + "/1?r=" + Math.random(),
                    success: function (data) {
                        if (data.data) {
                            REGISTER.doSubmit();
                        } else {
                            alert("此用户名已经被占用，请选择其他用户名");
                            $("#regName").select();
                        }
                    }
                });

            },
            doSubmit: function () {
                //表单序列化传给后台
                $.post("/user/register", $("#personRegForm").serialize(), function (data) {
                    if (data.status == 200) {
                        alert('用户注册成功，请登录！');
                        REGISTER.login();
                    } else {
                        alert("注册失败！");
                    }
                });
            },
            login: function () {
                location.href = "/user/login";
                return false;
            },
            reg: function () {
                if (this.inputcheck()) {
                    this.doSubmit();
                }
            }
        };
    </script>
</body>
</html>
