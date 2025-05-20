<#macro dataTableComponent dataTableId>
<table id="${dataTableId}" class="table table-bordered table-striped dt-responsive" width="100%">
    <thead>
    </thead>
    <tbody>

    </tbody>
</table>
</#macro>


<#macro modalDataTableReference modalId dataTableId>
<div class="modal fade" id="${modalId}" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" style="">
        <div class="modal-content" style="padding:10px">
            <div class="modal-header form-title">
                <p class="text-center ref-title"></p>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"
                        style="margin:3px">&times;
                </button>
            </div>
            <div class="modal-body" id="loadingInfo">
                <@dataTableComponent dataTableId/>
            </div>
        </div>
    </div>
</div>
</#macro>

<#macro modalView modalId>
<div class="modal fade" id="${modalId}" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg" style="">
        <div class="modal-content" style="padding:10px">
            <div class="modal-header form-title">
                <p class="text-center ref-title"></p>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"
                        style="margin:3px">&times;
                </button>
            </div>
            <div class="modal-body" id="viewResult">

            </div>
        </div>
    </div>
</div>
</#macro>