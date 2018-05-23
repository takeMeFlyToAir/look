<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">
    <title>用户登录</title>
    <link rel="stylesheet" href="<@spring.url '/res/backstage/css/bootstrap.css'/>">
    <link rel="stylesheet" href="<@spring.url '/res/backstage/css/bootstrap-theme.css'/>">
    <link rel="stylesheet" href="<@spring.url '/res/backstage/css/main.css'/>">
    <script src="<@spring.url '/res/backstage/js/jquery-1.10.1.min.js'/>" type="text/javascript"></script>
    <script src="<@spring.url '/res/backstage/js/bootstrap.min.js'/>" type="text/javascript"></script>
    <script src="${base}/res/backstage/js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${base}/res/backstage/js/messages_zh.min.js" type="text/javascript"></script>
</head>
<style>
    label.error
    {
        color:Red;
    }
</style>
<body>
<div class="panel panel-primary login-panel">
    <div class="panel-heading">
        <h3 class="panel-title">用户登录</h3>
    </div>
    <div class="panel-body">
        <form id="loginForm" name="loginForm">
            <div class="form-group">
                <label for="managerName" class="col-xs-3 control-label">用户名</label>
                <div class="col-xs-9">
                    <input type="text" class="form-control" name="account" id="account" placeholder="用户名" >
                </div>
            </div>
            <div class="form-group">
                <label for="managerPwd" class="col-xs-3 control-label">密码</label>
                <div class="col-xs-9">
                    <input type="password" class="form-control" name="password" id="password" placeholder="密码">
                </div>
            </div>
        <form/>
            <div class="form-group">
                <div class="col-xs-offset-3 col-xs-9">
                    <button type="button" id="subButton" onclick="add()" class="btn btn-primary col-xs-9">
                        <span class="glyphicon glyphicon-log-in"></span>&nbsp;
                        登&nbsp;&nbsp;录
                    </button>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-offset-3 col-xs-9">
                    <label style="color: #ff0000" id="errorMsg">${error!""}</label>
                </div>
            </div>

    </div>
</div>
<script type="text/javascript">
    $(function(){
        //增加校验
        $("#loginForm").validate({
            rules : {
                account : {
                    required : true,
                },
                password : {
                    required : true
                },
                captcha : {
                    required : true
                }
            },
            messages : {
                account : {
                    required : '请输入用户名'
                },
                password : {
                    required : '请输入密码'
                },
                captcha : {
                    required : '请输入验证码'
                }
            }
        });

        $("#vcodeImg").click(function(){
            var url = $(this).attr('src').replace(/&r=[\w]*/g,'');
            $(this).attr('src',url+'?r='+new Date().getTime());
        });
        $("#loginForm").onclick = function () {
            alert(3);
        }
    });
    function add() {
        console.log($("#loginForm").valid())
        if ($("#loginForm").valid()){
            var data = {};
            data.account = $("#account").val();
            data.password = $("#password").val();
            $.ajax({
                type : "post",
                url : '${base}/ajaxLogin',
                data : data,
                dataType : "json",
                async : false,
                success : function(data) {
                    if (data.result) {
                        window.location.href = "/";
                    } else {
                        $("#errorMsg").text(data.message);
                    }
                }
            });
        }
    }


</script>
</body>
</html>