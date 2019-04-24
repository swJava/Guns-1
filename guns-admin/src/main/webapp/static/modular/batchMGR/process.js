/**
 * 批量任务初始化
 */
var Process = {
    id: "ProcessTable",	//表格id
    service: $('#service').val(), // 业务类型
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Process.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'ID', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '批次号', field: 'code', visible: true, align: 'center', valign: 'middle'},
        {title: '业务类型', field: 'serviceName', visible: true, align: 'center', valign: 'middle'},
        {title: '业务描述', field: 'description', visible: true, align: 'center', valign: 'middle'},
        {title: '导入记录数', field: 'importCount', visible: true, align: 'center', valign: 'middle'},
        {title: '处理记录数', field: 'completeCount', visible: true, align: 'center', valign: 'middle'},
        {title: '状态', field: 'workStatusName', visible: true, align: 'center', valign: 'middle'},
        {title: '导入时间', field: 'importDate', visible: true, align: 'center', valign: 'middle'},
        {title: '处理完成时间', field: 'completeDate', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Process.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Process.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加批处理任务
 */
Process.openAddProcess = function () {
    var index = layer.open({
        type: 2,
        title: '新增任务',
        area: ['640px', '480px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/batch/process/add?service=' + Process.service
    });
    layer.full(index);
    this.layerIndex = index;
};

/**
 * 打开查看批处理任务详情
 */
Process.openClassDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '任务处理详情',
            area: ['640px', '480px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/class/class_update/' + Process.seItem.code
        });
        layer.full(index);
        this.layerIndex = index;
    }
};

/**
 * 删除课程管理
 */
Process.deleteProcess = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/batch/process/delete", function (data) {
            Feng.success("删除成功!");
            Process.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("batchCode",this.seItem.code);
        ajax.start();
    }
};


/**
 * 导出批处理详情
 */
Process.exportProcess = function () {
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
 * 打开查看批处理任务详情
 */
Process.openProcessDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '任务处理详情',
            area: ['640px', '480px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/batch/detail/' + Process.seItem.code
        });
        layer.full(index);
        this.layerIndex = index;
    }
};

/**
 * 查询课程管理列表
 */
Process.search = function () {
    var queryData = {};
    queryData['service'] = $("#service").val();
    queryData['beginDate'] = $("#beginDate").val();
    queryData['endDate'] = $("#endDate").val();
    Process.table.refresh({query: queryData});
};


$(function () {

    var defaultColunms = Process.initColumn();
    var table = new BSTable(Process.id, "/batch/process/list", defaultColunms);
    table.setPaginationType("server");
    table.setQueryParamsGetter(function(){
        return {'service': Process.service};
    });
    Process.table = table.init();

    // 日期条件初始化
    laydate.render({elem: '#beginDate'});
    laydate.render({elem: '#endDate'});
});
