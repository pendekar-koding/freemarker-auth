<#include "../../layout/admin_lte-list-layout.ftl">
<#import "../../template/crud_action_component.ftl" as formComponent>

<@listLayout>
    <div class="dataTables_wrapper no-footer" style="margin:0 auto">
        <@formComponent.dataTableComponent "dtAuthor"/>
    </div>
</@listLayout>

<#include "../../template/_datatables_script.ftl"/>
<script type="text/javascript" src="${springMacroRequestContext.contextPath}/assets/js/pages/store/author/author_dtList.js"></script>