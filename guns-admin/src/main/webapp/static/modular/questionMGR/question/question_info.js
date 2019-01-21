/**
 * 初始化入学诊断详情对话框
 */
var QuestionInfoDlg = {
    editor: null,
    questionInfoData : {},
    itemIndex: ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N'],
    itemTemplate: $("#itemTemplate").html(),
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
    this.questionInfoData['content'] = QuestionInfoDlg.editor.txt.html();

    this
    .set('id')
    .set('code')
    .set('question')
    .set('type')
    .set('subject')
    .set('status')
    .set('expactAnswer');

    this.clearNullDom();
    var mutiString = "";
    $("[name='dictItem']").each(function(){
        var code = $(this).find("[name='itemCode']").val();
        var name = $(this).find("[name='itemName']").val();
        var num = $(this).find("[name='itemNum']").val();
        mutiString = mutiString + (code + ":" + name + ":"+ num+";");
    });
    this.dictName = $("#dictName").val();
    this.dictCode = $("#dictCode").val();
    this.dictTips = $("#dictTips").val();
    this.mutiString = mutiString;
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
 * item获取新的id
 */
QuestionInfoDlg.newId = function () {
    if(this.count == undefined){
        this.count = 0;
    }
    this.count = this.count + 1;
    return "dictItem" + this.count;
};

/**
 * 添加条目
 */
QuestionInfoDlg.addItem = function () {
    var itemCount = $('#itemArea [name="dictItem"]').length

    $("#itemArea").append(this.itemTemplate);
    $('#itemArea [name="dictItem"]:last [name="itemIndex"]').val(QuestionInfoDlg.itemIndex[itemCount]);
    $("#dictItem").attr("id", this.newId());
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

    //初始化编辑器
    var E = window.wangEditor;
    var editor = new E('#editor');
    // 配置服务器端地址
    editor.customConfig.uploadImgServer = Feng.ctxPath + '/attachment/upload/async';
    editor.customConfig.uploadFileName = 'files';
    editor.customConfig.uploadImgHooks = {
        customInsert: function (insertImg, result, editor) {
            // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
            // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果

            // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
            console.log(result);
            var url = Feng.ctxPath + '/attachment/download?masterName=' + result.data.name + '&masterCode=' + result.data.code;
            insertImg(url);

            // result 必须是一个 JSON 格式字符串！！！否则报错
        }
    }
    editor.create();
    editor.txt.html($("#contentVal").val());
    QuestionInfoDlg.editor = editor;

});
