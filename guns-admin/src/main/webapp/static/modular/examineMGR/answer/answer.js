/**
 * 入学诊断管理初始化
 */
var Answer = {
    id: "AnswerTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Answer.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '学员编码', field: 'student_code', visible: true, align: 'center', valign: 'middle'},
        {title: '学员名称', field: 'studentName', visible: true, align: 'center', valign: 'middle'},
        {title: '针对年级', field: 'gradeName', visible: true, align: 'center', valign: 'middle'},
        {title: '考试学科', field: 'subjectName', visible: true, align: 'center', valign: 'middle'},
        {title: '所得分数', field: 'score', visible: true, align: 'center', valign: 'middle'},
        {title: '总分值', field: 'total_score', visible: true, align: 'center', valign: 'middle'},
        {title: '状态', field: 'stateName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Answer.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Answer.seItem = selected[0];
        return true;
    }
};

/**
 * 打开查看入学诊断详情
 */
Answer.openDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '答卷详情',
            area: ['640px', '480px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/examine/answer/view/' + Answer.seItem.code
        });
        layer.full(index);
        this.layerIndex = index;
    }
};


/**
 * 查询答卷列表
 */
Answer.search = function () {
    var queryData = {};
    queryData['grade'] = $("#grade").val();
    queryData['subject'] = $("#subject").val();
    queryData['ability'] = $("#ability").val();
    queryData['student'] = $("#student").val();
    queryData['beginScore'] = $("#beginScore").val();
    queryData['endScore'] = $("#endScore").val();
    queryData['status'] = $("#status").val();
    Answer.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Answer.initColumn();
    var table = new BSTable(Answer.id, "/examine/answer/list", defaultColunms);
    table.setPaginationType("server");
    Answer.table = table.init();
});
