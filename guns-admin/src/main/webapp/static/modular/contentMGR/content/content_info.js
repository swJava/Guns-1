/**
 * 初始化资讯管理详情对话框
 */
var ContentInfoDlg = {
    contentInfoData : {},
    validateFields: {
        code: {
            validators: {
                notEmpty: {
                    message: '编码不能为空'
                }
            }
        },
        type: {
            validators: {
                notEmpty: {
                    message: '类型不能为空'
                }
            }
        },
        timage: {
            validators: {
                notEmpty: {
                    message: '标题图片不能为空'
                }
            }
        },
        introduce: {
            validators: {
                notEmpty: {
                    message: '简介不能为空'
                }
            }
        },
        publishType: {
            validators: {
                notEmpty: {
                    message: '发布类型不能为空'
                }
            }
        },
        status: {
            validators: {
                notEmpty: {
                    message: '状态不能为空'
                }
            }
        }
    }
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
    //非空校验
    Feng.initValidator("contentInfoForm", ContentInfoDlg.validateFields);

    //初始select选项
    $("#status").val($("#statusValue").val());
    $("#type").val($("#typeValue").val());
    $("#publishType").val($("#publishTypeValue").val());


    // 初始化图片上传
    var avatarUp = new $WebUpload("timage");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.init();

    laydate.render({
        elem: '#deadDate'
    });

});
