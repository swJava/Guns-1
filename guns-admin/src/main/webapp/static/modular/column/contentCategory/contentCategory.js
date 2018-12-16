/**
 * 关系栏目内容管理初始化
 */
var ContentCategory = {
    id: "ContentCategoryTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
ContentCategory.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '栏目编码', field: 'columnCode', visible: true, align: 'center', valign: 'middle'},
            {title: '栏目名称', field: 'columnName', visible: true, align: 'center', valign: 'middle'},
            {title: '内容编码', field: 'contentCode', visible: true, align: 'center', valign: 'middle'},
            {title: '文章标题', field: 'contentName', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'statusName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
ContentCategory.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        ContentCategory.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加关系栏目内容
 */
ContentCategory.openAddContentCategory = function () {
    var index = layer.open({
        type: 2,
        title: '添加关系栏目内容',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/contentCategory/contentCategory_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看关系栏目内容详情
 */
ContentCategory.openContentCategoryDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '关系栏目内容详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/contentCategory/contentCategory_update/' + ContentCategory.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除关系栏目内容
 */
ContentCategory.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/contentCategory/delete", function (data) {
            Feng.success("删除成功!");
            ContentCategory.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("contentCategoryId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询关系栏目内容列表
 */
ContentCategory.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    ContentCategory.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = ContentCategory.initColumn();
    var table = new BSTable(ContentCategory.id, "/contentCategory/list", defaultColunms);
    table.setPaginationType("server");
    ContentCategory.table = table.init();
});
