<#include "fragment/head.ftl">
<!-- Right side column. Contains the navbar and content of the page -->
<aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            用户管理
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li>用户</li>
            <li class="active">用户管理</li>
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
                        <button id="addUser" class="btn btn-success btn-sm" data-toggle="tooltip" title="添加用户">
                            <i class="fa fa-plus"></i> 添加用户</button>
                        <button id="userBatchDelete" class="btn btn-warning btn-sm" data-toggle="tooltip" title="批量删除">
                            <i class="fa fa-trash-o"></i> 批量删除</button>
                    </div><!-- /. tools -->
                    <i class="fa fa-table"></i>
                    <h3 class="box-title">用户列表</h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive">
                    <div class="container-fluid" style="margin-bottom: 8px;">
                        <form id="userForm">
                        <#--<div class="col-xs-1 v5-text-align">-->
                        <#--<label>用户名称</label>-->
                        <#--</div>-->
                            <div class="col-xs-4">
                                <input type="text" class="form-control" name="account"
                                       id="userName" placeholder="账号">
                            </div>
                            <div class="col-xs-4">
                                <button type="button" id="searchFormButton" class="btn btn-success">
                                    <i class="fa fa-search"></i>
                                    查询
                                </button>
                            </div>
                        </form>
                    </div>
                    </div>
                    </div>
                    <table id="userListTable" class="table table-striped table-bordered "
                           data-url="${base}/user/loadUser"
                           class="table table-striped table-hover "
                           data-toggle="table"
                           data-query-params="adminUserParam"
                           data-side-pagination="server"
                           data-pagination="true">
                        <thead>
                        <tr>
                            <th  data-checkbox="true"></th>
                            <th  data-field="account" data-align="center" >账号</th>
                            <th  data-field="nickname"  data-align="center">姓名</th>
                            <th  data-field="mobilePhone"  data-align="center">手机号</th>
                            <th  data-field="sex"  data-align="center">性别</th>
                            <th  data-align="center"  data-formatter="operationFormatter">操作</th>
                        </tr>
                        </thead>
                    </table>
                </div><!-- /.box-body -->
            </div><!-- /.box -->
        </div><!-- /.row -->
    </section><!-- /.content -->
</aside><!-- /.right-side -->
<#include "fragment/footer.ftl">
<script type="text/javascript">
    function operationFormatter(value, row,index){
        var html = "";
        html += '<a href="${base}/user/edit/'+row.id+'" class="btn btn-primary btn-xs" data-toggle="tooltip" title="修改用户"> <i class="fa fa-edit"></i></a>&nbsp;&nbsp;';
        html += '<a href="javascript:void(0);" data-userId="'+row.id+'" onclick="deleteUser(\''+row.id+'\')" id="deleteUser" class="btn btn-warning btn-xs " data-toggle="tooltip" title="删除用户"> <i class="fa fa-times"></i></a>';
        return html;
    }
    function adminUserParam(params) {
        GT.Common.getFormData($('#userForm'), params);
        return params;
    }
    function deleteUser(userIds) {
        $.v5cms.confirm({icon:"question",content:"您确定要删除用户吗，删除后将不能恢复？",width:350,ok:function(){
            var url = "${base}/user/deleteUser";
            $.ajax({
                dataType:'json',
                type:'POST',
                url:url,
                data:{"ids":userIds},
                success:function(data){
                    if(data.result){
                        layer.msg(data.message, {
                            icon: 1,
                            time:2000
                        },function(){
                            location.reload();
                        });
                    }else{
                        layer.msg(data.message, {icon: 2});
                    }
                },
                error:function(XMLHttpRequest, textStatus, errorThrown){
                    layer.msg("删除用户信息出错，"+textStatus+"，"+errorThrown, {icon: 2});
                }
            });
        }});
    }

    $(function(){

        $("#addUser").click(function(){
            location.href="<@spring.url '/user/edit/0'/>";
        });

        $("#searchFormButton").click(function () {
            $("#userListTable").bootstrapTable("refresh");
        });

        $("#userBatchDelete").click(function(){
            var selectedArray = GT.BootStrap.checkDataIdsToBootstrap("userListTable");
            if (selectedArray.length==0) {
                layer.msg("您还没有选中要操作的数据项！", {icon: 0});
                return;
            }
//          var selectPlans = $("#annualPlanTable").bootstrapTable("getSelections");
            deleteUser(selectedArray.join(","));
        });
    });
</script>