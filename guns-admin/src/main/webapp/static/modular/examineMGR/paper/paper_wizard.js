/**
 * 试卷管理
 */
var PaperWizard = {
    Wizard: {
        id: 'wizard',
        paper: $('#paper').val()
    },
    UnSelectQuestion: {
        id: "UnSelectQuestionTable",	        //表格id
        seItems: new Array(),		            //选中的条目
        table: null,
        layerIndex: -1
    },
    SelectedQuestion: {
        id: "SelectedQuestionTable",	        //表格id
        seCodes: new Array(),                   // 已加入试卷的题目
        seScores : new Object(),                // 题目分数
        seItems: new Array(),		            //选中的条目
        table: null,
        layerIndex: -1
    },
    forms: [
        {
            id: 'basePaperForm',
            validateFields: {
                grades: {
                    feedbackIcons: false,
                    validators: {
                        notEmpty: {
                            message: '适应年级不能为空'
                        }
                    }
                },
                subject: {
                    feedbackIcons: false,
                    validators: {
                        notEmpty: {
                            message: '学科不能为空'
                        }
                    }
                },
                examTime: {
                    feedbackIcons: false,
                    validators: {
                        notEmpty: {
                            message: '测试时间不能为空'
                        }
                    }
                },
                ability: {
                    feedbackIcons: false,
                    validators: {
                        notEmpty: {
                            message: '适应班次不能为空'
                        }
                    }
                }
            }
        },
        {
            id: 'questionItemForm',
            validateFields: {
                feedbackIcons: false,
                questionItemCount: {
                    validators: {
                        notEmpty: {
                            message: '请加入试题'
                        },
                        greaterThan: {
                            value: 1,
                            message: '请加入试题',
                            onError: function(e, data){
                                Feng.error('请加入试题');
                            }
                        }
                    }
                }
            }
        },
        {
            id: 'questionScoreForm',
            validateFields: {
                feedbackIcons: false,
                scoreCountValidator: {
                    validators: {
                        score_v: {
                            message: '题目需设置分数',
                            onError: function(e, data){
                                Feng.error('题目需设置分数');
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
PaperWizard.SelectedQuestion.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
        {title: '试题编码', field: 'code', visible: false, align: 'center', valign: 'middle'},
        {title: '试题题目', field: 'question', visible: true, align: 'center', valign: 'middle'},
        {title: '试题类型', field: 'typeName', visible: true, align: 'center', valign: 'middle'},
        {title: '出题人', field: 'teacherName', visible: true, align: 'center', valign: 'middle'},
        {title: '分值', field: 'score', visible: true, align: 'center', valign: 'middle',
            formatter: function(val, row){
                console.log(PaperWizard.SelectedQuestion.seScores);
                console.log(row);
                var score = parseInt(PaperWizard.SelectedQuestion.seScores[row.code], 10);
                if (isNaN(score))
                    score = 0;

                return score;
            }
        }
    ];
};

PaperWizard.UnSelectQuestion.initColumn = function () {
    return [
        {field: 'selectItem', checkbox: true},
        {title: '试题编码', field: 'code', visible: false, align: 'center', valign: 'middle'},
        {title: '试题题目', field: 'question', visible: true, align: 'center', valign: 'middle'},
        {title: '试题类型', field: 'typeName', visible: true, align: 'center', valign: 'middle'},
        {title: '出题人', field: 'teacherName', visible: true, align: 'center', valign: 'middle'},
        {title: '分值', field: 'score', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
PaperWizard.UnSelectQuestion.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PaperWizard.UnSelectQuestion.seItems = selected.slice(0);
        return true;
    }
};
PaperWizard.SelectedQuestion.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        PaperWizard.SelectedQuestion.seItems = selected.slice(0);
        return true;
    }
};

PaperWizard.join = function(){
    if (this.UnSelectQuestion.check()) {
        var count = 0;
        try {
            count = parseInt($('#questionItemCount').val(), 10);
        }catch(e){}

        $.each(this.UnSelectQuestion.seItems, function(idx, eo){
            $('#' + PaperWizard.SelectedQuestion.id).bootstrapTable('append', eo);
            $('#' + PaperWizard.UnSelectQuestion.id).bootstrapTable('remove', {
                field: 'code',
                values: [eo.code]
            });
            PaperWizard.SelectedQuestion.seCodes.push(eo.code);
            count++;
        });
        $('#questionItemCount').val(count);
        $('#UnSelectQuestionTableToolbar .label').html(count);
    }
};

/**
 * 移除问题
 */
PaperWizard.remove = function(){
    if (this.SelectedQuestion.check()) {
        console.log('begin remove');
        $.each(this.SelectedQuestion.seItems, function(idx, eo){
            $('#' + PaperWizard.SelectedQuestion.id).bootstrapTable('remove', {
                field: 'code',
                values: [eo.code]
            });
            $.each(PaperWizard.SelectedQuestion.seCodes, function(idx, code){
                if (code == eo.code){
                    // 删除
                    PaperWizard.SelectedQuestion.seCodes.splice(idx, 1);
                }
            });
            delete PaperWizard.SelectedQuestion.seScores[row.code];
        });
    }

    console.log('remove over');
};

/**
 * 保存列值
 * @param index
 * @param field
 * @param value
 */
PaperWizard.saveEditColumn = function(index, field, value) {
    $('#' + PaperWizard.SelectedQuestion.id).bootstrapTable('updateCell', {
        index: index,       //行索引
        field: field,       //列名
        value: value        //cell值
    });
};
/**
 * 打开试卷题目预览界面
 */
PaperWizard.openPaperViewer = function () {
    var index = layer.open({
        type: 2,
        title: '添加题目',
        area: ['320px', '480px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: ''
    });
    this.layerIndex = index;
};

$(function () {
    // 注册验证方法
    $.fn.bootstrapValidator.validators.score_v = {
        validate: function() {
            console.log('<<< check score submit');
            var questions = PaperWizard.SelectedQuestion.seCodes.slice(0);
            var paperItems = new Array();
            $.each(questions, function(idx, code){
                var score = parseInt(PaperWizard.SelectedQuestion.seScores[code], 10);

                if (isNaN(score))
                    return true;

                if (score <= 0)
                    return true;

                paperItems.push(code + '=' + score);
            });
            return paperItems.length == questions.length;
        }
    };
    // 初始化
    try {
        PaperWizard.SelectedQuestion.seCodes = JSON.parse($('#questionCodes').val());
        PaperWizard.SelectedQuestion.seScores = JSON.parse($('#questionScores').val());
        $('#questionItemCount').val(PaperWizard.SelectedQuestion.seCodes.length);
        $('#UnSelectQuestionTableToolbar .label').html(PaperWizard.SelectedQuestion.seCodes.length);
    }catch(error){}

    var form = $('#' + PaperWizard.Wizard.id);
    form.steps({
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

            if (step > PaperWizard.forms.length - 1) {
                console.log(' no validator match');
                return true;
            }

            var id = PaperWizard.forms[step].id;
            console.log(id);
            console.log(PaperWizard.forms[step].validateFields);
            Feng.initValidator(PaperWizard.forms[step].id, PaperWizard.forms[step].validateFields, {excludes: [":disabled"]});
            $('#' + PaperWizard.forms[step].id).data("bootstrapValidator").resetForm();
            $('#' + PaperWizard.forms[step].id).bootstrapValidator('validate');
            return $('#' + PaperWizard.forms[step].id).data('bootstrapValidator').isValid();
        },
        onStepChanged: function(event, step, prev){
            console.log('<<< step ' + step + ' change from ' + prev);

            if (step == 1 && step < prev){
                // 正向进入到"设置分数"步骤时
                PaperWizard.UnSelectQuestion.table.refresh();
                $('#questionItemCount').val(PaperWizard.SelectedQuestion.seCodes.length);
                $('#UnSelectQuestionTableToolbar .label').html(PaperWizard.SelectedQuestion.seCodes.length);
            }

            if (step == 2) {
                // 预览试卷， 数据加载
                var currStep = form.steps('getStep', step+1);
                console.log('<<< set review url parameter');
                console.log(PaperWizard.SelectedQuestion.seCodes);
                currStep.contentUrl = Feng.ctxPath + '/examine/paper/wizard/review?questionItemExp=' + JSON.stringify(PaperWizard.SelectedQuestion.seCodes);
            }
        }
    });

    form.steps('add', {
        title: "确认",
        contentMode: "iframe",
        contentUrl: Feng.ctxPath + '/examine/paper/wizard/review?questionItemExp=' + JSON.stringify(PaperWizard.SelectedQuestion.seCodes)
    });

    var displayColumns = PaperWizard.UnSelectQuestion.initColumn();
    var table = new BSTable(PaperWizard.UnSelectQuestion.id, "/examine/paper/question/list?excludePaper=" + $('#code').val(), displayColumns);
    table.setPaginationType("server");
    table.setQueryParamsGetter(function(){
        return {'workingCodes': PaperWizard.SelectedQuestion.seCodes.join(',')};
    });

    PaperWizard.UnSelectQuestion.table = table.init();

    displayColumns = PaperWizard.SelectedQuestion.initColumn();
    var table = new BSTable(PaperWizard.SelectedQuestion.id, "/examine/paper/question/list?includePaper=" + $('#code').val(), displayColumns);
    table.setPaginationType("server");
    table.setShowColumns(false);
    table.setShowRefresh(true);
    table.setPaginational(false);
    table.setClickCell(function(field, value, row, $element) {
        //$element.attr('contenteditable', true);
        if (PaperWizard.editable)
            return;

        PaperWizard.editable = true
        var currValue = $element.html();
        $element.html('');
        var input = $('<input type="input" class="form-group" name="score" size="3" maxlength="3" value="'+currValue+'" />');
        $element.append(input);
        input.focus();

        $(document).bind('keydown', function(event){
            console.log('>>> key code ' + event.keyCode);

            if (event.keyCode==13) {  //回车键的键值为13
                var tdvalue = input.val();
                var pattern = new RegExp(/^[1-9]\d{0,1}$|^1[0]{2}$/);
                var value = parseInt(input.val(), 10);

                console.log(pattern.test(tdvalue));
                console.log('value = ' + isNaN(value));
                console.log('value = ' + value);
                console.log('tdValue = ' + tdvalue);
                if ( !(pattern.test(tdvalue)) || isNaN(value) ){
                    Feng.error('请输入正确分值： 1 - 100');
                    event.preventDefault();
                    return false;
                }

                console.log('<<< continue set score');
                input.remove();
                var index = $element.parent().data('index');
                PaperWizard.SelectedQuestion.seScores[row.code] = tdvalue;

                PaperWizard.saveEditColumn(index, field, tdvalue);

                $('#indeedScorecount').val(PaperWizard.SelectedQuestion.seScores.length);
                PaperWizard.editable = false;
            }
        });

        input.blur(function(){
            input.remove();
            var index = $element.parent().data('index');
            PaperWizard.SelectedQuestion.seScores[row.code] = currValue;

            PaperWizard.saveEditColumn(index, field, currValue);
            PaperWizard.editable = false;
        });

    });
    PaperWizard.SelectedQuestion.table = table.init();
});
