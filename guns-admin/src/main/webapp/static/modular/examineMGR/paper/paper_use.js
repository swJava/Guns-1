/**
 * 初始化试卷应用详情对话框
 */
var PaperUseDlg = {

};

/**
 * item获取新的id
 */
PaperUseDlg.newId = function () {
    if(this.count == undefined){
        this.count = 0;
    }
    this.count = this.count + 1;
    return "dictItem" + this.count;
};

/**
 * 关闭此对话框
 */
PaperUseDlg.close = function () {
    parent.layer.close(window.parent.Paper.layerIndex);
};

/**
 * 添加条目
 */
PaperUseDlg.addItem = function () {
    $("#itemsArea").append(this.itemTemplate);
    $("#dictItem").attr("id", this.newId());
};

/**
 * 删除item
 */
PaperUseDlg.deleteItem = function (event) {
    var obj = Feng.eventParseObject(event);
    obj = obj.is('button') ? obj : obj.parent();
    obj.parent().parent().remove();
};

/**
 * 清除为空的item Dom
 */
PaperUseDlg.clearNullDom = function(){
    $("[name='dictItem']").each(function(){
        var examTime = $(this).find("[name='examTime']").val();
        var passScore = $(this).find("[name='passScore']").val();
        if(num == '' || name == ''){
            $(this).remove();
        }
    });
};

/**
 * 收集添加字典的数据
 */
PaperUseDlg.collectData = function () {
    this.clearNullDom();
    var mutiString = "";
    $("[name='dictItem']").each(function(){
        var code = $(this).find("[name='itemCode']").val();
        var name = $(this).find("[name='itemName']").val();
        var num = $(this).find("[name='itemNum']").val();
        mutiString = mutiString + (code + ":" + name + ":"+ num+";");
    });
    this.dictName = $("#dictName").val();
    this.dictCode = $("#dictCode").val();
    this.dictTips = $("#dictTips").val();
    this.mutiString = mutiString;
};

/**
 * 提交修改
 */
PaperUseDlg.doSubmit = function () {
    this.collectData();
    var ajax = new $ax(Feng.ctxPath + "/examine/paper/use", function (data) {
        Feng.success("修改成功!");
        PaperUseDlg.close();
    }, function (data) {
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });

    ajax.start();
};
