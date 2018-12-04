/**
 * 题库归档管理初始化
 */
var QuestionWeight = {
    id: "QuestionWeightTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
QuestionWeight.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '标示', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '试题编码', field: 'qcode', visible: true, align: 'center', valign: 'middle'},
            {title: '年级', field: 'grade', visible: true, align: 'center', valign: 'middle'},
            {title: '对应班次', field: 'ability', visible: true, align: 'center', valign: 'middle'},
            {title: '所含分值', field: 'score', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
QuestionWeight.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        QuestionWeight.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加题库归档
 */
QuestionWeight.openAddQuestionWeight = function () {
    var index = layer.open({
        type: 2,
        title: '添加题库归档',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/questionWeight/questionWeight_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看题库归档详情
 */
QuestionWeight.openQuestionWeightDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '题库归档详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/questionWeight/questionWeight_update/' + QuestionWeight.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除题库归档
 */
QuestionWeight.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/questionWeight/delete", function (data) {
            Feng.success("删除成功!");
            QuestionWeight.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("questionWeightId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询题库归档列表
 */
QuestionWeight.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    QuestionWeight.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = QuestionWeight.initColumn();
    var table = new BSTable(QuestionWeight.id, "/questionWeight/list", defaultColunms);
    table.setPaginationType("client");
    QuestionWeight.table = table.init();
});
