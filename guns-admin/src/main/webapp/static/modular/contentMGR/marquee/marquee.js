/**
 * 滚动广告管理初始化
 */
var Marquee = {
    id: "MarqueeTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Marquee.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '内容编码', field: 'code', visible: false, align: 'center', valign: 'middle'},
        {title: '类型', field: 'typeName', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '标题图片', field: 'timage', visible: true, align: 'center', valign: 'middle',
            formatter:function (value,row,index) {
                return '<img alt="row.title" class="img-circle" src="'+ row.timage +'" height="64px">';
            }
        },
        {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
        {title: '一句话简介', field: 'introduce', visible: true, align: 'center', valign: 'middle'},
        {title: '作者', field: 'author', visible: false, align: 'center', valign: 'middle', sortable: true},
        {title: '发布类型', field: 'publishTypeName', visible: false, align: 'center', valign: 'middle'},
        {title: '创建时间', field: 'createDate', visible: false, align: 'center', valign: 'middle'},
        {title: '下架时间', field: 'deadDate', visible: false, align: 'center', valign: 'middle', sortable: true},
    ];
};

/**
 * 检查是否选中
 */
Marquee.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Marquee.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加资讯管理
 */
Marquee.openAddContent = function () {
    var index = layer.open({
        type: 2,
        title: '内容编辑',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/content/marquee/marquee_add'
    });
    layer.full(index);
    this.layerIndex = index;
};

/**
 * 打开查看资讯管理详情
 */
Marquee.openMarqueeDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '资讯管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/content/marquee/marquee_update?contentCode=' + Marquee.seItem.code
        });
        layer.full(index);
        this.layerIndex = index;
    }
};

/**
 * 删除资讯管理
 */
Marquee.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/content/marquee/delete", function (data) {
            Feng.success("删除成功!");
            Marquee.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("contentId",this.seItem.id);
        ajax.start();
    }
};

$(function () {
    var defaultColunms = Marquee.initColumn();
    var table = new BSTable(Marquee.id, "/content/marquee/list", defaultColunms);
    //table.setPaginationType("server");
    Marquee.table = table.init();
});
