(function ($) {
    jsAuthorDtList = function (dataTableJsonURL, canEdit, canDelete, lang) {
        let table;
        $(document).ready(function () {
            if (currLang === "en") {
                table = $('#dtPublisher').dataTable({
                    responsive: true,
                    "language": {
                        "aria": {
                            "sortAscending": ": activate to sort column ascending",
                            "sortDescending": ": activate to sort column descending"
                        },
                        "emptyTable": "No data available in table",
                        "info": "Showing _START_ to _END_ of _TOTAL_ records",
                        "infoEmpty": "No records found",
                        "infoFiltered": "(filtered1 from _MAX_ total records)",
                        "search": "_INPUT_",
                        "searchPlaceholder": "Search...",
                        "lengthMenu": "_MENU_",
                        "zeroRecords": "No matching records found",
                        "paginate": {
                            "previous": "Prev",
                            "next": "Next",
                            "last": "Last",
                            "first": "First"
                        }
                    },
                    "order": [
                        [0, "asc"]
                    ], // set first column as a default sort by asc
                    bProcessing: true,
                    bServerSide: true,
                    bStateSave: true, // to save state in cookie
                    iCookieDuration: 60, // cookie will save for about 60 seconds
                    sAjaxSource: dataTableJsonURL,
                    iDisplayLength: 10,
                    aoColumns: [{
                        "mData": "id",
                        "sName": "id",
                        "bSortable": true,
                        "visible": false,
                        "sTitle": "id"
                    },{
                        "mData": "publisherName",
                        "sName": "publisherName",
                        "bSortable": true,
                        "sTitle": "Publisher Name"
                    },{
                        "mData": "address",
                        "sName": "address",
                        "bSortable": true,
                        "sTitle": "Address"
                    }, {
                        "mData": "email",
                        "sName": "email",
                        "bSortable": true,
                        "sTitle": "Email"
                    },{
                        "mData": "id",
                        "sClass": "text-center",
                        "sName": "id",
                        "bSortable": false,
                        "sTitle": "Action",
                        "sWidth": "12%",
                        "bVisible": true,
                        "mRender": function (data, type, full) {
                            return generateDataTablesFormControlNoInfo(data, canEdit, canDelete);
                        }
                    }]
                });
                table.find('.group-checkable').change(function () {
                    let set = jQuery(this).attr("data-set");
                    let checked = jQuery(this).is(":checked");
                    jQuery(set).each(function () {
                        if (checked) {
                            $(this).prop("checked", true);
                            $(this).parents('tr').addClass("active");
                        } else {
                            $(this).prop("checked", false);
                            $(this).parents('tr').removeClass("active");
                        }
                    });
                });

                table.on('change', 'tbody tr .checkboxes', function () {
                    $(this).parents('tr').toggleClass("active");
                })
            } else {
                table = $('#dtPublisher').dataTable({
                    "language": {
                        "aria": {
                            "sortAscending": ": activate to sort column ascending",
                            "sortDescending": ": activate to sort column descending"
                        },
                        "emptyTable": "Data tidak tersedia",
                        "info": "Menampilkan _START_ sampai _END_ dari _TOTAL_ data",
                        "infoEmpty": "Data tidak ditemukan",
                        "infoFiltered": "(terpilih dari _MAX_ total data)",
                        "search": "_INPUT_",
                        "searchPlaceholder": "Cari...",
                        "lengthMenu": "_MENU_",
                        "zeroRecords": "Data tidak ditemukan",
                        "paginate": {
                            "previous": "Prev",
                            "next": "Next",
                            "last": "Last",
                            "first": "First"
                        }
                    },
                    "order": [
                        [0, "asc"]
                    ], // set first column as a default sort by asc
                    bProcessing: true,
                    bServerSide: true,
                    bStateSave: true, // to save state in cookie
                    iCookieDuration: 60, // cookie will save for about 60 seconds
                    sAjaxSource: dataTableJsonURL,
                    iDisplayLength: 10,
                    aoColumns: [{
                        "mData": "publisherName",
                        "sName": "publisherName",
                        "bSortable": true,
                        "sTitle": "Nama Penulis"
                    },{
                        "mData": "address",
                        "sName": "address",
                        "bSortable": true,
                        "sTitle": "Alamat"
                    },{
                        "mData": "email",
                        "sName": "email",
                        "bSortable": true,
                        "sTitle": "Email"
                    }, {
                        "mData": "id",
                        "sClass": "text-center",
                        "sName": "id",
                        "bSortable": false,
                        "sTitle": "Action",
                        "sWidth": "12%",
                        "bVisible": true,
                        "mRender": function (data, type, full) {
                            return generateDataTablesFormControlNoInfo(data, canEdit, canDelete);
                        }
                    }]
                });

                table.on('dblclick','tr',function () {
                    let data = $(this)[0].lastChild;
                    if(canEdit) {
                        window.location = "edit?id=" + $(data).find("input[type='hidden']").val();
                    }
                });

                table.find('.group-checkable').change(function () {
                    let set = jQuery(this).attr("data-set");
                    let checked = jQuery(this).is(":checked");
                    jQuery(set).each(function () {
                        if (checked) {
                            $(this).prop("checked", true);
                            $(this).parents('tr').addClass("active");
                        } else {
                            $(this).prop("checked", false);
                            $(this).parents('tr').removeClass("active");
                        }
                    });
                });

                table.on('change', 'tbody tr .checkboxes', function () {
                    $(this).parents('tr').toggleClass("active");
                })
            }
        });
    }
})(jQuery);


$(document).ready(function () {
    jsAuthorDtList(context + "/store/publisher/getDataList", true, true);
});
