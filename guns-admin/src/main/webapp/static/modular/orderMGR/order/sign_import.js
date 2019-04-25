/**
 * 初始化资讯管理详情对话框
 */
var ImportInfoDlg = {
    importInfoData: {},
    validateFields: {}
}
/**
 * 清除数据
 */
ImportInfoDlg.clearData = function() {
    this.importInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ImportInfoDlg.set = function(key, val) {
    this.importInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ImportInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ImportInfoDlg.close = function() {
    parent.layer.close(window.parent.Sign.layerIndex);
}

/**
 * 收集数据
 */
ImportInfoDlg.collectData = function() {
    this
        .set("classCode")
        .set('masterName')
        .set('masterCode');
}

/**
 * 提交添加
 */
ImportInfoDlg.doSubmit = function() {

    this.clearData();
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/order/sign/import/student", function(data){
        Feng.success("报名成功!");
        window.parent.Sign.table.refresh();
        ImportInfoDlg.close();
    },function(data){
        Feng.error("报名失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.importInfoData);
    ajax.start();
}

$(function() {
    //非空校验
    Feng.initValidator("importInfoForm", ImportInfoDlg.validateFields);

    // 初始化图片上传
    var avatarUp = new $WebUpload("timage");
    avatarUp.setUploadBarId("progressBar");

    avatarUp.setViewWidth($('#displayImage').width());
    avatarUp.setViewHeight(50);
    avatarUp.init();
});
