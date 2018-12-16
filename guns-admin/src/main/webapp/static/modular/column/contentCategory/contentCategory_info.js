/**
 * 初始化关系栏目内容详情对话框
 */
var ContentCategoryInfoDlg = {
    contentCategoryInfoData : {},
    validateFields: {
        columnCode: {
            validators: {
                columnCode: {
                    message: '栏目编码不能为空'
                }
            }
        },
        contentCode: {
            validators: {
                notEmpty: {
                    message: '内容不能为空'
                }
            }
        },
    }
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
    $("#columnName").val($("#columnCode option:selected").text());
    $("#contentName").val($("#contentCode option:selected").text());
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
    $("#columnName").val($("#columnCode option:selected").text());
    $("#contentName").val($("#contentCode option:selected").text());
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
    /* 栏目*/
    var html = "";
    var ajax = new $ax(Feng.ctxPath + "/column/listAll", function (data) {
        data.forEach(function (item) {
            html +="<option value="+item.code+">"+item.name+"</option>";
        })
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
    $("#columnCode").append(html);
    /* 内容*/
    var html = "";
    var ajax = new $ax(Feng.ctxPath + "/content/listAll", function (data) {
        data.forEach(function (item) {
            html +="<option value="+item.code+">"+item.introduce+"</option>";
        })
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
    $("#contentCode").append(html);
    //初始select选项
    $("#status").val($("#statusValue").val());
});
