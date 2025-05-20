<#macro listLayout>
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
            <div class="box">
                <div class="box-header">
                    <div class="page-list-control row">
                        <div class="col-md-6"></div>
                        <div class="col-md-6 text-right">
                            <a href="new" class="btn btn-primary btn-sm"><span class="glyphicon glyphicon-plus-sign"></span> <@spring.message "common.label.button.AddNew"/></a>
                        </div>
                    </div>
                </div>
                <div class="box-body">
                    <#nested/>
                </div>
            </div>
        </section>
    </div>

    <#include "common/_footer.ftl">

</div>

<#include "common/_app-script.ftl">
<script type="text/javascript">
    let currLang = "${.locale}";
    let context = "${context}";
</script>
</body>
</html>
</#macro>