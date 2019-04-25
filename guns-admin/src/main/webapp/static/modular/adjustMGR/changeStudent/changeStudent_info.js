/**
 * 初始化转班管理详情对话框
 */
var AdjustStudentInfoDlg = {
    adjustStudentInfoData : {}
};

/**
 * 清除数据
 */
AdjustStudentInfoDlg.clearData = function() {
    this.adjustStudentInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AdjustStudentInfoDlg.set = function(key, val) {
    this.adjustStudentInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AdjustStudentInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AdjustStudentInfoDlg.close = function() {
    parent.layer.close(window.parent.AdjustStudent.layerIndex);
}

/**
 * 收集数据
 */
AdjustStudentInfoDlg.collectData = function() {
    this
    .set('applyId')
    .set('remark')
    ;
}


/**
 * 提交修改
 */
AdjustStudentInfoDlg.doApprove = function(op) {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/change/approve/" + op, function(data){
        Feng.success("操作成功!");
        window.parent.AdjustStudent.table.refresh();
        AdjustStudentInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.adjustStudentInfoData);
    ajax.start();
}

$(function() {

});
