/**
 * 资讯管理管理初始化
 */
var Collector = {
    Content: {
        id: "ArticleTable",	//表格id
        seItem: null,		//选中的条目
        table: null,
        layerIndex: -1
    }
};

/**
 * 初始化表格的列
 */
Collector.Content.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '内容编码', field: 'code', visible: false, align: 'center', valign: 'middle'},
        {title: '类型', field: 'typeName', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '标题图片', field: 'timage', visible: true, align: 'center', valign: 'middle',
            formatter:function (value,row,index) {
                return '<img alt="image" class="img-circle" src="'+Feng.ctxPath+'/attachment/download?masterName=Content&masterCode='+row.id+'" width="64px" height="64px">';
            }
        },
        {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
        {title: '一句话简介', field: 'introduce', visible: true, align: 'center', valign: 'middle'},
        {title: '作者', field: 'author', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '发布类型', field: 'publishTypeName', visible: false, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createDate', visible: true, align: 'center', valign: 'middle'},
        {title: '下架时间', field: 'deadDate', visible: false, align: 'center', valign: 'middle', sortable: true},
    ];
};

/**
 * 检查是否选中
 */
Collector.Content.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Collector.Content.seItem = selected[0];
        return true;
    }
};

/**
 * 查询资讯管理列表
 */
Collector.Content.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Collector.Content.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Collector.Content.initColumn();
    var table = new BSTable(Collector.Content.id, "/content/list", defaultColunms);
    table.setPaginationType("server");
    Collector.Content.table = table.init();
});
