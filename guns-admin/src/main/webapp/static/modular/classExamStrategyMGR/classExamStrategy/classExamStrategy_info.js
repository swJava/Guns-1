/**
 * 初始化试卷策略详情对话框
 */
var ClassExamStrategyInfoDlg = {
    classExamStrategyInfoData : {},
    validateFields: {
        classCode: {
            validators: {
                notEmpty: {
                    message: '班级编码不能为空'
                }
            }
        },
        count: {
            validators: {
                notEmpty: {
                    message: '题目数量不能为空'
                },
                regexp: {
                    regexp: /^[0-9]{1,4}$/,
                    message: '4位数字内'
                }
            }
        },
        duration: {
            validators: {
                notEmpty: {
                    message: '测试时间不能为空'
                },
                regexp: {
                    regexp: /^[0-9]{1,4}$/,
                    message: '4位数字内'
                }
            }
        },
        fullCredit: {
            validators: {
                notEmpty: {
                    message: '总分数不能为空'
                },
                regexp: {
                    regexp: /^[0-9]{1,4}$/,
                    message: '4位数字内'
                }
            }
        },
        selectRatio: {
            validators: {
                notEmpty: {
                    message: '选择题占比不能为空'
                },
                regexp: {
                    regexp: /^[0-9]{1,4}$/,
                    message: '4位数字内'
                }
            }
        },
        fillRatio: {
            validators: {
                notEmpty: {
                    message: '填空题占比不能为空'
                },
                regexp: {
                    regexp: /^[0-9]{1,4}$/,
                    message: '4位数字内'
                }
            }
        },
        subjectRatio: {
            validators: {
                notEmpty: {
                    message: '主观题占比不能为空'
                },
                regexp: {
                    regexp: /^[0-9]{1,4}$/,
                    message: '4位数字内'
                }
            }
        },
    }
};

/**
 * 清除数据
 */
ClassExamStrategyInfoDlg.clearData = function() {
    this.classExamStrategyInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ClassExamStrategyInfoDlg.set = function(key, val) {
    this.classExamStrategyInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ClassExamStrategyInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ClassExamStrategyInfoDlg.close = function() {
    parent.layer.close(window.parent.ClassExamStrategy.layerIndex);
}

/**
 * 收集数据
 */
ClassExamStrategyInfoDlg.collectData = function() {
    this
    .set('id')
    .set('classCode')
    .set('count')
    .set('duration')
    .set('fullCredit')
    .set('selectRatio')
    .set('fillRatio')
    .set('subjectRatio')
    .set('autoMarking');
}
/**
 * 验证数据是否为空
 */
ClassExamStrategyInfoDlg.validate = function () {
    $('#classExamStrategyInfoForm').data("bootstrapValidator").resetForm();
    $('#classExamStrategyInfoForm').bootstrapValidator('validate');
    return $("#classExamStrategyInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
ClassExamStrategyInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/classExamStrategy/add", function(data){
        Feng.success("添加成功!");
        window.parent.ClassExamStrategy.table.refresh();
        ClassExamStrategyInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.classExamStrategyInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ClassExamStrategyInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/classExamStrategy/update", function(data){
        Feng.success("修改成功!");
        window.parent.ClassExamStrategy.table.refresh();
        ClassExamStrategyInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.classExamStrategyInfoData);
    ajax.start();
}

$(function() {

    //非空校验
    Feng.initValidator("classExamStrategyInfoForm", ClassExamStrategyInfoDlg.validateFields);

    var html = "";
    var ajax = new $ax(Feng.ctxPath + "/class/listAll", function (data) {
        data.forEach(function (item) {
            html +="<option value="+item.code+">"+item.name+"</option>";
        })
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
    $("#classCode").append(html);

    //初始select选项
    $("#classCode").val($("#classCodeValue").val());
});
