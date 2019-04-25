/**
 * 初始化考试管理详情对话框
 */
var AnswerPaperInfoDlg = {
    answerPaperInfoData : {}
};

/**
 * 清除数据
 */
AnswerPaperInfoDlg.clearData = function() {
    this.answerPaperInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AnswerPaperInfoDlg.set = function(key, val) {
    this.answerPaperInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AnswerPaperInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AnswerPaperInfoDlg.close = function() {
    parent.layer.close(window.parent.AnswerPaper.layerIndex);
}

/**
 * 收集数据
 */
AnswerPaperInfoDlg.collectData = function() {
    this
    .set('id')
    .set('examCode')
    .set('studentCode')
    .set('beginDate')
    .set('endDate')
    .set('score')
    .set('remark')
    .set('grade')
    .set('status');
}

/**
 * 提交添加
 */
AnswerPaperInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/answer/add", function(data){
        Feng.success("添加成功!");
        window.parent.AnswerPaper.table.refresh();
        AnswerPaperInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.answerPaperInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AnswerPaperInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/answer/update", function(data){
        Feng.success("修改成功!");
        window.parent.AnswerPaper.table.refresh();
        AnswerPaperInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.answerPaperInfoData);
    ajax.start();
}

$(function() {

});
