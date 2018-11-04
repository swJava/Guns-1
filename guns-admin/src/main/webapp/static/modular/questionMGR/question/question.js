/**
 * 入学诊断管理初始化
 */
var Question = {
    id: "QuestionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Question.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '试题题目', field: 'question', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    var imgUrl;
                    if(row.question != null && row.question != ''){
                        imgUrl = '<img alt="image" class="img-circle" src="/kaptcha/'+ row.question +'" width="64px" height="64px">';
                    }else {
                        imgUrl = '<img alt="image" class="img-circle" src="/static/img/swiming.png" width="64px" height="64px">';
                    }
                    return imgUrl;
                }
            },
            {title: '试题编码', field: 'code', visible: true, align: 'center', valign: 'middle'},
            {title: '试题类型', field: 'typeName', visible: true, align: 'center', valign: 'middle'},
            {title: '试题学科', field: 'subjectName', visible: true, align: 'center', valign: 'middle'},
            {title: '年级', field: 'gradeName', visible: true, align: 'center', valign: 'middle'},
            {title: '分值', field: 'score', visible: true, align: 'center', valign: 'middle'},
            {title: '答案', field: 'answer', visible: true, align: 'center', valign: 'middle'},
            {title: '参考答案', field: 'expactAnswer', visible: true, align: 'center', valign: 'middle'},
            {title: '排序号', field: 'sort', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'statusName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Question.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Question.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加入学诊断
 */
Question.openAddQuestion = function () {
    var index = layer.open({
        type: 2,
        title: '添加入学诊断',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/question/question_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看入学诊断详情
 */
Question.openQuestionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '入学诊断详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/question/question_update/' + Question.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除入学诊断
 */
Question.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/question/delete", function (data) {
            Feng.success("删除成功!");
            Question.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("questionId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询入学诊断列表
 */
Question.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Question.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Question.initColumn();
    var table = new BSTable(Question.id, "/question/list", defaultColunms);
    table.setPaginationType("server");
    Question.table = table.init();
});
