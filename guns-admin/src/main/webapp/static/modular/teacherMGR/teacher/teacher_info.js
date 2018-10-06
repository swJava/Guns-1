/**
 * 初始化教师管理详情对话框
 */
var TeacherInfoDlg = {
    teacherInfoData: {},
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
        gender: {
            validators: {
                notEmpty: {
                    message: '性别不能为空'
                }
            }
        },
        identical: {
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
TeacherInfoDlg.clearData = function() {
    this.teacherInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TeacherInfoDlg.set = function(key, val) {
    this.teacherInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TeacherInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TeacherInfoDlg.close = function() {
    parent.layer.close(window.parent.Teacher.layerIndex);
}

/**
 * 收集数据
 */
TeacherInfoDlg.collectData = function() {
    this
    .set('id')
    .set('code')
    .set('name')
    .set('avatar')
    .set('type')
    .set('gender')
    .set('graduate')
    .set('grade')
    .set('havest')
    .set('experience')
    .set('feature')
    .set('status');
}

/**
 * 验证数据是否为空
 */
TeacherInfoDlg.validate = function () {
    $('#teacherInfoForm').data("bootstrapValidator").resetForm();
    $('#teacherInfoForm').bootstrapValidator('validate');
    return $("#teacherInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
TeacherInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/teacher/add", function(data){
        Feng.success("添加成功!");
        window.parent.Teacher.table.refresh();
        TeacherInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.teacherInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TeacherInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/teacher/update", function(data){
        Feng.success("修改成功!");
        window.parent.Teacher.table.refresh();
        TeacherInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.teacherInfoData);
    ajax.start();
}

$(function() {
    //非空校验
    Feng.initValidator("teacherInfoForm", TeacherInfoDlg.validateFields);

    //初始select选项
    $("#gender").val($("#genderValue").val());
    $("#type").val($("#typeValue").val());
    $("#status").val($("#statusValue").val());


    // 初始化头像上传
    var avatarUp = new $WebUpload("avatar");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.init();

});
