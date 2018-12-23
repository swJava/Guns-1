/**
 * 学生管理管理初始化
 */
var Student = {
    id: "StudentTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Student.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '头像', field: 'avatar', visible: true, align: 'center', valign: 'middle',
                formatter:function (value,row,index) {
                    /*var imgUrl;
                    if(row.avatar != null && row.avatar != ''){
                        imgUrl = '<img alt="image" class="img-circle" src="/kaptcha/'+ row.avatar +'" width="64px" height="64px">';
                    }else {
                        imgUrl = '<img alt="image" class="img-circle" src="/static/img/swiming.png" width="64px" height="64px">';
                    }*/
                    return '<img alt="image" class="img-circle" src="/attachment/download?masterName=Student&masterCode='+row.id+'" width="64px" height="64px">';
                }
            },
            {title: '学员编码', field: 'code', visible: true, align: 'center', valign: 'middle', sortable: true},
            {title: '学员名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '性别', field: 'genderName', visible: true, align: 'center', valign: 'middle'},
            {title: '在读年级', field: 'gradeName', visible: true, align: 'center', valign: 'middle'},
            {title: '在读学校', field: 'school', visible: true, align: 'center', valign: 'middle'},
            {title: '目标学校', field: 'targetSchool', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'statusName', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Student.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Student.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加学生管理
 */
Student.openAddStudent = function () {
    var index = layer.open({
        type: 2,
        title: '添加学生管理',
        area: ['850px', '650px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/student/student_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看学生管理详情
 */
Student.openStudentDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '学生管理详情',
            area: ['850px', '650px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/student/student_update/' + Student.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除学生管理
 */
Student.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/student/delete", function (data) {
            Feng.success("删除成功!");
            Student.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("studentId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询学生管理列表
 */
Student.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Student.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Student.initColumn();
    var table = new BSTable(Student.id, "/student/list", defaultColunms);
    table.setPaginationType("server");
    Student.table = table.init();
});
