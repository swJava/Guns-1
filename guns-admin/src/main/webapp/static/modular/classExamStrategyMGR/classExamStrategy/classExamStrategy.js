/**
 * 试卷策略管理初始化
 */
var ClassExamStrategy = {
    id: "ClassExamStrategyTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ClassExamStrategy.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '班级名称', field: 'classCodeName', visible: true, align: 'center', valign: 'middle'},
            {title: '题目数量', field: 'count', visible: true, align: 'center', valign: 'middle'},
            {title: '测试时间， 单位： 分钟', field: 'duration', visible: true, align: 'center', valign: 'middle'},
            {title: '总分数', field: 'fullCredit', visible: true, align: 'center', valign: 'middle'},
            {title: '选择题占比(%)', field: 'selectRatio', visible: true, align: 'center', valign: 'middle'},
            {title: '填空题占比(%)', field: 'fillRatio', visible: true, align: 'center', valign: 'middle'},
            {title: '主观题占比(%)', field: 'subjectRatio', visible: true, align: 'center', valign: 'middle'},
            {title: '是否自动阅卷', field: 'autoMarkingName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ClassExamStrategy.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ClassExamStrategy.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加试卷策略
 */
ClassExamStrategy.openAddClassExamStrategy = function () {
    var index = layer.open({
        type: 2,
        title: '添加试卷策略',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/classExamStrategy/classExamStrategy_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看试卷策略详情
 */
ClassExamStrategy.openClassExamStrategyDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '试卷策略详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/classExamStrategy/classExamStrategy_update/' + ClassExamStrategy.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除试卷策略
 */
ClassExamStrategy.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/classExamStrategy/delete", function (data) {
            Feng.success("删除成功!");
            ClassExamStrategy.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("classExamStrategyId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询试卷策略列表
 */
ClassExamStrategy.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ClassExamStrategy.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ClassExamStrategy.initColumn();
    var table = new BSTable(ClassExamStrategy.id, "/classExamStrategy/list", defaultColunms);
    table.setPaginationType("server");
    ClassExamStrategy.table = table.init();
});
