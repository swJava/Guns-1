/**
 * 初始化资讯管理详情对话框
 */
var ContentInfoDlg = {
    contentInfoData : {}
};

/**
 * 清除数据
 */
ContentInfoDlg.clearData = function() {
    this.contentInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ContentInfoDlg.set = function(key, val) {
    this.contentInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ContentInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ContentInfoDlg.close = function() {
    parent.layer.close(window.parent.Content.layerIndex);
}

/**
 * 收集数据
 */
ContentInfoDlg.collectData = function() {
    this
    .set('id')
    .set('code')
    .set('type')
    .set('timage')
    .set('title')
    .set('introduce')
    .set('author')
    .set('publishType')
    .set('content')
    .set('createDate')
    .set('deadDate')
    .set('status');
}

/**
 * 提交添加
 */
ContentInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/content/add", function(data){
        Feng.success("添加成功!");
        window.parent.Content.table.refresh();
        ContentInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.contentInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ContentInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/content/update", function(data){
        Feng.success("修改成功!");
        window.parent.Content.table.refresh();
        ContentInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.contentInfoData);
    ajax.start();
}

$(function() {

});
