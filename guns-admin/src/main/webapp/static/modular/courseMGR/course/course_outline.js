/**
 * 初始化字典详情对话框
 */
var CourseOutlineInfoDlg = {
    count: $("#itemSize").val(),
    course: '',
    outlines: new Array(),
    itemTemplate: $("#itemTemplate").html()
};

/**
 * item获取新的id
 */
CourseOutlineInfoDlg.newId = function () {
    if(this.count == undefined){
        this.count = 0;
    }
    this.count = this.count + 1;
    return "courseOutlineItem" + this.count;
};

/**
 * 关闭此对话框
 */
CourseOutlineInfoDlg.close = function () {
    parent.layer.close(window.parent.Course.layerIndex);
};

/**
 * 添加条目
 */
CourseOutlineInfoDlg.addItem = function () {
    $("#itemsArea").append(this.itemTemplate);
    $("#courseOutlineItem").attr("id", this.newId());
};

/**
 * 删除item
 */
CourseOutlineInfoDlg.deleteItem = function (event) {
    var obj = Feng.eventParseObject(event);
    obj = obj.is('button') ? obj : obj.parent();
    obj.parent().parent().remove();
};

/**
 * 清除为空的item Dom
 */
CourseOutlineInfoDlg.clearNullDom = function(){
    $("[name='courseOutlineItem']").each(function(){
        var sort = $(this).find("[name='sort']").val();
        var outline = $(this).find("[name='outline']").val();
        if(sort == '' || outline == ''){
            $(this).remove();
        }
    });
};

/**
 * 收集添加字典的数据
 */
CourseOutlineInfoDlg.collectData = function () {
    this.clearNullDom();
    var mutiString = "";
    var outlines = new Array();
    $("[name='courseOutlineItem']").each(function(){
        var item = new Object();
        /*var outline = $(this).find("[name='outline']").val();
        var description = $(this).find("[name='description']").val();
        var sort = $(this).find("[name='sort']").val();
        mutiString = mutiString + (code + ":" + name + ":"+ num+";");*/
        item.outline = $(this).find("[name='outline']").val();
        item.sort = $(this).find("[name='sort']").val();
        item.description = $(this).find("[name='description']").val();
        outlines.push(item);
    });
    this.mutiString = mutiString;
    this.outlines = outlines;
    this.course = $('#courseCode').val();
};


/**
 * 提交添加字典
 */
CourseOutlineInfoDlg.saveSubmit = function () {
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/course/outline/save?course=" + this.course, function (data) {
        Feng.success("添加成功!");
        //window.parent.Dict.table.refresh();
        //DictInfoDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.setData(JSON.stringify(this.outlines));
    ajax.setContentType('application/json');
    ajax.start();
};
