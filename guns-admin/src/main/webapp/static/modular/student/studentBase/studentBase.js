/**
 * 学生管理管理初始化
 */
var StudentBase = {
    id: "StudentBaseTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
StudentBase.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '自增主键', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '姓名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '账号类型', field: 'type', visible: false, align: 'center', valign: 'middle'},
            {title: '账号类型', field: 'typeName', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'timeCreate', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'timeUpdate', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
StudentBase.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        StudentBase.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加学生管理
 */
StudentBase.openAddStudentBase = function () {
    var index = layer.open({
        type: 2,
        title: '添加学生管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/studentBase/studentBase_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看学生管理详情
 */
StudentBase.openStudentBaseDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '学生管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/studentBase/studentBase_update/' + StudentBase.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除学生管理
 */
StudentBase.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/studentBase/delete", function (data) {
            Feng.success("删除成功!");
            StudentBase.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("studentBaseId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询学生管理列表
 */
StudentBase.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    StudentBase.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = StudentBase.initColumn();
    var table = new BSTable(StudentBase.id, "/studentBase/list", defaultColunms);
    table.setPaginationType("server");
    StudentBase.table = table.init();
});
