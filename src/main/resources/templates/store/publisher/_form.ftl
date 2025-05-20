<div class="row">
    <div class="col-md-12">
        <div class="form-group required">
            <label class="label-control col-md-3" for="publisherName"><@spring.message "page.publisher.label.publisherName"/></label>
            <div class="col-md-6">
                <input type="text" class="form-control required-field" id="publisherName" name="publisherName"
                       value="${model.publisherName!""}">
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <div class="form-group required">
            <label class="label-control col-md-3" for="email"><@spring.message "page.publisher.label.email"/></label>
            <div class="col-md-6">
                <input type="email" class="form-control required-field" id="email" name="email"
                       value="${model.email!""}">
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <div class="form-group">
            <label class="label-control col-md-3" for="address"><@spring.message "page.publisher.label.address"/></label>
            <div class="col-md-6">
                <textarea class="form-control" id="address" name="address">${model.address!""}</textarea>
            </div>
        </div>
    </div>
</div>