/**
 * 初始化学生管理详情对话框
 */
var StudentBaseInfoDlg = {
    studentBaseInfoData : {}
};

/**
 * 清除数据
 */
StudentBaseInfoDlg.clearData = function() {
    this.studentBaseInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StudentBaseInfoDlg.set = function(key, val) {
    this.studentBaseInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
StudentBaseInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
StudentBaseInfoDlg.close = function() {
    parent.layer.close(window.parent.StudentBase.layerIndex);
}

/**
 * 收集数据
 */
StudentBaseInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('phone')
    .set('type')
    .set('timeCreate')
    .set('timeUpdate');
}

/**
 * 提交添加
 */
StudentBaseInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/studentBase/add", function(data){
        Feng.success("添加成功!");
        window.parent.StudentBase.table.refresh();
        StudentBaseInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.studentBaseInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
StudentBaseInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/studentBase/update", function(data){
        Feng.success("修改成功!");
        window.parent.StudentBase.table.refresh();
        StudentBaseInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.studentBaseInfoData);
    ajax.start();
}

$(function() {

});
