<#include "fragment/head.ftl">
<!-- webuploader -->
<link href="<@spring.url '/res/backstage/webuploader/css/webuploader.css'/>" rel="stylesheet" type="text/css"/>
<!-- Right side column. Contains the navbar and content of the page -->
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            用户管理
            <small>Control panel</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="<@spring.url '/'/>"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li>用户</li>
            <li><a href="<@spring.url '/user/list'/>">用户管理</a></li>
            <li class="active"> <#if user.id == null>添加用户<#else>修改用户</#if></li>
        </ol>
    </section>

    <!-- Main content -->
    <section class="content">
        <!-- Small boxes (Stat box) -->
        <div class="row">
            <div class="box box-success">
                <div class="box-header">
                    <!-- tools box -->
                    <div class="pull-right box-tools">
                        <button id="saveUserForm" class="btn btn-success btn-sm" data-toggle="tooltip" title="保存">
                            <i class="fa fa-save"></i> 保存</button>
                        <button id="backUserList" class="btn btn-default btn-sm" data-toggle="tooltip" title="返回">
                            <i class="fa fa-mail-forward"></i> 返回</button>
                    </div><!-- /. tools -->
                    <i class="fa fa-globe"></i>

                    <h3 class="box-title"><#if user.id == null>添加用户<#else>修改用户</#if></h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body" style="position: relative;">
                    <form id="userForm" action="<@spring.url '/user/edit'/>" class="form-horizontal" role="form" method="POST">
                        <input type="hidden" value="${user.id}" name="id">
                        <div class="form-group">
                            <label for="loginname" class="col-sm-2 control-label">登录名称 <span class="v5-required">*</span></label>
                            <div class="col-sm-4">
                                <input type="text" <#if user.id != null>readonly</#if> class="form-control" name="account" id="account"
                                       placeholder="登陆名称" value="${user.account}" datatype="*" nullmsg="用户登录名称不能为空！" ajaxurl="${base}/user/checkAccountIsExist?id=${user.id}"/>
                                <span class="help-block">用户登录名称，用于登录系统使用。</span>
                            </div>
                            <div class="col-sm-3 Validform_checktip"></div>
                        </div>
                        <#if user.id == null>
                            <div class="form-group">
                                <label for="password" class="col-sm-2 control-label">登录密码 <span class="v5-required">*</span></label>
                                <div class="col-sm-4">
                                    <input class="form-control" name="password" id="password"
                                           placeholder="登录密码" value="${user.password}" datatype="s6-18" nullmsg="登录密码不能为空！" errormsg="请输入6-18位的密码！"/>
                                    <span class="help-block">用户登录密码，用于登陆系统使用。</span>
                                </div>
                                <div class="col-sm-3 Validform_checktip"></div>
                            </div>
                        </#if>
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">用户名 </label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="nickname" id="nickname"
                                       placeholder="用户名" value="${user.nickname}" />
                                <span class="help-block">用户名，用于显示使用。</span>
                            </div>
                            <div class="col-sm-3 Validform_checktip"></div>
                        </div>
                        <div class="form-group" style="padding-bottom: 10px;">
                            <label for="inputPassword3" class="col-sm-2 control-label">性别</label>
                            <div class="col-sm-8">
                                <label class="radio-inline" style="padding-left: 0px;">
                                    <input type="radio" name="sex" <#if user.sex == '男'>checked </#if> value="男"> 男
                                </label>
                                <label class="radio-inline" style="padding-left: 0px;">
                                    <input type="radio" name="sex"  <#if user.sex == '女'>checked </#if> value="女"> 女
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="mobilephone" class="col-sm-2 control-label">手机号码 </label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" name="mobilePhone" id="mobilePhone"
                                       placeholder="手机号码" value="${user.mobilePhone}" ignore="ignore" datatype="m"/>
                                <span class="help-block">填写用户手机号码。</span>
                            </div>
                            <div class="col-sm-3 Validform_checktip"></div>
                        </div>
                        <div class="form-group">
                            <label for="originalPic" class="col-sm-2 control-label" style="line-height: 80px;">用户头像上传 </label>
                            <div class="col-sm-4">
                                <input type="hidden" id="headImg" name="headImg" value="${user.headImg}">
                                <div id="originalPicImageUpload">
                                    <#if user.headImg != null>
                                        <img id="originalPicImg" src="${base}${user.headImg}" class="img-circle upload-img" style="width: 80px;box-shadow: 0 5px 10px #888888;" alt="用户头像上传"/>
                                    <#else>
                                        <img id="originalPicImg" src="${base}/res/backstage/images/avatar5.png" class="img-circle upload-img" style="width: 80px;box-shadow: 0 5px 10px #888888;" alt="用户头像上传"/>
                                    </#if>
                                </div>
                            </div>
                        </div>
                    </form>
                </div><!-- /.box-body -->
            </div><!-- /.box -->
        </div><!-- /.row -->
    </section><!-- /.content -->
</aside><!-- /.right-side -->
<#include "fragment/footer.ftl">
<!-- webuploader -->
<script src="<@spring.url '/res/backstage/webuploader/webuploader.min.js'/>" type="text/javascript"></script>
<script type="text/javascript">
    $(function(){
        $("#nav_siteSetting").imitClick();

        $("#backUserList").click(function(){
            location.href="<@spring.url '/user/list'/>"
        });

        $("#userForm").Validform({
            ajaxPost:true,
            showAllError:true,
            tiptype:2,
            callback:function(data){
                if(data.result){
                    layer.msg(data.message, {
                        icon: 1,
                        time:2000
                    },function(){
                        console.log(data);
                        location.href="<@spring.url '/user/list'/>";
                    });
                }else{
                    layer.msg(data.message, {icon: 2});
                }
            }
        });

        $("#saveUserForm").click(function(){
            $("#userForm").submit();
        });

        //上传图片
        var uploadImage = WebUploader.create({
            // swf文件路径
            swf: "<@spring.url '/res/backstage/webuploader/Uploader.swf'/>",
            auto: true,
            // 文件接收服务端。
            server: '<@spring.url '/upload?tt='/>'+new Date().getTime(),
            // 选择文件的按钮。可选。
            // 内部根据当前运行是创建，可能是input元素，也可能是flash.
            pick: '#originalPicImageUpload',

            accept: {
                title: 'Images',
                extensions: 'gif,jpg,jpeg,bmp,png',
                mimeTypes: 'image/*'
            }
        });

        uploadImage.on( 'uploadSuccess', function( file,response ) {

            if(!response.result){
                layer.msg(response.message, {icon: 2});
                return;
            }
            layer.msg(response.message, {icon: 1});
            $("#headImg").val(response.data.filePath);
            $("#originalPicImg").attr("src",response.data.contentPath + response.data.filePath)
        });

        uploadImage.on( 'uploadError', function( file,reason  ) {
            layer.msg("上传图片出错！", {icon: 2});
        });

        uploadImage.on("beforeFileQueued",function(file){
            /*var temp = $("#adv_image_url").val();
            if(temp != null && temp != ""){
                layer.msg("您已经上传了一张图片，请先删除在上传！", {icon: 2});
                return false;
            }*/
            return true;
        });

    });
</script>