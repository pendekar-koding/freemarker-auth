<#include "layout/admin_lte-layout.ftl">
<@defaultLayout>
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Dashboard
            <small></small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active">${springMacroRequestContext.getRequestUri()?replace("/","")}</li>
        </ol>
    </section>
    
    <!-- Main content -->
    <section class="content">
        <div class="page-content">
            <p><@spring.message "common.label.welcome"/> ${authentication.name}</p>
            <p>Locale : ${.locale}</p>
        </div>
    </section>
    <!-- /.content -->
</@defaultLayout>