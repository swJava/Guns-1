/**
 * 初始化调课管理详情对话框
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
    .set('id')
    .set('classCode')
    .set('studentCode')
    .set('type')
    .set('userName')
    .set('target')
    .set('status')
    .set('workStatus')
    .set('remark')
    .set('createTime')
    .set('updateTime');
}

/**
 * 提交添加
 */
AdjustStudentInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/adjustStudent/add", function(data){
        Feng.success("添加成功!");
        window.parent.AdjustStudent.table.refresh();
        AdjustStudentInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.adjustStudentInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AdjustStudentInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/adjustStudent/update", function(data){
        Feng.success("修改成功!");
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
