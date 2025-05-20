$(function () {

    console.log(locale);
    //change Language
    $("#lang").on("change", function () {
        let param = $("#changeLangForm").serialize();
        $.post("/changeLang",param, function (jsonData) {
            window.location.reload();
        })
    });

    $("#changeLang").click(function () {
        let param = $("#changeLangForm").serialize();
        $.post("/changeLang",param, function (jsonData) {
            window.location.reload();
        })
    });


    $("#btnCancel").click(function () {
        window.location.href = "list";
    });

    $("#btnSave").click(function () {
        removeDotFromTextField("formData");
        let bVal = validateFormRequiredFields($("#formData"));
        if (bVal) {
            let paramData = $("#formData").serialize();
            $.ajax({
                method: "POST",
                url: "add",
                data: paramData
            }).done(function (res) {
                callSweetAlert(res, "list");
            }).error(function (data) {
                $("#btnSave").button('reset');
                callSweetAlertOnlyWithHtml(data.status, "error", data.responseJSON.error);
            });
        }
    });

    $("#btnUpdate").click(function () {
        removeDotFromTextField("formEditData");
        if(validateFormRequiredFields($("#formEditData")) === true){
            let paramData = $("#formEditData").serialize();
            $.ajax({
                method: "POST",
                url: "update",
                data: paramData
            }).done(function (res) {
                callSweetAlert(res, "list");
            }).error(function (data) {
                $("#btnUpdate").button('reset');
                callSweetAlertOnlyWithHtml(data.status, "error", data.responseJSON.error);
            });
        }
    });


    $(".info-box").click(function () {
        window.location = $(this).find("input[type='hidden']").val();
    });


    //variatif styling
    //iCheck for checkbox and radio inputs
    $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
        checkboxClass: 'icheckbox_minimal-blue',
        radioClass   : 'iradio_minimal-blue'
    });

    $('input[type="text"]').on("blur", function () {
        if($(this).hasClass("unique")){
            validateUniqueKey($(this));
        }
    });


    //cheak unique


});

function removeDotFromTextField(formId) {
    $("#" + formId).find("input[type=text]").each(function (id, el) {
        if ($(el).hasClass('number')) {
            $(el).val($(el).val().replace(/[,.]/g, ''));
        }
        if ($(el).hasClass('date')) {
            $(el).val($(el).val().replace(/Jan/g, 'Jan'));
            $(el).val($(el).val().replace(/Feb/g, 'Feb'));
            $(el).val($(el).val().replace(/Mar/g, 'Mar'));
            $(el).val($(el).val().replace(/Apr/g, 'Apr'));
            $(el).val($(el).val().replace(/Mei/g, 'May'));
            $(el).val($(el).val().replace(/Jun/g, 'Jun'));
            $(el).val($(el).val().replace(/Jul/g, 'Jul'));
            $(el).val($(el).val().replace(/Agu/g, 'Aug'));
            $(el).val($(el).val().replace(/Sep/g, 'Sep'));
            $(el).val($(el).val().replace(/Okt/g, 'Oct'));
            $(el).val($(el).val().replace(/Nov/g, 'Nov'));
            $(el).val($(el).val().replace(/Des/g, 'Dec'));
        }
    })
}
function validateUniqueKey(elForm) {
        let param = elForm.attr("name") + "=" + elForm.val();
        $.get("is_exist?" + param, function (result) {
            if(result.result){
                elForm.val("");
                elForm.addClass("error");
                callSweetAlertOnlyWithHtml("Error","error","Please enter other value");
                return false;
            }
        })

}
function validateFormRequiredFields(elForm) {
    let bVal = true;
    elForm.find('.required-field').each(function(id,dtx){
        if($(dtx).val() === ""){
            //callSweetAlertOnlyWithHtml("Error","error","Fields required");
            $(dtx).addClass("error");
            $(dtx).attr('placeholder','this field is required');
            bVal = false;
        }else{
            $(dtx).removeClass("error");
        }
    });

    return bVal;
}

/*------------------------------dataTable control and attribute--------------------------------------------------*/

function generateDataTablesFormControlNoInfo(id, canEdit, canDelete) {
    let btnEdit = "<a title='Click to edit data' href='edit?id=" + id + "' class='btn btn-circle blue btn-sm'><i class='fa fa-pencil'></i></a> ";
    let btnDelete = "<a title='Click to delete data'  onclick='deleteData(" + id + ")' href='#' class='btn btn-circle red btn-sm btnDelete'><i class='fa fa-trash'></i></a> ";
    let action = "<input type='hidden' value='" + id + "'>";
    if (canEdit) {
        action += btnEdit
    }
    if (canDelete) {
        action += btnDelete;
    }

    return action;
}

function destroyDataTables(dataTablesId) {
    $('#' + dataTablesId).DataTable().destroy();
}

function deleteData(idToDelete) {
    let postDeleteData = $("#changeLangForm").serialize() + "&id=" +idToDelete;
    swal({
        title: "Are you sure?",
        text: "Once deleted, you will not be able to recover this data!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                $.post("delete",postDeleteData, function (rJson) {
                    callSweetAlert(rJson)
                });
            }
        });
}

/*-----------------------------SWEET ALERT AREA ---------------------------------------------------------------*/
/**
 * @param title string title to display
 * @param icon "warning","error","info", "success"
 * @param htmlMessage string message to display on html format
 * */
function callSweetAlertOnlyWithHtml(title, icon, htmlMessage) {
    swal({
        title: title,
        text: htmlMessage,
        icon: icon,
        button: "Ok",
        closeOnClickOutside: false,
        html: true
    });
}


/**
 * @param ajaxResult string title to display
 * @param urlBack string return url
 * */
function callSweetAlert(ajaxResult, urlBack) {
    if (ajaxResult.result === 'Error') {
        let message = ajaxResult.cause ? ajaxResult.cause.message : "Error";
        swal({
            title: "Error",
            text: message,
            icon: "error",
            button: "Ok",
            closeOnClickOutside: false
        });
    } else {
        swal({
            title: ajaxResult.result,
            text: ajaxResult.message,
            icon: "success",
            button: "Ok",
            closeOnClickOutside: false,
        }).then((value) => {
            if (value) {
                if (urlBack !== null && urlBack !== "" && (typeof urlBack !== "undefined")) {
                    window.location = urlBack;
                } else {
                    window.location = "list";
                }
            }
        });
    }
}

/*-----------------------------END OF SWEET ALERT AREA ----------------------------------------------------------*/

function menuHover() {
    let path = window.location.pathname;
    let content = $(".main-sidebar a");
    let submenu = $(".sub-menu");
    if (path.startsWith("/admin/system/")) {
        path = decodeURIComponent(path).split("/")[2];
    }else if (path.startsWith("/admin/")) {
        path = decodeURIComponent(path).split("/")[2];
    } else {
        path = decodeURIComponent(path).split("/")[1];
    }
    content.each(function () {
        let href = $(this).attr('href');
        if (href.startsWith("/admin/system/")) {
            href = href.split("/")[2];
        } else if (href.startsWith("/admin/")) {
            href = href.split("/")[2];
        } else {
            href = href.split("/")[1];
        }
        if (path !== undefined && (path === href)) {
            $(this).closest('li').addClass('active open');
            $(this).closest('a').append('<span class="selected"></span>');
            $(this).closest('a').parents('li').parents('li').addClass('active open');
            $(this).closest('a').parents('li').parents('li').find("span").addClass("open");
        } else if (path === "index") {
            $("#dashboard").addClass("active");
        }
    });
}

function back() {
    window.history.back();
}

function message(message){
    return message
}


/*masih ga optimal berfungsinya... :(*/
function printPage() {
    let elToPrint = $(document).find(".printable-area");
    console.log(elToPrint);
    if(elToPrint.length > 0){
        let printContents = $(elToPrint).html();
        let originalContents = $(document);
        document.body.innerHTML = printContents;
        window.print();
        document.body.innerHTML = originalContents;
    }else{
        callSweetAlertOnlyWithHtml("Error","error","This page cannot be printed");
    }

}