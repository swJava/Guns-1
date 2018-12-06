/**
 * 初始化题库归档详情对话框
 */
var QuestionWeightInfoDlg = {
    questionWeightInfoData : {},
    validateFields: {
        qcode: {
            validators: {
                notEmpty: {
                    message: '编码不能为空'
                }
            }
        },
        grade: {
            validators: {
                notEmpty: {
                    message: '年级不能为空'
                }
            }
        },
        ability: {
            validators: {
                notEmpty: {
                    message: '对应班次不能为空'
                }
            }
        },
        score: {
            validators: {
                notEmpty: {
                    message: '所含分值不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
QuestionWeightInfoDlg.clearData = function() {
    this.questionWeightInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QuestionWeightInfoDlg.set = function(key, val) {
    this.questionWeightInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
QuestionWeightInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
QuestionWeightInfoDlg.close = function() {
    parent.layer.close(window.parent.QuestionWeight.layerIndex);
}

/**
 * 收集数据
 */
QuestionWeightInfoDlg.collectData = function() {
    this
    .set('id')
    .set('qcode')
    .set('grade')
    .set('ability')
    .set('score')
    .set('status');
}

/**
 * 验证数据是否为空
 */
QuestionWeightInfoDlg.validate = function () {
    $('#itemInfoForm').data("bootstrapValidator").resetForm();
    $('#itemInfoForm').bootstrapValidator('validate');
    return $("#itemInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
QuestionWeightInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/questionWeight/add", function(data){
        Feng.success("添加成功!");
        window.parent.QuestionWeight.table.refresh();
        QuestionWeightInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.questionWeightInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
QuestionWeightInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/questionWeight/update", function(data){
        Feng.success("修改成功!");
        window.parent.QuestionWeight.table.refresh();
        QuestionWeightInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.questionWeightInfoData);
    ajax.start();
}

$(function() {
    //非空校验
    Feng.initValidator("itemInfoForm", QuestionWeightInfoDlg.validateFields);

    var html = "";
    var ajax = new $ax(Feng.ctxPath + "/question/listObject", function (data) {
        data.forEach(function (item) {
            html +="<option value="+item.code+">"+item.code+"</option>";
        })
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
    $("#qcode").append(html);

    //初始select选项
    $("#grade").val($("#gradeValue").val());
    $("#status").val($("#statusValue").val());
    $("#qcode").val($("#qcodeValue").val());
    $("#ability").val($("#abilityValue").val());
});
