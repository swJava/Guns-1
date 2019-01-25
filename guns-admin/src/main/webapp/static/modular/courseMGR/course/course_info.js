/**
 * 初始化课程管理详情对话框
 */
var CourseInfoDlg = {
    courseInfoData : {},
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
                    message: '名称不能为空'
                },
                regexp: {
                    regexp: /^.{2,30}$/,
                    message: '2 - 30个字符'
                }
            }
        },
        grade: {
            validators: {
                notEmpty: {
                    message: '年级'
                }
            }
        },
        period: {
            validators: {
                notEmpty: {
                    message: '课时数'
                },
                regexp: {
                    regexp: /^[0-9]{1,2}$/,
                    message: '2位数字内'
                }
            }
        },
        method: {
            validators: {
                notEmpty: {
                    message: '教室编码'
                }
            }
        },
        subject: {
            validators: {
                notEmpty: {
                    message: '报名人数'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
CourseInfoDlg.clearData = function() {
    this.courseInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CourseInfoDlg.set = function(key, val) {
    this.courseInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CourseInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CourseInfoDlg.close = function() {
    parent.layer.close(window.parent.Course.layerIndex);
}

/**
 * 收集数据
 */
CourseInfoDlg.collectData = function() {
    this
        .set('id')
        .set('code')
        .set('name')
        .set('grade')
        .set('period')
        .set('subject')
        .set('method')
    ;
}

/**
 * 验证数据是否为空
 */
CourseInfoDlg.validate = function () {
    $('#courseInfoForm').data("bootstrapValidator").resetForm();
    $('#courseInfoForm').bootstrapValidator('validate');
    return $("#courseInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
CourseInfoDlg.addSubmit = function() {

    this.clearData();
    if (!this.validate()) {
        return;
    }
    this.collectData();
    
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/course/save", function(data){
        Feng.success("添加成功!");
        window.parent.Course.table.refresh();
        CourseInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.courseInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CourseInfoDlg.editSubmit = function() {

    this.clearData();
    if (!this.validate()) {
        return;
    }

    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/course/save", function(data){
        Feng.success("修改成功!");
        window.parent.Course.table.refresh();
        CourseInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.courseInfoData);
    ajax.start();
}

$(function() {
    //非空校验
    Feng.initValidator("courseInfoForm", CourseInfoDlg.validateFields);

    //初始select选项
    $("#method").val($("#methodValue").val());
    $("#grade").val($("#gradeValue").val());
    $("#subject").val($("#subjectValue").val());
});
