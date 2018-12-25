/**
 * 初始化栏目详情对话框
 */
var ColumnInfoDlg = {
    columnInfoData : {},
    validateFields: {
        code: {
            validators: {
                notEmpty: {
                    message: '编码不能为空'
                }
            }
        },
        name: {
            validators: {
                notEmpty: {
                    message: '栏目不能为空名称'
                }
            }
        },
        icon: {
            validators: {
                notEmpty: {
                    message: '图标不能为空'
                }
            }
        },
        pcode: {
            validators: {
                notEmpty: {
                    message: '父级栏目不能为空'
                }
            }
        },
        pcodes: {
            validators: {
                notEmpty: {
                    message: '祖先栏目不能为空'
                }
            }
        },
    }
};

/**
 * 清除数据
 */
ColumnInfoDlg.clearData = function() {
    this.columnInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ColumnInfoDlg.set = function(key, val) {
    this.columnInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ColumnInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ColumnInfoDlg.close = function() {
    parent.layer.close(window.parent.Column.layerIndex);
}

/**
 * 收集数据
 */
ColumnInfoDlg.collectData = function() {
    this
    .set('id')
    .set('code')
    .set('name')
    .set('icon')
    .set('pcode')
    .set('pcodes')
    .set('status')
    .set('masterName')
    .set('masterCode');
}

/**
 * 提交添加
 */
ColumnInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/column/add", function(data){
        Feng.success("添加成功!");
        window.parent.Column.table.refresh();
        ColumnInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    //console.log('data ==> ' + this.columnInfoData);
    ajax.set(this.columnInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ColumnInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/column/update", function(data){
        Feng.success("修改成功!");
        window.parent.Column.table.refresh();
        ColumnInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.columnInfoData);
    ajax.start();
}

$(function() {
    //非空校验
    Feng.initValidator("columnInfoForm", ColumnInfoDlg.validateFields);

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
    $("#pcode").append(html);

    //初始select选项
    $("#status").val($("#statusValue").val());
    $("#pcode").val($("#pcodeValue").val());


    // 初始化图片上传
    var avatarUp = new $WebUpload("icon");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.init();
});
