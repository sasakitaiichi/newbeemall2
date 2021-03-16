$(function () {
    $("#jqGrid").jqGrid({
        // url: '/admin/goods/upload',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'insertId', index: 'insertId', width: 120},
            {label: 'process', name: 'updateId', index: 'updateId', width: 120}
        ],
        height: 560,
        rowNum: 10,
        rowList: [10, 20, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.list",
            page: "data.currPage",
            total: "data.totalPage",
            records: "data.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order",
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });

    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });

    function reload() {
        var page = $("#jqGrid").jqGrid('getGridParam', 'page');
        $("#jqGrid").jqGrid('setGridParam', {
            page: page
        }).trigger("reloadGrid");
    }

    new AjaxUpload('#uploadFile', {
        action: '/admin/goods/upload',
        name: 'file',
        autoSubmit: true,
        responseType: "json",
        onSubmit: function (file, extension) {
            if (!(extension && /^(jpg|jpeg|png|gif|csv)$/.test(extension.toLowerCase()))) {
                alert('只支持jpg、png、gif、csv格式的文件！');
                return false;
            }
        },
        onComplete: function (file, r) {
            if (r != null && r.resultCode == 200) {

                var el;
                var insert = [];
                var update = [];
                var goodsIds = r.data;
                var length = r.data.length;

                for (var i = 0; i < length; i++) {
                    // console.log(goodsIds[i])
                    if (goodsIds[i].idFlag == true) {
                       update = '<tr role="row" id="1" tabIndex="-1" className="jqgrow ui-row-ltr">'
                           +'<td role = \"gridcell\"style = \"text-align:center;width: 45px;\"className = \"jqgrid-multibox\"aria - describedby = \"jqGrid_cb\" > '
                           +'<inputrole = \"checkbox\"type = \"checkbox\"id = \"jqg_jqGrid_12\"className = \"cbox checkbox\"name = \"jqg_jqGrid_12\" >'
                           +'</td>'
                           +'<td role=\"idList\" style=\"\" title=' + goodsIds[i].id + 'aria-describedby=\"jqGrid_redirectUrl\">'
                           + goodsIds[i].id
                           +'<td role=\"updateList\" style=\"\" title=' + "update" + 'aria-describedby=\"jqGrid_redirectUrl\">'
                           + "update"
                           +'</td>'
                           + '</tr>'
                    }
                    if (goodsIds[i].idFlag == false) {
                        insert = '<tr role="row" id="2" tabIndex="-1" className="jqgrow ui-row-ltr">'
                            +'<td role = \"gridcell\"style = \"text-align:center;width: 45px;\"className = \"jqgrid-multibox\"aria - describedby = \"jqGrid_cb\" > '
                            +'<inputrole = \"checkbox\"type = \"checkbox\"id = \"jqg_jqGrid_12\"className = \"cbox checkbox\"name = \"jqg_jqGrid_12\" >'
                            +'</td>'
                            +'<td role=\"idList\" style=\"\" title=' + goodsIds[i].id + 'aria-describedby=\"jqGrid_redirectUrl\">'
                            + goodsIds[i].id
                            +'<td role=\"updateList\" style=\"\" title=' + "insert" + 'aria-describedby=\"jqGrid_redirectUrl\">'
                            + "insert"
                            +'</td>'
                            + '</tr>'
                    }
                    el = el+update+insert;
                }
                $('#jqGrid').find('tbody').html(el);




                return false;
            } else {
                alert("error");
            }
        }
    })
})