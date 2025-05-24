<#assign context = springMacroRequestContext.contextPath/>
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title><@spring.message "application.name"/> | Log in</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="${context}/assets/bootstrap/css/bootstrap.min.css"/>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${context}/assets/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${context}/assets/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${context}/assets/admin-lte/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="${context}/assets/plugins/iCheck/square/blue.css">

    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->


    <!-- Google Font -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <a href="#"><b><@spring.message "application.name"/></b></a>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <#if (RequestParameters["logout"]??)>
            <div class="alert alert-success">
                <@spring.message "label.logout.success" />
            </div>
        </#if>

        <#if RequestParameters["error"]??>
            <#assign ex = Session.SPRING_SECURITY_LAST_EXCEPTION?if_exists>
            <#if ex?has_content>
                <#if ex.message?contains("User account is not active")>
                    <div class="alert alert-danger">
                        <@spring.message "login.inactive.message" />
                    </div>
                <#else>
                    <div class="alert alert-danger">
                        <@spring.message "login.failed.message" />
                    </div>
                </#if>
            </#if>
        </#if>


        <p class="login-box-msg">
            <@spring.message "form.login.header2"/>
        </p>

        <form action="${context}/validate" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-group has-feedback">
                <input type="text" class="form-control" placeholder="Username" id="username" name="username">
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" placeholder="Password" id="password" name="password">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-8">
                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat"><@spring.message "common.label.btnSignIn"/></button>
                </div>
                <!-- /.col -->
            </div>
        </form>

    </div>
    <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<!-- jQuery 3 -->
<script src="${context}/assets/jquery/jquery.min.js"></script>

<!-- Bootstrap 3.3.7 -->
<script src="${context}/assets/bootstrap/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="${context}/assets/plugins/iCheck/icheck.min.js"></script>
<script>
    $(function () {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' /* optional */
        });
    });
</script>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</body>
</html>
