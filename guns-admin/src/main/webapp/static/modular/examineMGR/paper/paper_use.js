/**
 * 初始化试卷应用详情对话框
 */
var PaperUseDlg = {
    itemTemplate: $("#itemTemplate").html()
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
    console.log('<<< begin add item');

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
        var ability = $(this).find("[name='ability']").val();
        var cycle = $(this).find("[name='cycle']").val();
        var examTime = $(this).find("[name='examTime']").val();
        var passScore = $(this).find("[name='passScore']").val();
        if(ability == '' || cycle == ''){
            $(this).remove();
        }

        var examTimeValue = parseInt(examTime, 10);
        var passScoreValue = parseInt(passScore, 10);
        if (isNaN(examTimeValue) || isNaN(passScoreValue))
            $(this).remove();

    });
};

/**
 * 收集添加字典的数据
 */
PaperUseDlg.collectData = function () {
    this.clearNullDom();
    var applyDatas = new Array();
    $("[name='dictItem']").each(function(){
        var ability = $(this).find("[name='ability']").val();
        var cycle = $(this).find("[name='cycle']").val();
        var examTime = $(this).find("[name='examTime']").val();
        var passScore = $(this).find("[name='passScore']").val();

        applyDatas.push({
            ability: ability,
            cycle: cycle,
            examTime: examTime,
            passScore: passScore
        });
    });
    this.paperCode = $("#paperCode").val();
    this.mutiString = JSON.stringify(applyDatas);
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

    ajax.set('paperCode',this.paperCode);
    ajax.set('applyItems',this.mutiString);
    ajax.start();
};
