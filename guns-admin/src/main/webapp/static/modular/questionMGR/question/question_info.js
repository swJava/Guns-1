/**
 * 初始化入学诊断详情对话框
 */
var QuestionInfoDlg = {
    questionInfoData : {},
    validateFields: {
        code: {
            validators: {
                notEmpty: {
                    message: '编码不能为空'
                }
            }
        },
        question: {
            validators: {
                notEmpty: {
                    message: '题目不能为空'
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
        subject: {
            validators: {
                notEmpty: {
                    message: '学科不能为空'
                }
            }
        },
        expactAnswer: {
            validators: {
                notEmpty: {
                    message: '答案不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
QuestionInfoDlg.clearData = function() {
    this.questionInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QuestionInfoDlg.set = function(key, val) {
    this.questionInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QuestionInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
QuestionInfoDlg.close = function() {
    parent.layer.close(window.parent.Question.layerIndex);
}

/**
 * 收集数据
 */
QuestionInfoDlg.collectData = function() {
    this
    .set('id')
    .set('code')
    .set('question')
    .set('type')
    .set('subject')
    .set('status')
    .set('expactAnswer');
}


/**
 * 验证数据是否为空
 */
QuestionInfoDlg.validate = function () {
    $('#questionInfoForm').data("bootstrapValidator").resetForm();
    $('#questionInfoForm').bootstrapValidator('validate');
    return $("#questionInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
QuestionInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();


    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/question/add", function(data){
        Feng.success("添加成功!");
        window.parent.Question.table.refresh();
        QuestionInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.questionInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
QuestionInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();


    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/question/update", function(data){
        Feng.success("修改成功!");
        window.parent.Question.table.refresh();
        QuestionInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.questionInfoData);
    ajax.start();
}

$(function() {
    //非空校验
    Feng.initValidator("questionInfoForm", QuestionInfoDlg.validateFields);

    //初始select选项
    $("#type").val($("#typeValue").val());
    $("#subject").val($("#subjectValue").val());
    $("#status").val($("#statusValue").val());


    // 初始化头像上传
    var avatarUp = new $WebUpload("question");
    avatarUp.setUploadBarId("progressBar");
    avatarUp.init();

});
