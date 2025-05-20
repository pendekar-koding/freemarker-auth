<div class="row">
    <div class="col-md-12">
        <div class="form-group required">
            <label class="label-control col-md-3" for="configName"><@spring.message "page.applicationConfig.label.configName"/></label>
            <div class="col-md-6">
                <input type="text" class="form-control required-field unique" id="configName" name="configName"
                       value="${model.configName!""}">
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <div class="form-group required">
            <label class="label-control col-md-3" for="configValue"><@spring.message "page.applicationConfig.label.configValue"/></label>
            <div class="col-md-6">
                <input type="text" class="form-control required-field" id="configValue" name="configValue"
                       value="${model.configValue!""}">
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <div class="form-group">
            <label class="label-control col-md-3" for="description"><@spring.message "page.common.label.description"/></label>
            <div class="col-md-6">
                <textarea class="form-control" id="description" name="description">${model.description!""}</textarea>
            </div>
        </div>
    </div>
</div>