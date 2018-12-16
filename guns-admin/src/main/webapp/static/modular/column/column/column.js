/**
 * 栏目管理初始化
 */
var Column = {
    id: "ColumnTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Column.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '图标', field: 'icon', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    console.log(row.icon)
                    var imgUrl;
                    if(row.icon != null && row.icon != ''){
                        imgUrl = '<img alt="image" class="img-circle" src="/kaptcha/'+ row.icon +'" width="64px" height="64px">';
                    }else {
                        imgUrl = '<img alt="image" class="img-circle" src="/static/img/swiming.png" width="64px" height="64px">';
                    }
                    return imgUrl;
                }
            },
            {title: '栏目编码', field: 'code', visible: true, align: 'center', valign: 'middle'},
            {title: '栏目名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '父级栏目', field: 'pcode', visible: true, align: 'center', valign: 'middle'},
            {title: '祖先栏目', field: 'pcodes', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'statusName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Column.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Column.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加栏目
 */
Column.openAddColumn = function () {
    var index = layer.open({
        type: 2,
        title: '添加栏目',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/column/column_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看栏目详情
 */
Column.openColumnDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '栏目详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/column/column_update/' + Column.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除栏目
 */
Column.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/column/delete", function (data) {
            Feng.success("删除成功!");
            Column.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("columnId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询栏目列表
 */
Column.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Column.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Column.initColumn();
    var table = new BSTable(Column.id, "/column/list", defaultColunms);
    table.setPaginationType("server");
    Column.table = table.init();
});
