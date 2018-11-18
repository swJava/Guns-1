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
            {title: '班级编码', field: 'code', visible: true, align: 'center', valign: 'middle'},
            {title: '班级名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '课时数', field: 'period', visible: true, align: 'center', valign: 'middle'},
            {title: '授课教室', field: 'classRoom', visible: true, align: 'center', valign: 'middle'},
            {title: '教授课程', field: 'courseCode', visible: true, align: 'center', valign: 'middle'},
            {title: '课程名称', field: 'courseName', visible: true, align: 'center', valign: 'middle'},
            {title: '关注度', field: 'star', visible: true, align: 'center', valign: 'middle'},
            {title: '剩余报名人数', field: 'quato', visible: true, align: 'center', valign: 'middle'},
            {title: '报名截止时间', field: 'signEndDate', visible: true, align: 'center', valign: 'middle'},
            {title: '开课起始日期', field: 'beginDate', visible: true, align: 'center', valign: 'middle'},
            {title: '开课结束日期', field: 'endDate', visible: true, align: 'center', valign: 'middle'},
            {title: '开课时间类型', field: 'studyTimeTypeName', visible: true, align: 'center', valign: 'middle'},
            {title: '开课时间', field: 'studyTimeValue', visible: true, align: 'center', valign: 'middle'},
            {title: '开始时间', field: 'beginTime', visible: true, align: 'center', valign: 'middle'},
            {title: '结束时间', field: 'endTime', visible: true, align: 'center', valign: 'middle'},
            {title: '分钟', field: 'duration', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'statusName', visible: true, align: 'center', valign: 'middle'}
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
 * 点击添加课程大纲管理
 */
Class.openAddClassKCDG = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '添加课程大纲管理',
            area: ['1200px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/class/class_add_kcdg/' + Class.seItem.code + "/" + Class.seItem.courseCode
        });
        this.layerIndex = index;
    }
};
/**
 * 点击添加课程管理
 */
Class.openAddClass = function () {
    var index = layer.open({
        type: 2,
        title: '添加课程管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/class/class_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看课程管理详情
 */
Class.openClassDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '课程管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/class/class_update/' + Class.seItem.id
        });
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
        ajax.set("classId",this.seItem.id);
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
