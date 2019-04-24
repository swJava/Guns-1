/**
 * 批量任务详情初始化
 */
var ProcessDetail = {
    id: "ProcessDetailTable",	//表格id
    batchCode: $('#batchCode').val(), // 业务类型
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ProcessDetail.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'ID', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '批次号', field: 'batchCode', visible: true, align: 'center', valign: 'middle'},
        {title: '行号', field: 'line', visible: true, align: 'center', valign: 'middle'},
        {title: '处理状态', field: 'workStatusName', visible: true, align: 'center', valign: 'middle'},
        {title: '处理持续时间', field: 'duration', visible: true, align: 'center', valign: 'middle'},
        {title: '导入时间', field: 'importDate', visible: true, align: 'center', valign: 'middle'},
        {title: '处理完毕时间', field: 'completeDate', visible: true, align: 'center', valign: 'middle'},
        {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 导出批处理详情
 */
ProcessDetail.exportProcess = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/batch/export/" + this.seItem.code, function (data) {
            //Feng.success("导出成功!");
            window.location.href = encodeURI(data.message);
        }, function (data) {
            Feng.error("导出失败!" + data.responseJSON.message + "!");
        });

        ajax.start();
    }
};


/**
 * 查询批处理任务详情列表
 */
ProcessDetail.search = function () {
    var queryData = {};
    queryData['batchCode'] = $("#batchCode").val();
    queryData['status'] = $("#status").val();
    ProcessDetail.table.refresh({query: queryData});
};


$(function () {

    var defaultColunms = ProcessDetail.initColumn();
    var table = new BSTable(ProcessDetail.id, "/batch/detail/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParamsGetter(function(){
        return {'batchCode': ProcessDetail.batchCode};
    });
    ProcessDetail.table = table.init();

});
