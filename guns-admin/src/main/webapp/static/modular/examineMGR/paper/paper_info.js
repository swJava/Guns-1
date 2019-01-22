/**
 * 试卷管理初始化
 */
var PaperDlg = {
    currPaper: $('#code').val(),
    ztreeInstance: null,
    UnSelectQuestion: {
        id: "UnSelectQuestionTable",	//表格id
        seItem: null,		            //选中的条目
        seItems: new Array(),		            //选中的条目
        table: null,
        layerIndex: -1
    },
    SelectedQuestion: {
        id: "SelectedQuestionTable",	//表格id
        seItem: null,		            //选中的条目
        seItems: new Array(),		            //选中的条目
        table: null,
        layerIndex: -1
    }
};

/**
 * 初始化表格的列
 */
PaperDlg.SelectedQuestion.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
        {title: '试题题目', field: 'question', visible: true, align: 'center', valign: 'middle'},
        {title: '试题类型', field: 'typeName', visible: true, align: 'center', valign: 'middle'},
        {title: '出题人', field: 'teacherName', visible: true, align: 'center', valign: 'middle'}
    ];
};
PaperDlg.UnSelectQuestion.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
        {title: '试题题目', field: 'question', visible: true, align: 'center', valign: 'middle'},
        {title: '试题类型', field: 'typeName', visible: true, align: 'center', valign: 'middle'},
        {title: '出题人', field: 'teacherName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
PaperDlg.UnSelectQuestion.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PaperDlg.UnSelectQuestion.seItems = selected.slice(0);
        return true;
    }
};
PaperDlg.SelectedQuestion.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PaperDlg.SelectedQuestion.seItems = selected.slice(0);
        return true;
    }
};

PaperDlg.join = function(){
    if (this.UnSelectQuestion.check()) {
        var questionIds = new Array();
        $.each(this.UnSelectQuestion.seItems, function(idx, eo){
            questionIds.push(eo.code);
        });

        var ajax = new $ax(Feng.ctxPath + "/examine/paper/question/join", function (data) {
            Feng.success("加入成功!");
            Collector.SelectedQuestion.table.refresh();
            Collector.UnSelectQuestion.table.refresh();
        }, function (data) {
            Feng.error("加入失败!" + data.responseJSON.message + "!");
        });
        ajax.set("questions",questionIds.join(','));
        ajax.set("paper",this.currPaper);
        ajax.start();
    }
}

PaperDlg.remove = function(){
    if (this.SelectedQuestion.check()) {
        var ajax = new $ax(Feng.ctxPath + "/examine/paper/question/remove", function (data) {
            Feng.success("移除成功!");
            Collector.SelectedQuestion.table.refresh();
            Collector.UnSelectQuestion.table.refresh();
        }, function (data) {
            Feng.error("移除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("contents",this.SelectedQuestion.seItem.code);
        ajax.set("paper",this.currPaper);
        ajax.start();
    }
}

/**
 * 关闭此对话框
 */
PaperDlg.close = function() {
    parent.layer.close(window.parent.Paper.layerIndex);
}

$(function () {
    var displayColumns = PaperDlg.UnSelectQuestion.initColumn();
    var table = new BSTable(PaperDlg.UnSelectQuestion.id, "/examine/paper/question/list?excludePaper=" + $('#code').val(), displayColumns);
    table.setPaginationType("server");
    table.setLoadSuccessCallback(function(){
        var currHeight = $('.btn-move').parent().height();
        var height = $('.btn-move').parent().parent().height();

        if (height > currHeight)
            $('.btn-move').parent().css({
                height: height + 'px',
                marginTop: (height-25)/2 + 'px'
            });
    });
    PaperDlg.UnSelectQuestion.table = table.init();

    displayColumns = PaperDlg.SelectedQuestion.initColumn();
    var table = new BSTable(PaperDlg.SelectedQuestion.id, "/examine/paper/question/list?includePaper=" + $('#code').val(), displayColumns);
    table.setPaginationType("server");

    table.setLoadSuccessCallback(function(){
        var currHeight = $('.btn-move').parent().height();
        var height = $('.btn-move').parent().parent().height();

        if (height > currHeight)
            $('.btn-move').parent().css({
                height: height + 'px',
                marginTop: (height-25)/2 + 'px'
            });
    });
    PaperDlg.SelectedQuestion.table = table.init();



});
