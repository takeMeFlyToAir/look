<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>

    <#--<!-- bootstrap 3.0.2 &ndash;&gt;
    <link href="<@spring.url '/res/backstage/css/bootstrap.css'/>" rel="stylesheet" type="text/css"/>
    <!-- font Awesome &ndash;&gt;
    <link href="<@spring.url '/res/backstage/css/font-awesome.min.css'/>" rel="stylesheet" type="text/css"/>
    <!-- Ionicons &ndash;&gt;
    <link href="<@spring.url '/res/backstage/css/ionicons.min.css'/>" rel="stylesheet" type="text/css"/>
    <!-- colorpicker &ndash;&gt;
    <link href="<@spring.url '/res/backstage/css/bootstrap-colorpicker.min.css'/>" rel="stylesheet" type="text/css"/>
    <!-- artDialog &ndash;&gt;
    <link href="<@spring.url '/res/backstage/css/ui-dialog.css'/>" rel="stylesheet" type="text/css"/>
    <!-- Theme style &ndash;&gt;
    <link href="<@spring.url '/res/backstage/css/AdminLTE.css'/>" rel="stylesheet" type="text/css"/>
    <!-- bootstrap-chosen &ndash;&gt;
    <link href="<@spring.url '/res/backstage/css/bootstrap-chosen.min.css'/>" rel="stylesheet" type="text/css"/>
    <!-- DatePicker &ndash;&gt;
    <link href="<@spring.url '/res/backstage/css/datepicker3.css'/>" rel="stylesheet" type="text/css"/>
    <!-- zTreeStyle.css &ndash;&gt;
    <link href="<@spring.url '/res/backstage/css/zTreeStyle.css'/>" rel="stylesheet" type="text/css"/>
    <!--custom CSS&ndash;&gt;
    <link href="<@spring.url '/res/backstage/css/main.css'/>" rel="stylesheet" type="text/css"/>-->
    <link href="<@spring.url '/wro/all.css'/>" rel="stylesheet" type="text/css"/>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body class="skin-blue">
<input type="hidden" id="v5cms_context_path" value="<@spring.url ''/>"/>
<!-- header logo: style can be found in header.less -->
<header class="header">
<a href="index" class="logo">
    <!-- Add the class icon to your logo image or logo icon to add the margining -->
</a>
<!-- Header Navbar: style can be found in header.less -->
<nav class="navbar navbar-static-top" role="navigation">
<div class="navbar-right">

<ul class="nav navbar-nav">

<!-- User Account: style can be found in dropdown.less -->
<li class="dropdown user user-menu">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
        <i class="glyphicon glyphicon-user"></i>
        <span>你好，${currentUser.account}<i class="caret"></i></span>
    </a>
    <ul class="dropdown-menu">
        <!-- User image -->
        <li class="user-header bg-light-blue">
            <img src="<@spring.url '/res/backstage/images/avatar5.png'/>" class="img-circle" alt="User Image"/>

            <p>-${currentUser.account}
                <#--<small>${managerKey.email}</small>-->
            </p>
        </li>
       <#-- Menu Body
        <li class="user-body">
            <div class="col-xs-4 text-center">
                <a href="#">Followers</a>
            </div>
            <div class="col-xs-4 text-center">
                <a href="#">Sales</a>
            </div>
            <div class="col-xs-4 text-center">
                <a href="#">Friends</a>
            </div>
        </li>-->
        <!-- Menu Footer-->
        <li class="user-footer">
            <div class="pull-left">
                <a href="#" class="btn btn-default btn-flat">设置</a>
            </div>
            <div class="pull-right">
                <a href="<@spring.url '/logout'/>" class="btn btn-default btn-flat">退出</a>
            </div>
        </li>
    </ul>
</li>
</ul>
</div>
</nav>
</header>
<div class="wrapper row-offcanvas row-offcanvas-left">
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="left-side sidebar-offcanvas">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <ul class="sidebar-menu">
                <li class="treeview" id="user_auth">
                    <a href="javascript:;">
                        <i class="fa fa-user"></i> <span>用户</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="<@spring.url '/user/list'/>"><i class="fa fa-angle-double-right"></i> 用户管理</a></li>
                    </ul>
                </li>
                <li class="treeview" id="user_auth">
                    <a href="javascript:;">
                        <i class="fa fa-user"></i> <span>客户</span>
                        <i class="fa fa-angle-left pull-right"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="<@spring.url '/customer/list'/>"><i class="fa fa-angle-double-right"></i>客户管理</a></li>
                    </ul>
                </li>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>