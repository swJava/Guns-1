/**
 * 初始化栏目行为详情对话框
 */
var ColumnActionInfoDlg = {
    columnActionInfoData : {},
    validateFields: {
        code: {
            validators: {
                columnCode: {
                    message: '栏目编码不能为空'
                }
            }
        },
        name: {
            validators: {
                notEmpty: {
                    message: '动作名称不能为空'
                }
            }
        },
        type: {
            validators: {
                notEmpty: {
                    message: '类型：不能为空'
                }
            }
        },
        action: {
            validators: {
                notEmpty: {
                    message: '动作：不能为空'
                }
            }
        },
    }
};

/**
 * 清除数据
 */
ColumnActionInfoDlg.clearData = function() {
    this.columnActionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ColumnActionInfoDlg.set = function(key, val) {
    this.columnActionInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ColumnActionInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ColumnActionInfoDlg.close = function() {
    parent.layer.close(window.parent.ColumnAction.layerIndex);
}

/**
 * 收集数据
 */
ColumnActionInfoDlg.collectData = function() {
    this
    .set('id')
    .set('columnCode')
    .set('name')
    .set('type')
    .set('action')
    .set('data')
    .set('status');
}

/**
 * 提交添加
 */
ColumnActionInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/columnAction/add", function(data){
        Feng.success("添加成功!");
        window.parent.ColumnAction.table.refresh();
        ColumnActionInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.columnActionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ColumnActionInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/columnAction/update", function(data){
        Feng.success("修改成功!");
        window.parent.ColumnAction.table.refresh();
        ColumnActionInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.columnActionInfoData);
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

    //初始select选项
    $("#status").val($("#statusValue").val());
    $("#type").val($("#typeValue").val());
    $("#action").val($("#actionValue").val());
    $("#columnCode").val($("#columnCodeValue").val());
});
