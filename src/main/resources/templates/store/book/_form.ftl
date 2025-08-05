<div class="row">
    <div class="col-md-12">
        <div class="form-group required">
            <label class="label-control col-md-3" for="authorName"><@spring.message "page.author.label.authorName"/></label>
            <div class="col-md-6">
                <input type="text" class="form-control required-field" id="authorName" name="authorName"
                       value="${model.authorName!""}">
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <div class="form-group">
            <label class="label-control col-md-3" for="address"><@spring.message "page.author.label.address"/></label>
            <div class="col-md-6">
                <textarea class="form-control" id="address" name="address">${model.address!""}</textarea>
            </div>
        </div>
    </div>
</div>