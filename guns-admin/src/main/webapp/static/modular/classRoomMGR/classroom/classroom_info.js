/**
 * 初始化教室管理详情对话框
 */
var ClassroomInfoDlg = {
    classroomInfoData : {},
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
                    message: '姓名不能为空'
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
        address: {
            validators: {
                notEmpty: {
                    message: '地址不能为空'
                }
            }
        },
        maxount: {
            validators: {
                notEmpty: {
                    message: '最大人数不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
ClassroomInfoDlg.clearData = function() {
    this.classroomInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ClassroomInfoDlg.set = function(key, val) {
    this.classroomInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ClassroomInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ClassroomInfoDlg.close = function() {
    parent.layer.close(window.parent.Classroom.layerIndex);
}

/**
 * 收集数据
 */
ClassroomInfoDlg.collectData = function() {
    this
    .set('id')
    .set('code')
    .set('name')
    .set('type')
    .set('address')
    .set('maxCount')
    .set('status');
}

/**
 * 验证数据是否为空
 */
ClassroomInfoDlg.validate = function () {
    $('#classRoomInfoForm').data("bootstrapValidator").resetForm();
    $('#classRoomInfoForm').bootstrapValidator('validate');
    return $("#classRoomInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
ClassroomInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/classroom/add", function(data){
        Feng.success("添加成功!");
        window.parent.Classroom.table.refresh();
        ClassroomInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.classroomInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ClassroomInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }


    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/classroom/update", function(data){
        Feng.success("修改成功!");
        window.parent.Classroom.table.refresh();
        ClassroomInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.classroomInfoData);
    ajax.start();
}

$(function() {
    //非空校验
    Feng.initValidator("classRoomInfoForm", ClassroomInfoDlg.validateFields);

    //初始select选项
    $("#type").val($("#typeValue").val());
    $("#status").val($("#statusValue").val());

});
