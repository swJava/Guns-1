/**
 * 考试管理管理初始化
 */
var AnswerPaper = {
    id: "AnswerPaperTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
AnswerPaper.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '试卷名称', field: 'examName', visible: true, align: 'center', valign: 'middle'},
            {title: '年级', field: 'gradeName', visible: true, align: 'center', valign: 'middle'},
            {title: '答题学员', field: 'studentCode', visible: true, align: 'center', valign: 'middle'},
            {title: '开始时间', field: 'beginDate', visible: true, align: 'center', valign: 'middle'},
            {title: '结束时间', field: 'endDate', visible: true, align: 'center', valign: 'middle'},
            {title: '总分', field: 'score', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'statusName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
AnswerPaper.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        AnswerPaper.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加考试管理
 */
AnswerPaper.openAddAnswerPaper = function () {
    var index = layer.open({
        type: 2,
        title: '添加考试管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/answerPaper/answerPaper_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看考试管理详情
 */
AnswerPaper.openAnswerPaperDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '考试管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/answerPaper/answerPaper_update/' + AnswerPaper.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除考试管理
 */
AnswerPaper.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/answerPaper/delete", function (data) {
            Feng.success("删除成功!");
            AnswerPaper.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("answerPaperId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询考试管理列表
 */
AnswerPaper.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    AnswerPaper.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = AnswerPaper.initColumn();
    var table = new BSTable(AnswerPaper.id, "/answerPaper/list", defaultColunms);
    table.setPaginationType("server");
    AnswerPaper.table = table.init();
});
