/**
 * 栏目行为管理初始化
 */
var ColumnAction = {
    id: "ColumnActionTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ColumnAction.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '栏目编码', field: 'columnCode', visible: true, align: 'center', valign: 'middle'},
            {title: '动作名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '类型', field: 'type', visible: true, align: 'center', valign: 'middle'},
            {title: '功能', field: 'action', visible: true, align: 'center', valign: 'middle'},
            {title: '格式数据', field: 'data', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ColumnAction.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ColumnAction.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加栏目行为
 */
ColumnAction.openAddColumnAction = function () {
    var index = layer.open({
        type: 2,
        title: '添加栏目行为',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/columnAction/columnAction_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看栏目行为详情
 */
ColumnAction.openColumnActionDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '栏目行为详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/columnAction/columnAction_update/' + ColumnAction.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除栏目行为
 */
ColumnAction.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/columnAction/delete", function (data) {
            Feng.success("删除成功!");
            ColumnAction.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("columnActionId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询栏目行为列表
 */
ColumnAction.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ColumnAction.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ColumnAction.initColumn();
    var table = new BSTable(ColumnAction.id, "/columnAction/list", defaultColunms);
    table.setPaginationType("server");
    ColumnAction.table = table.init();
});
