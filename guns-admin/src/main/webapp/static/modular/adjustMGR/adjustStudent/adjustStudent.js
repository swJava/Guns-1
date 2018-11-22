/**
 * 调课管理管理初始化
 */
var AdjustStudent = {
    id: "AdjustStudentTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
AdjustStudent.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '申请用户名', field: 'userName', visible: true, align: 'center', valign: 'middle'},
            {title: '学生编码（学号）', field: 'studentName', visible: true, align: 'center', valign: 'middle'},
            {title: '当前班级', field: 'className', visible: true, align: 'center', valign: 'middle'},
            {title: '调入目标编码', field: 'targetName', visible: true, align: 'center', valign: 'middle'},
            {title: '类型', field: 'typeName', visible: true, align: 'center', valign: 'middle'},
            {title: '流程状态', field: 'workStatusName', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'statusName', visible: true, align: 'center', valign: 'middle' },
            {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
            {title: '申请时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '审核时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
AdjustStudent.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        AdjustStudent.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加调课管理
 */
AdjustStudent.openAddAdjustStudent = function () {
    var index = layer.open({
        type: 2,
        title: '添加调课管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/adjustStudent/adjustStudent_add'
    });
    this.layerIndex = index;
};

/**
 * 審核通過
 */
AdjustStudent.openAdjustStudentDetail = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath  + '/adjustStudent/pass/' + AdjustStudent.seItem.id , function (data) {
            Feng.success("编辑成功!");
            AdjustStudent.table.refresh();
        }, function (data) {
            Feng.error("编辑失败!" + data.responseJSON.message + "!");
        });
        ajax.set("adjustStudentId",this.seItem.id);
        ajax.start();
    }
};
/**
 * 審核打回
 */
AdjustStudent.openAdjustStudentDetail_not = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath  + '/adjustStudent/pass_not/' + AdjustStudent.seItem.id , function (data) {
            Feng.success("编辑成功!");
            AdjustStudent.table.refresh();
        }, function (data) {
            Feng.error("编辑失败!" + data.responseJSON.message + "!");
        });
        ajax.set("adjustStudentId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 删除调课管理
 */
AdjustStudent.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/adjustStudent/delete", function (data) {
            Feng.success("删除成功!");
            AdjustStudent.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("adjustStudentId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询调课管理列表
 */
AdjustStudent.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    AdjustStudent.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = AdjustStudent.initColumn();
    var table = new BSTable(AdjustStudent.id, "/adjustStudent/list", defaultColunms);
    table.setPaginationType("server");
    AdjustStudent.table = table.init();
});
