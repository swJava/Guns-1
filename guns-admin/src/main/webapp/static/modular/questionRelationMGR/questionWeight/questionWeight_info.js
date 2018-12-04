/**
 * 初始化题库归档详情对话框
 */
var QuestionWeightInfoDlg = {
    questionWeightInfoData : {}
};

/**
 * 清除数据
 */
QuestionWeightInfoDlg.clearData = function() {
    this.questionWeightInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QuestionWeightInfoDlg.set = function(key, val) {
    this.questionWeightInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QuestionWeightInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
QuestionWeightInfoDlg.close = function() {
    parent.layer.close(window.parent.QuestionWeight.layerIndex);
}

/**
 * 收集数据
 */
QuestionWeightInfoDlg.collectData = function() {
    this
    .set('id')
    .set('qcode')
    .set('grade')
    .set('ability')
    .set('score')
    .set('status');
}

/**
 * 提交添加
 */
QuestionWeightInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/questionWeight/add", function(data){
        Feng.success("添加成功!");
        window.parent.QuestionWeight.table.refresh();
        QuestionWeightInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.questionWeightInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
QuestionWeightInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/questionWeight/update", function(data){
        Feng.success("修改成功!");
        window.parent.QuestionWeight.table.refresh();
        QuestionWeightInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.questionWeightInfoData);
    ajax.start();
}

$(function() {

});
