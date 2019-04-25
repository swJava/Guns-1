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
            {title: '用户名', field: 'userName', visible: true, align: 'center', valign: 'middle'},
            {title: '姓名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '性别', field: 'genderName', visible: true, align: 'center', valign: 'middle'},
            {title: '联系手机', field: 'mobileNumber', visible: true, align: 'center', valign: 'middle'},
            {title: '联系地址', field: 'address', visible: true, align: 'center', valign: 'middle'},
            {title: 'QQ号码', field: 'qq', visible: true, align: 'center', valign: 'middle'},
            {title: '微信号', field: 'weiixin', visible: true, align: 'center', valign: 'middle'},
            {title: '电子邮箱', field: 'email', visible: true, align: 'center', valign: 'middle'},
            {title: '加入时间', field: 'joinDate', visible: true, align: 'center', valign: 'middle'},
            {title: '状态 ', field: 'status', visible: true, align: 'center', valign: 'middle',
                formatter: function(value, row){
                    if (1 == value)
                        return '<input type="checkbox" class="js-switch" data-username="'+row.userName+'" checked />';
                    else
                        return '<input type="checkbox" class="js-switch" data-username="'+row.userName+'" />';
                }
            }
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
    layer.full(index);
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
        layer.full(index);
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
    queryData['beginQueryDate'] = $("#beginQueryDate").val();
    queryData['endQueryDate'] = $("#endQueryDate").val();
    queryData['status'] = $("#status").val();
    Member.table.refresh({query: queryData});
};

$(function () {

    var now = new Date();
    var year = now.getFullYear();
    var month = 1 + now.getMonth();
    if (month < 10)
        month = '0' + month;
    var day = now.getDate();
    if (day < 10)
        day = '0' + day;

    var today = year + '-' + month + '-' + day;

    var defaultColunms = Member.initColumn();
    var table = new BSTable(Member.id, "/member/list", defaultColunms);
    table.setPaginationType("server");
    table.setLoadSuccessCallback(function(){
        var switchers = Array.prototype.slice.call(document.querySelectorAll('.js-switch'));
        switchers.forEach(function(switcher) {
            var switchery = new Switchery(switcher, {
                size: 'small'
            });
            switcher.onchange = function(){
                var state = switcher.checked;
                console.log('<<< change member state' + $(switcher).attr('data-username') + ' : ' + state);

                var reqUrl = '';
                if (state){
                    // true 启用
                    reqUrl = Feng.ctxPath + "/member/resume";
                }else{
                    // false 停用
                    reqUrl = Feng.ctxPath + "/member/pause";
                }

                var ajax = new $ax(reqUrl, function (data) {
                }, function (data) {
                    Feng.error("操作失败!" + data.responseJSON.message + "!");
                });
                ajax.set("userName",$(switcher).attr('data-username'));
                ajax.start();

            }
        });
    });
    Member.table = table.init();

    laydate.render({elem: '#beginQueryDate', max: today});
    laydate.render({elem: '#endQueryDate', max: today});
});
