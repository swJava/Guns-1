/**
 * 初始化关系栏目内容详情对话框
 */
var ContentCategoryInfoDlg = {
    contentCategoryInfoData : {}
};

/**
 * 清除数据
 */
ContentCategoryInfoDlg.clearData = function() {
    this.contentCategoryInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ContentCategoryInfoDlg.set = function(key, val) {
    this.contentCategoryInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ContentCategoryInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ContentCategoryInfoDlg.close = function() {
    parent.layer.close(window.parent.ContentCategory.layerIndex);
}

/**
 * 收集数据
 */
ContentCategoryInfoDlg.collectData = function() {
    this
    .set('id')
    .set('columnCode')
    .set('columnName')
    .set('contentCode')
    .set('contentName')
    .set('status');
}

/**
 * 提交添加
 */
ContentCategoryInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/contentCategory/add", function(data){
        Feng.success("添加成功!");
        window.parent.ContentCategory.table.refresh();
        ContentCategoryInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.contentCategoryInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ContentCategoryInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/contentCategory/update", function(data){
        Feng.success("修改成功!");
        window.parent.ContentCategory.table.refresh();
        ContentCategoryInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.contentCategoryInfoData);
    ajax.start();
}

$(function() {

});
