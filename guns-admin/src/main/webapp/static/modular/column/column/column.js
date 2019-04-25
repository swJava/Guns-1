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
            {title: '图标', field: 'icon', visible: false, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    return '<img alt="image" class="img-circle" src="'+Feng.ctxPath+'/attachment/download?masterName=Column&masterCode='+row.id+'" width="64px" height="64px">';
                }
            },
            {title: '栏目名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '栏目编码', field: 'code', visible: false, align: 'center', valign: 'middle'},
            {title: '父级栏目', field: 'pcode', visible: true, align: 'center', valign: 'middle'},
            {title: '祖先栏目', field: 'pcodes', visible: false, align: 'center', valign: 'middle'},
            {title: '状态', field: 'status', visible: false, align: 'center', valign: 'middle',
                formatter: function(value, row){
                    if (1 == value)
                        return '<input type="checkbox" class="js-switch" data-code="'+row.code+'" checked />';
                    else
                        return '<input type="checkbox" class="js-switch" data-code="'+row.code+'" />';
                }
            }
    ];
};


/**
 * 检查是否选中
 */
Column.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
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
        area: ['640px', '480px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/column/column_add'
    });
    layer.full(index);
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
            area: ['640px', '480px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/column/column_update/' + Column.seItem.id
        });
        layer.full(index);
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


/**
 * 添加文章
 */
Column.openCollector = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '文章列表',
            area: ['640px', '480px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/column/collector/' + Column.seItem.code
        });
        layer.full(index);
        this.layerIndex = index;
    }
};

Column.doUpdate = function(reqUrl, data){
    var ajax = new $ax(reqUrl, function (data) {
    }, function (data) {
        Feng.error("操作失败!" + data.responseJSON.message + "!");
    });

    ajax.setData(data);
    ajax.start();
};

Column.initSwitcher = function(selector, options){
    var switchers = Array.prototype.slice.call(document.querySelectorAll(selector));

    switchers.forEach(function(switcher) {
        var switchery = new Switchery(switcher, {
            size: 'small'
        });
        switcher.onchange = function(){
            var state = switcher.checked;

            var reqUrl = '';
            var postData = {
                classCode : $(switcher).attr('data-code')
            };

            if (state){
                // true 启用
                reqUrl = options.resume.url;
            }else{
                // false 停用
                reqUrl = options.pause.url;
            }
            Column.doUpdate(reqUrl, postData);
        }
    });
};

$(function () {
    var defaultColunms = Column.initColumn();
    //var table = new BSTable(Column.id, "/column/list", defaultColunms);
    var table = new BSTreeTable(Column.id, "/column/list", defaultColunms);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("code");
    table.setParentCodeField("pcode");
    table.setExpandAll(true);
    table.setRootCodeValue('LM000000');
    table.setLoadSuccessCallback(function(){
        // 初始化状态开关
        console.log('<<< init switch');
        Column.initSwitcher('.js-switch', {
            pause: {
                url: Feng.ctxPath + "/column/stop"
            },
            resume: {
                url: Feng.ctxPath + "/column/resume"
            }
        });

    });
    table.init();
    Column.table = table;
});
