/**
 * 会员管理管理初始化
 */
var Member = {
    id: "MemberTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Member.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '主键标示', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '用户名', field: 'username', visible: true, align: 'center', valign: 'middle'},
            {title: '密码sha256加密', field: 'password', visible: true, align: 'center', valign: 'middle'},
            {title: '用户名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '性别: 1 男  2 女', field: 'gender', visible: true, align: 'center', valign: 'middle'},
            {title: '联系手机', field: 'mobileNumber', visible: true, align: 'center', valign: 'middle'},
            {title: '联系地址', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: 'QQ号码', field: 'qq', visible: true, align: 'center', valign: 'middle'},
            {title: '微信号', field: 'weiixin', visible: true, align: 'center', valign: 'middle'},
            {title: '电子邮箱', field: 'email', visible: true, align: 'center', valign: 'middle'},
            {title: '状态：  0 无效 1 有效  2 锁定 ', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '加入时间', field: 'joinDate', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Member.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Member.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加会员管理
 */
Member.openAddMember = function () {
    var index = layer.open({
        type: 2,
        title: '添加会员管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/member/member_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看会员管理详情
 */
Member.openMemberDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '会员管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/member/member_update/' + Member.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除会员管理
 */
Member.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/member/delete", function (data) {
            Feng.success("删除成功!");
            Member.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("memberId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询会员管理列表
 */
Member.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Member.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Member.initColumn();
    var table = new BSTable(Member.id, "/member/list", defaultColunms);
    table.setPaginationType("client");
    Member.table = table.init();
});
