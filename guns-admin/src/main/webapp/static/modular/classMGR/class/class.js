/**
 * 课程管理管理初始化
 */
var Class = {
    id: "ClassTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Class.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'ID', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '班级编码', field: 'code', visible: false, align: 'center', valign: 'middle'},
            {title: '班级名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '年级', field: 'gradeName', visible: false, align: 'center', valign: 'middle'},
            {title: '学期', field: 'cycle', visible: false, align: 'center', valign: 'middle'},
            {title: '班次', field: 'ability', visible: false, align: 'center', valign: 'middle'},
            {title: '开课起始日期', field: 'beginDate', visible: true, align: 'center', valign: 'middle'},
            {title: '开课结束日期', field: 'endDate', visible: false, align: 'center', valign: 'middle'},
            {title: '开课时间类型', field: 'studyTimeTypeName', visible: false, align: 'center', valign: 'middle'},
            {title: '开课时间', field: 'studyTimeValue', visible: false, align: 'center', valign: 'middle'},
            {title: '教学开始时间', field: 'beginTime', visible: true, align: 'center', valign: 'middle'},
            {title: '教学结束时间', field: 'endTime', visible: true, align: 'center', valign: 'middle'},
            {title: '单节时长(分钟)', field: 'duration', visible: false, align: 'center', valign: 'middle'},
            {title: '总课时数', field: 'period', visible: false, align: 'center', valign: 'middle'},
            {title: '教室编码', field: 'classRoomCode', visible: false, align: 'center', valign: 'middle'},
            {title: '教室', field: 'classRoom', visible: true, align: 'center', valign: 'middle'},
            {title: '教授课程', field: 'courseCode', visible: false, align: 'center', valign: 'middle'},
            {title: '课程名称', field: 'courseName', visible: false, align: 'center', valign: 'middle'},
            {title: '关注度', field: 'star', visible: false, align: 'center', valign: 'middle'},
            {title: '价格(元)', field: 'price', visible: true, align: 'center', valign: 'middle'},
            {title: '剩余报名人数', field: 'quato', visible: true, align: 'center', valign: 'middle'},
            {title: '报名截止时间', field: 'signEndDate', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'statusName', visible: false, align: 'center', valign: 'middle'},
            {title: '主讲教师编码', field: 'teacherCode', visible: false, align: 'center', valign: 'middle'},
            {title: '主讲教师名称', field: 'teacher', visible: true, align: 'center', valign: 'middle'},
            {title: '辅导教师编码', field: 'teacherSecondCode', visible: false, align: 'center', valign: 'middle'},
            {title: '辅导教师名称', field: 'teacherSecond', visible: true, align: 'center', valign: 'middle'},
            {title: '是否需要考前测试', field: 'needTest', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Class.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Class.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加班级管理
 */
Class.openAddClass = function () {
    var index = layer.open({
        type: 2,
        title: '添加班级',
        area: ['640px', '480px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/class/class_add'
    });
    layer.full(index);
    this.layerIndex = index;
};

/**
 * 打开查看班级详情
 */
Class.openClassDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '课程管理详情',
            area: ['640px', '480px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/class/class_update/' + Class.seItem.code
        });
        layer.full(index);
        this.layerIndex = index;
    }
};

/**
 * 删除课程管理
 */
Class.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/class/delete", function (data) {
            Feng.success("删除成功!");
            Class.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("classCode",this.seItem.code);
        ajax.start();
    }
};


/**
 *  打开入学测试设置
 */
Class.test = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/class/setting/test", function (data) {
            Feng.success("设置成功!");
            Class.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("classCode",this.seItem.code);
        ajax.start();
    }
};

/**
 * 查询课程管理列表
 */
Class.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Class.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Class.initColumn();
    var table = new BSTable(Class.id, "/class/list", defaultColunms);
    table.setPaginationType("server");
    Class.table = table.init();
});
