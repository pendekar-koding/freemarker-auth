<#macro defaultLayout>
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
        <#nested/>
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