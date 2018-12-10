/**
 * 初始化课程管理详情对话框
 */
var ClassInfoDlg = {
    classInfoData : {},
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
                }
            }
        },
        beginDate: {
            validators: {
                notEmpty: {
                    message: '开课起始日期不能为空'
                }
            }
        },
        endDate: {
            validators: {
                notEmpty: {
                    message: '开课结束日期不能为空'
                }
            }
        },
        studyTimeValue: {
            validators: {
                notEmpty: {
                    message: '开课时间不能为空'
                },
                regexp: {
                    regexp: /^[0-9]{1,4}$/,
                    message: '4位数字内'
                }
            }
        },
        beginTime: {
            validators: {
                notEmpty: {
                    message: '开始时间不能为空'
                },
                regexp: {
                    regexp: /^[0-9]{1,4}$/,
                    message: '4位数字内'
                }
            }
        },
        endTime: {
            validators: {
                notEmpty: {
                    message: '结束时间'
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
                    message: '课时时长'
                },
                regexp: {
                    regexp: /^[1-9][0-9]{0,3}$/,
                    message: '4位数字内'
                }
            }
        },
        period: {
            validators: {
                notEmpty: {
                    message: '课时数'
                },
                regexp: {
                    regexp: /^[1-9][0-9]{0,3}$/,
                    message: '4位数字内'
                }
            }
        },
        classRoomCode: {
            validators: {
                notEmpty: {
                    message: '教室编码'
                }
            }
        },
        quato: {
            validators: {
                notEmpty: {
                    message: '报名人数'
                },
                regexp: {
                    regexp: /^[1-9][0-9]{0,9}$/,
                    message: '9位数字内'
                }
            }
        },
        signEndDate: {
            validators: {
                notEmpty: {
                    message: '报名截止时间'
                }
            }
        },
        star: {
            validators: {
                notEmpty: {
                    message: '关注度'
                },
                regexp: {
                    regexp: /^[0-9]{0,2}$/,
                    message: '2位数字内'
                }
            }
        },
        courseCode: {
            validators: {
                notEmpty: {
                    message: '教授课程'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
ClassInfoDlg.clearData = function() {
    this.classInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ClassInfoDlg.set = function(key, val) {
    this.classInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ClassInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
ClassInfoDlg.close = function() {
    parent.layer.close(window.parent.Class.layerIndex);
}

/**
 * 收集数据
 */
ClassInfoDlg.collectData = function() {
    this
    .set('id')
    .set('code')
    .set('name')
    .set('beginDate')
    .set('endDate')
    .set('studyTimeType')
    .set('studyTimeValue')
    .set('beginTime')
    .set('endTime')
    .set('duration')
    .set('period')
    .set('classRoomCode')
    .set('classRoom')
    .set('courseCode')
    .set('courseName')
    .set('star')
    .set('quato')
    .set('signEndDate')
    .set('status');
}

/**
 * 验证数据是否为空
 */
ClassInfoDlg.validate = function () {
    $('#classInfoForm').data("bootstrapValidator").resetForm();
    $('#classInfoForm').bootstrapValidator('validate');
    return $("#classInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
ClassInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/class/add", function(data){
        Feng.success("添加成功!");
        window.parent.Class.table.refresh();
        ClassInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.classInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
ClassInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/class/update", function(data){
        Feng.success("修改成功!");
        window.parent.Class.table.refresh();
        ClassInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.classInfoData);
    ajax.start();
}

$(function() {
    //非空校验
    Feng.initValidator("classInfoForm", ClassInfoDlg.validateFields);

    /* 教室 */
    var html = "";
    var ajax = new $ax(Feng.ctxPath + "/classroom/listRoom", function (data) {
        data.forEach(function (item) {
            html +="<option value="+item.code+">"+item.name+"</option>";
        })
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
    $("#classRoomCode").append(html);

    /* 课程*/
    var html = "";
    var ajax = new $ax(Feng.ctxPath + "/classroom/listRoom", function (data) {
        data.forEach(function (item) {
            html +="<option value="+item.code+">"+item.name+"</option>";
        })
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
    $("#courseCode").append(html);

    /* 老师*/
    var html = "";
    var ajax = new $ax(Feng.ctxPath + "/teacher/listAll", function (data) {
        data.forEach(function (item) {
            html +="<option value="+item.code+">"+item.name+"</option>";
        })
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
    $("#teacherCode").append(html);
    $("#teacherSecondCode").append(html);

    //初始select选项
    $("#classRoomCode").val($("#classRoomCodeValue").val());
    $("#classRoom").val($("#classRoomCodeValue option:selected").text());
    $("#status").val($("#statusValue").val());
    $("#studyTimeType").val($("#studyTimeTypeValue").val());
    $("#courseCode").val($("#courseCodeValue").val());
    $("#courseName").val($("#courseCodeValue option:selected").text());
    $("#teacherCode").val($("#teacherCodeValue").val());
    $("#teacher").val($("#teacherCodeValue option:selected").text());
    $("#teacherSecondCode").val($("#teacherSecondCodeValue").val());
    $("#teacherSecond").val($("#teacherSecondCodeValue option:selected").text());
});
