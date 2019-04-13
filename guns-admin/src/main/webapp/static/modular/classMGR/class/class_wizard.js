/**
 * 班级管理
 */
var ClassWizard = {
    Wizard: {
        id: 'wizard',
        op: $('#operator').val(),
        classCode: $('#code').val(),
        postData: {},
        planList: new Array(),
        postUrl: {
            'add': Feng.ctxPath + "/class/add",
            'update': Feng.ctxPath + "/class/update"
        },
        courseTable : {
            id : 'courseTable'
        },
        planListTable : {
            id : 'planListTable',
            planList : new Array()
        },
    },
    forms: [
        {
            id: 'courseForm',
            validateFields: {
                courseCode: {
                    feedbackIcons: false,
                    validators: {
                        notEmpty: {
                            message: '请选择课程',
                            onError: function(){
                                Feng.error('请选择课程');
                            }
                        }
                    }
                }
            }
        },
        {
            id: 'classForm',
            validateFields: {
                crossable: {
                    validators: {
                        cross_v: {
                            message: ' ',
                            onError: function(e, data){
                                Feng.error('请设置跨报时间');
                            }
                        }
                    }
                },
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
                price: {
                    validators: {
                        notEmpty: { message: '金额不能为空'},
                        numeric: { message: '无效的金额'},
                        between: { min: 0.00, max: 9999.99, message: '无效的金额'}
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
                signStartDate: {
                    validators: {
                        notEmpty: {
                            message: '报名开始时间'
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
        },
        {
            id: 'planListForm',
            validateFields: {
                feedbackIcons: false,
                planListSetting: {
                    validators: {
                        planlist_v: {
                            message: ' ',
                            onError: function(e, data){
                                Feng.error('请设置排课计划');
                            }
                        }
                    }
                }
            }
        }
    ]
};

/**
 * 初始化表格的列
 */
ClassWizard.Wizard.courseTable.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '课程编码', field: 'code', visible: false, align: 'center', valign: 'middle'},
        {title: '课程名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '授课方式', field: 'methodName', visible: true, align: 'center', valign: 'middle'},
        {title: '授课年级', field: 'gradeName', visible: true, align: 'center', valign: 'middle'},
        {title: '学科', field: 'subjectName', visible: true, align: 'center', valign: 'middle'},
        {title: '课时数', field: 'period', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 初始化排班计划表格
 */
ClassWizard.Wizard.planListTable.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '课程安排', field: 'outline', visible: true, align: 'center', valign: 'middle'},
        {title: '上课日期', field: 'studyDate', visible: true, align: 'center', valign: 'middle',
            formatter: function(value, row, index){
                return '<input id = "studyDate_'+row.code+'" data-field="studyDate" data-row="'+index+'" class="datePicker" />';
            }
        },
        {title: '上课时间', field: 'classTime', visible: true, align: 'center', valign: 'middle',
            formatter: function(value, row, index){
                return '<input id = "classTime_'+row.code+'" data-field="classTime" data-row="'+index+'" class="timePicker" />';
            }
        },
        {title: '下课时间', field: 'endTime', visible: true, align: 'center', valign: 'middle',
            formatter: function(value, row, index){
                return '<input id = "endTime_'+row.code+'" data-field="endTime" data-row="'+index+'" class="timePicker" />';
            }
        }
    ];
};

/**
 * 检查是否选中
 */
ClassWizard.Wizard.courseTable.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ClassWizard.Wizard.courseTable.seItems = selected.slice(0);
        return true;
    }
};
ClassWizard.Wizard.planListTable.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ClassWizard.Wizard.planListTable.seItems = selected.slice(0);
        return true;
    }
};

/**
 * 保存列值
 * @param index
 * @param field
 * @param value
 */
ClassWizard.Wizard.saveEditColumn = function(index, field, value) {
    $('#' + ClassWizard.Wizard.planListTable.id).bootstrapTable('updateCell', {
        index: index,       //行索引
        field: field,       //列名
        value: value        //cell值
    });
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
ClassWizard.set = function(key, val) {
    this.Wizard.postData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
};

/**
 * 清除数据
 */
ClassWizard.clearData = function() {
    this.Wizard.postData = {};
};

/**
 * 收集数据
 */
ClassWizard.collectData = function() {

    var submitPrice = Math.floor(100 * parseFloat($('#price').val(), 10));

    this
        .set('id')
        .set('code')
        .set('name')
        .set('grade')
        .set('cycle')
        .set('ability')
        .set('duration')
        .set('period')
        .set('courseCode')
        .set('courseName')
        .set('star')
        .set('price')
        .set('quato')
        .set('signStartDate')
        .set('signEndDate')
        .set('status')
        .set('teacherCode')
        .set('teacher')
        .set('teacherSecondCode')
        .set('teacherSecond')
        .set('classRoomCode')
        .set('classRoom')
        .set('studyTimeDesp');

    var crossable = $(':radio[name="crossable"]:checked').val();
    if (1 == crossable ){
        this.set('presignStartDate')
            .set('crossStartDate')
            .set('presignEndDate')
            .set('crossEndDate')
        ;
    }

    this.Wizard.postData['price'] = submitPrice;
    this.Wizard.postData['crossable'] = crossable;
};

ClassWizard.padLeft = function(val, len, chr){
    //console.log('val = ' + val);
    var value = ''+val;
    var length = value.length;

    if (length >= len)
        return value;

    var c = length;
    for(; c < len; c++){
        value = chr + value;
    }

    //console.log('result value = ' + value);
    return value;
};
/**
 * 关闭此对话框
 */
ClassWizard.close = function() {
    parent.layer.close(window.parent.Class.layerIndex);
};

ClassWizard.initPlanListTable = function(code){
    console.log('<=== initial plan list ');
    if (ClassWizard.Wizard.planListTable.table) {
        delete ClassWizard.Wizard.planListTable.table;
        $('#' + ClassWizard.Wizard.planListTable.id).bootstrapTable('destroy');
    }
    var courseDisplaColumns = ClassWizard.Wizard.planListTable.initColumn();
    var table = new BSTable(ClassWizard.Wizard.planListTable.id, "/courseOutline/list", courseDisplaColumns);
    table.setPageSize(200);
    table.setPaginational(false);
    table.setPaginationType("server");
    table.setQueryParams({
        courseCode: code
    });
    ClassWizard.Wizard.planListTable.table = table.init();
};

ClassWizard.iniCalendar = function(options) {

    var me = this;
    console.log('<<< init calendar >>>');
    console.log(options);

    window.events = options.calEvents;

    if (me.Wizard.scheduleView){
        console.log('update calendar events');
        $('#calendar').fullCalendar('refetchEvents');
    }else {
        me.calendar = $('#calendar').fullCalendar({
            //isRTL: true,
            eventLimit: true,
            defaultDate: options.today,
            buttonText: {
                today: '今天',
                month: '月视图',
                week: '周视图',
                day: '日视图'
            },
            allDayText: "全天",
            timeFormat: {
                '': 'H(:mm)'
            },
            axisFormat: 'H时(:mm分)',
            firstHour: 8,
            minTime: 8,
            buttonHtml: {
                prev: '<i class="ace-icon fa fa-chevron-left"></i>',
                next: '<i class="ace-icon fa fa-chevron-right"></i>'
            },
            titleFormat: {
                month: 'YYYY年 MMMM月',
                day: 'YYYY年 MMMM月DD日 dddd'
            },
            monthNames: ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"],
            dayNames: ["星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
            dayNamesShort: ["星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"],
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month'
            },
            events: function(start, end, timezone, callback){
                return callback(window.events);
            }
            ,
            selectable: false,
            selectHelper: true
            ,
            editable: false
        });

        me.Wizard.scheduleView = true;
    }
}

$(function () {
    var now = new Date();
    var year = now.getFullYear();
    var month = 1 + now.getMonth();
    if (month < 10)
        month = '0' + month;
    var day = now.getDate();
    if (day < 10)
        day = '0' + day;

    var today = year + '-' + month + '-' + day;
    // 注册验证方法
    $.fn.bootstrapValidator.validators.cross_v = {
        validate: function() {
            var crossable = $(':radio[name="crossable"]:checked').val();
            var presignBeginDate = $('#presignStartDate').val();
            var crossBeginDate = $('#crossStartDate').val();
            var presignEndDate = $('#presignEndDate').val();
            var crossEndDate = $('#crossEndDate').val();
            return 1 == crossable ? (presignBeginDate.length > 0 && presignEndDate.length > 0) && (crossBeginDate.length > 0 && crossEndDate.length > 0) : true;
        }
    };

    $.fn.bootstrapValidator.validators.planlist_v = {
        validate: function() {
            var valid = true;

            $('.datePicker').each(function(idx, eo){
                var value = $(eo).val().trim();
                if (0 == value.length) {
                    valid = false;
                    return false;
                }
            });

            if (!valid)
                return valid;

            $('.timePicker').each(function(idx, eo){
                var value = $(eo).val().trim();
                if (0 == value.length) {
                    valid = false;
                    return false;
                }
            });

            return valid;
        }
    };
    // 初始化
    /*
    try {
        PaperWizard.SelectedQuestion.seCodes = JSON.parse($('#questionCodes').val());
        PaperWizard.SelectedQuestion.seScores = JSON.parse($('#questionScores').val());
        $('#questionItemCount').val(PaperWizard.SelectedQuestion.seCodes.length);
        $('#UnSelectQuestionTableToolbar .label').html(PaperWizard.SelectedQuestion.seCodes.length);
    }catch(error){}
    */

    console.log('<<< init result');
    var wizard = $('#' + ClassWizard.Wizard.id);
    wizard.steps({
        headerTag: "h1",
        bodyTag: "fieldset",
        transitionEffect: "slideLeft",
        autoFocus: true,
        labels: {
            finish: "完成", // 修改按钮得文本
            next: "下一步", // 下一步按钮的文本
            previous: "上一步", // 上一步按钮的文本
            loading: "Loading ..."
        },
        onStepChanging: function(event, step, next){
            console.log('<<< step ' + step + ' change to ' + next);

            if (next < step) {
                console.log(' return not need validate');
                return true;
            }

            if (step > ClassWizard.forms.length - 1) {
                console.log(' no validator match');
                return true;
            }

            Feng.initValidator(ClassWizard.forms[step].id, ClassWizard.forms[step].validateFields, {excludes: [":disabled"]});
            $('#' + ClassWizard.forms[step].id).data("bootstrapValidator").resetForm();
            $('#' + ClassWizard.forms[step].id).bootstrapValidator('validate');
            return $('#' + ClassWizard.forms[step].id).data('bootstrapValidator').isValid();
        },
        onStepChanged: function(event, step, prev){
            console.log('<<< step ' + step + ' change from ' + prev);

            if (1 == step) {
                //日期控件初始化
                laydate.render({elem: '#signStartDate', min: today});
                laydate.render({elem: '#signEndDate', min: today});
                laydate.render({elem: '#presignStartDate', min: today});
                laydate.render({elem: '#crossStartDate', min: today});
                laydate.render({elem: '#presignEndDate', min: today});
                laydate.render({elem: '#crossEndDate', min: today});

                //$(':radio[name="crossable"]').unbindAll();
                $(':radio[name="crossable"]').bind('click', function(){
                   console.log($(this).val());
                   var switchValue = $(this).val();

                   if (1 == switchValue){
                       $('#presignStartDate').parents('.form-group').show();
                       $('#presignEndDate').parents('.form-group').show();
                       $('#crossStartDate').parents('.form-group').show();
                       $('#crossEndDate').parents('.form-group').show();
                   }else{
                       $('#presignStartDate').parents('.form-group').hide();
                       $('#presignEndDate').parents('.form-group').hide();
                       $('#crossStartDate').parents('.form-group').hide();
                       $('#crossEndDate').parents('.form-group').hide();
                   }
                });
                /*  学年 */
                var html = "";
                var academicYears = JSON.parse($('#academicYearsValue').val());
                academicYears.forEach(function (item) {
                    html +="<option value="+item+">"+item+"学年</option>";
                });
                $("#academicYear").append(html);
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
            }

            if (2 == step) {
                console.log('<=== today ' + today  );
                var tableDatas = $('#' + ClassWizard.Wizard.planListTable.id).bootstrapTable('getData');
                $('.datePicker').each(function(idx, eo){
                    laydate.render({elem: '#'+ eo.id, type: 'date', min: today,
                        done: function(value, date){
                            var row = $(eo).attr('data-row');
                            var field = $(eo).attr('data-field');
                            var className = $('#name').val();

                            ClassWizard.Wizard.planListTable.planList[field + '_' + row] = {
                                title: className + ' ' + tableDatas[row].outline,
                                value: value
                            };
                            console.log('<=== planList');
                            console.log(ClassWizard.Wizard.planListTable.planList);
                        }
                    });
                });

                $('.timePicker').each(function(idx, eo){
                    laydate.render({elem: '#'+ eo.id, type: 'time', min: '07:00:00',
                        done: function(value, date, endDate){
                            // 将还未设置上、下课时间的记录，设置为当前值
                            $('.timePicker[data-field="'+$(eo).attr('data-field')+'"]').each(function(idx, eeo){
                               if (null == $(eeo).val() || $(eeo).val().length == 0){
                                   $(eeo).val(value);

                                   var autoRow = $(eeo).attr('data-row');
                                   var autoField = $(eeo).attr('data-field');
                                   var className = $('#name').val();

                                   ClassWizard.Wizard.planListTable.planList[autoField + '_' + autoRow] = {
                                       title: className + ' ' + tableDatas[autoRow].outline,
                                       value: value
                                   };
                               }
                            });

                            var row = $(eo).attr('data-row');
                            var field = $(eo).attr('data-field');

                            ClassWizard.Wizard.planListTable.planList[field + '_' + row] = {
                                title: tableDatas[row].outline,
                                value: value
                            };

                            console.log('<=== planList');
                            console.log(ClassWizard.Wizard.planListTable.planList);
                        }
                    });
                });
            }

            if (step == 3) {
                var tableDatas = $('#' + ClassWizard.Wizard.planListTable.id).bootstrapTable('getData');
                console.log(tableDatas);
                // 排课日历
                var initEvents = new Array();
                ClassWizard.Wizard.planList = new Array();

                $.each(tableDatas, function(idx, eo){
                    initEvents.push({
                        id: eo.code,
                        title: ClassWizard.Wizard.planListTable.planList['studyDate_' + idx].title,
                        start: new Date(ClassWizard.Wizard.planListTable.planList['studyDate_' + idx].value+'T'+ClassWizard.Wizard.planListTable.planList['classTime_' + idx].value),
                        end: new Date(ClassWizard.Wizard.planListTable.planList['studyDate_' + idx].value+'T'+ClassWizard.Wizard.planListTable.planList['endTime_' + idx].value),
                        color: '#ffffff',
                        backgroundColor: '#343faf'
                    });

                    var beginDate = new Date(ClassWizard.Wizard.planListTable.planList['studyDate_' + idx].value+' '+ClassWizard.Wizard.planListTable.planList['classTime_' + idx].value);
                    var endDate = new Date(ClassWizard.Wizard.planListTable.planList['studyDate_' + idx].value+' '+ClassWizard.Wizard.planListTable.planList['endTime_' + idx].value);

                    ClassWizard.Wizard.planList.push({
                        className: $('#name').val(),
                        courseName: $('#courseName').val(),
                        studyDate: beginDate.getFullYear() + '-' + ClassWizard.padLeft((beginDate.getMonth() + 1), 2, '0') + '-' + ClassWizard.padLeft(beginDate.getDate(), 2, '0'),
                        classTime: ClassWizard.padLeft(beginDate.getHours(), 2, '0') + ClassWizard.padLeft(beginDate.getMinutes(), 2, '0'),
                        endtime: ClassWizard.padLeft(endDate.getHours(), 2, '0') + ClassWizard.padLeft(endDate.getMinutes(), 2, '0'),
                        week: 0 == beginDate.getDay() ? 7 : beginDate.getDay()
                    });
                });
                var options = {
                    today: today,
                    calEvents: initEvents
                };
                ClassWizard.iniCalendar(options);
            }
        },
        onFinished: function(){
            console.log('do finish');

            ClassWizard.clearData();

            $("#classRoom").val($("#classRoomCode option:selected").text());
            $("#teacher").val($("#teacherCode option:selected").text());
            $("#teacherSecond").val($("#teacherSecondCode option:selected").text());
            ClassWizard.collectData();

            console.log('<=== operate ' + ClassWizard.Wizard.op);
            console.log('<=== planList ');
            console.log(ClassWizard.Wizard.planList);
            console.log('<=== post ');
            console.log(ClassWizard.Wizard.postData);

            //提交信息
            var ajax = new $ax(ClassWizard.Wizard.postUrl[ClassWizard.Wizard.op], function(data){
                Feng.success("保存成功!");
                window.parent.Class.table.refresh();
                ClassWizard.close();
            },function(data){
                Feng.error("保存失败!" + data.responseJSON.message + "!");
            });
            ajax.set(ClassWizard.Wizard.postData);
            ajax.set('planList', JSON.stringify(ClassWizard.Wizard.planList));

            ajax.start();

        }
    });

    // 课程列表初始化
    if ($('#' + ClassWizard.Wizard.courseTable.id)) {
        var courseDisplaColumns = ClassWizard.Wizard.courseTable.initColumn();
        var table = new BSTable(ClassWizard.Wizard.courseTable.id, "/course/list", courseDisplaColumns);
        table.setPageSize(10);
        table.setItemSelectCallback(function (row) {
            console.log('<=== begin load course info');
            console.log(row);
            $('#courseCode').val(row.code);
            $('#courseName').val(row.name);
            $('#subject').val(row.subject);
            $('#subjectName').val(row.subjectName);
            $('#grade').val(row.grade);
            $('#gradeName').val(row.gradeName);
            $('#method').val(row.method);
            $('#methodName').val(row.methodName);
            $('#period').val(row.period);

            ClassWizard.initPlanListTable(row.code);

        });
        table.setPaginationType("server");
        ClassWizard.Wizard.courseTable.table = table.init();
    };

    //初始select选项
    $("#grades").val($("#gradesValue").val());
    $("#ability").val($("#abilityValue").val());
    $("#subject").val($("#subjectValue").val());
});
