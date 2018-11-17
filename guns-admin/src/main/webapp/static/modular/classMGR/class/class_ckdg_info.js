/**
 * 初始化字典详情对话框
 */
var CourseOutlineDlg = {
    count: $("#itemSize").val(),
    outline: '',		//大纲条目
    classDate: '',      //排课日期
    classTime: '',      //上课时间
    mutiString: '',		//拼接字符串内容(拼接字典条目)
    itemTemplate: $("#itemTemplate").html()
};

/**
 * item获取新的id
 */
CourseOutlineDlg.newId = function () {
    if(this.count == undefined){
        this.count = 0;
    }
    this.count = this.count + 1;
    return "courseOutline" + this.count;
};

/**
 * 关闭此对话框
 */
CourseOutlineDlg.close = function () {
    parent.layer.close(window.parent.Dict.layerIndex);
};

/**
 * 添加条目
 */
CourseOutlineDlg.addItem = function () {
    $("#itemsArea").append(this.itemTemplate);
    $("#courseOutline").attr("id", this.newId());
};

/**
 * 删除item
 */
CourseOutlineDlg.deleteItem = function (event) {
    var obj = Feng.eventParseObject(event);
    obj = obj.is('button') ? obj : obj.parent();
    obj.parent().parent().remove();
};

/**
 * 清除为空的item Dom
 */
CourseOutlineDlg.clearNullDom = function(){
    $("[name='courseOutline']").each(function(){
        var num = $(this).find("[name='sort']").val();
        var name = $(this).find("[name='sort']").val();
        if(num == '' || name == ''){
            $(this).remove();
        }
    });
};

/**
 * 收集添加字典的数据
 */
CourseOutlineDlg.collectData = function () {
    this.clearNullDom();
    var mutiString = "";
    $("[name='courseOutline']").each(function(){
        var outline = $(this).find("[name='outline']").val();
        var classDate = $(this).find("[name='classDate']").val();
        var classTime = $(this).find("[name='classTime']").val();
        var sort = $(this).find("[name='sort']").val();
        mutiString = mutiString + (code + ":" + name + ":"+ num+";");
    });
    this.outline = $("#outline").val();
    this.classDate = $("#classDate").val();
    this.classTime = $("#classTime").val();
    this.sort = $("#sort").val();
    this.mutiString = mutiString;
};


/**
 * 提交添加字典
 */
CourseOutlineDlg.addSubmit = function () {
    this.collectData();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/dict/add", function (data) {
        Feng.success("添加成功!");
        window.parent.Class.table.refresh();
        CourseOutlineDlg.close();
    }, function (data) {
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set('outline',this.outline);
    ajax.set('classDate',this.classDate);
    ajax.set('classTime',this.classTime);
    ajax.set('dictValues',this.mutiString);
    ajax.start();
};

/**
 * 提交修改
 */
CourseOutlineDlg.editSubmit = function () {
    this.collectData();
    var ajax = new $ax(Feng.ctxPath + "/dict/update", function (data) {
        Feng.success("修改成功!");
        window.parent.Class.table.refresh();
        CourseOutlineDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set('dictId',$("#dictId").val());
    ajax.set('outline',this.outline);
    ajax.set('classDate',this.classDate);
    ajax.set('classTime',this.classTime);
    ajax.set('dictValues',this.mutiString);
    ajax.start();
};
