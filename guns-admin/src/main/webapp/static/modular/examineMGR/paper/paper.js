/**
 * 入学诊断管理初始化
 */
var Paper = {
    id: "PaperTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Paper.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '针对学科', field: 'subjectName', visible: true, align: 'center', valign: 'middle'},
        {title: '题目数量', field: 'count', visible: true, align: 'center', valign: 'middle'},
        {title: '测试时间（分钟）', field: 'examTime', visible: true, align: 'center', valign: 'middle'},
        {title: '总分值', field: 'totalScore', visible: true, align: 'center', valign: 'middle'},
        {title: '出题人', field: 'teacher', visible: true, align: 'center', valign: 'middle'},
        {title: '出题时间', field: 'createDate', visible: true, align: 'center', valign: 'middle'},
    ];
};

/**
 * 检查是否选中
 */
Paper.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Paper.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加入学诊断
 */
Paper.openAddPaper = function () {
    var index = layer.open({
        type: 2,
        title: '添加题目',
        area: ['640px', '480px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/examine/paper/paper_add'
    });
    layer.full(index);
    this.layerIndex = index;
};

/**
 * 打开查看入学诊断详情
 */
Paper.openPaperDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '试卷详情',
            area: ['640px', '480px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/examine/paper/paper_update/' + Paper.seItem.code
        });
        layer.full(index);
        this.layerIndex = index;
    }
};

/**
 * 删除试卷
 */
Paper.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/examine/paper/delete", function (data) {
            Feng.success("删除成功!");
            Paper.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("code",this.seItem.code);
        ajax.start();
    }
};

/**
 * 查询入学诊断列表
 */
Paper.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Paper.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Paper.initColumn();
    var table = new BSTable(Paper.id, "/examine/paper/list", defaultColunms);
    table.setPaginationType("server");
    Paper.table = table.init();
});
