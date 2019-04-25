/**
 * 课程管理管理初始化
 */
var Sign = {
    id: "ClassTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Sign.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'ID', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '班级编码', field: 'code', visible: false, align: 'center', valign: 'middle'},
        {title: '班级名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '年级', field: 'gradeName', visible: false, align: 'center', valign: 'middle'},
        {title: '学期', field: 'cycle', visible: false, align: 'center', valign: 'middle'},
        {title: '班次', field: 'ability', visible: false, align: 'center', valign: 'middle'},
        {title: '开课起始日期', field: 'beginDate', visible: true, align: 'center', valign: 'middle'},
        {title: '开课结束日期', field: 'endDate', visible: false, align: 'center', valign: 'middle'},
        {title: '单节时长(分钟)', field: 'duration', visible: false, align: 'center', valign: 'middle'},
        {title: '总课时数', field: 'period', visible: false, align: 'center', valign: 'middle'},
        {title: '教室编码', field: 'classRoomCode', visible: false, align: 'center', valign: 'middle'},
        {title: '教室', field: 'classRoom', visible: true, align: 'center', valign: 'middle'},
        {title: '教授课程', field: 'courseCode', visible: false, align: 'center', valign: 'middle'},
        {title: '课程名称', field: 'courseName', visible: false, align: 'center', valign: 'middle'},
        {title: '关注度', field: 'star', visible: false, align: 'center', valign: 'middle'},
        {title: '价格(元)', field: 'price', visible: true, align: 'center', valign: 'middle'},
        {title: '学员人数', field: 'quato', visible: true, align: 'center', valign: 'middle'},
        {title: '报名截止时间', field: 'signStartDate', visible: true, align: 'center', valign: 'middle'},
        {title: '报名截止时间', field: 'signEndDate', visible: true, align: 'center', valign: 'middle'},
        {title: '状态', field: 'statusName', visible: false, align: 'center', valign: 'middle'},
        {title: '主讲教师编码', field: 'teacherCode', visible: false, align: 'center', valign: 'middle'},
        {title: '主讲教师名称', field: 'teacher', visible: true, align: 'center', valign: 'middle'},
        {title: '辅导教师编码', field: 'teacherSecondCode', visible: false, align: 'center', valign: 'middle'},
        {title: '辅导教师名称', field: 'teacherSecond', visible: true, align: 'center', valign: 'middle'},
        {title: '是否需要考前测试', field: 'needTest', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Sign.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Sign.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加订单管理
 */
Sign.openImportDlg = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '导入学员',
            area: ['480px', '200px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/order/sign/sign_import/' + Sign.seItem.code
        });
        this.layerIndex = index;
    }
};

/**
 * 查询课程管理列表
 */
Sign.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Sign.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Sign.initColumn();
    var table = new BSTable(Sign.id, "/order/sign/classlist", defaultColunms);
    table.setPaginationType("server");
    Sign.table = table.init();
});
