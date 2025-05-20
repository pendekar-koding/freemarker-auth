<#macro newFormLayout>
    <#assign context=springMacroRequestContext.contextPath/>
    <#import "/spring.ftl" as spring/>
<html>
    <#include "common/_app-style.ftl">
<body class="hold-transition skin-blue fixed sidebar-mini">
<div class="wrapper">
<#--header-->
    <header class="main-header">
    <#include "navigation/nav-header.ftl">
    </header>

    <#--sideBar-->
    <aside class="main-sidebar">
        <#include "navigation/side-bar.ftl">
    </aside>
<#--mainContent-->
    <div class="content-wrapper">

        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                ${pageTitle} <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/index"><i class="fa fa-home"></i> Home</a> ${springMacroRequestContext.getRequestUri()?replace("/"," > ")}</li>
            </ol>
        </section>

        <section class="content">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h4> Add Form</h4>
                </div>
                <div class="box-body">
                    <form class="form form-horizontal" id="formData">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <#nested/>
                    </form>
                </div>
                <div class="box-footer">
                    <button type="button" id="btnSave" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-floppy-disk"></span> <@spring.message "common.label.btnSave"/></button>
                    &nbsp;&nbsp;&nbsp;
                    <button type="button" id="btnCancel" class="btn btn-warning btn-sm"><span class="glyphicon glyphicon-triangle-left"></span> <@spring.message "common.label.btnCancel"/></button>
                </div>
            </div>
        </section>
    </div>
    <#include "common/_footer.ftl">

</div>
<#include "common/_app-script.ftl">
<script type="text/javascript">
     let currLang = "${.locale}";
</script>
</body>
</html>
</#macro>