/**
 * 教师管理管理初始化
 */
var Teacher = {
    id: "TeacherTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Teacher.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: '头像', field: 'avatar', visible: true, align: 'center', valign: 'middle', sortable: true,
            formatter:function (value,row,index) {
                return '<img alt="image" class="img-circle" src="'+Feng.ctxPath+'/attachment/download?masterName=Teacher&masterCode='+row.code+'" width="64px" height="64px">';
            }
        },
        {title: '教师编码', field: 'code', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '教师名称', field: 'name', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '教师类型', field: 'typeName', visible: true, align: 'center', valign: 'middle'},
        {title: '性别', field: 'genderName', visible: true, align: 'center', valign: 'middle'},
        {title: '毕业院校', field: 'graduate', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '授课年级', field: 'gradeName', visible: true, align: 'center', valign: 'middle'},
        //{title: '教学成果', field: 'havest', visible: true, align: 'center', valign: 'middle', sortable: true},
        //{title: '教学经验', field: 'experience', visible: true, align: 'center', valign: 'middle', sortable: true},
        //{title: '教学特点', field: 'feature', visible: true, align: 'center', valign: 'middle', sortable: true},
        {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle',
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
Teacher.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Teacher.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加教师管理
 */
Teacher.openAddTeacher = function () {
    var index = layer.open({
        type: 2,
        title: '添加教师管理',
        area: ['640px', '500px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/teacher/teacher_add'
    });
    layer.full(index);
    this.layerIndex = index;
};

/**
 * 打开查看教师管理详情
 */
Teacher.openTeacherDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '教师管理详情',
            area: ['640px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/teacher/teacher_update/' + Teacher.seItem.id
        });
        layer.full(index);
        this.layerIndex = index;
    }
};

/**
 * 删除教师管理
 */
Teacher.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/teacher/delete", function (data) {
            Feng.success("删除成功!");
            Teacher.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("code",this.seItem.code);
        ajax.start();
    }
};

/**
 * 查询教师管理列表
 */
Teacher.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    queryData['status'] = $("#status").val();
    Teacher.table.refresh({query: queryData});
};

Teacher.doUpdate = function(reqUrl, data){
    var ajax = new $ax(reqUrl, function (data) {
    }, function (data) {
        Feng.error("操作失败!" + data.responseJSON.message + "!");
    });

    ajax.setData(data);
    ajax.start();
}

$(function () {
    var defaultColunms = Teacher.initColumn();
    var table = new BSTable(Teacher.id, "/teacher/list", defaultColunms);
    table.setPaginationType("server");
    table.setLoadSuccessCallback(function(){
        var switchers = Array.prototype.slice.call(document.querySelectorAll('.js-switch'));

        switchers.forEach(function(switcher) {
            var switchery = new Switchery(switcher, {
                size: 'small'
            });
            switcher.onchange = function(){
                var state = switcher.checked;
                console.log('<<< change teacher state' + $(switcher).attr('data-code') + ' : ' + state);

                var reqUrl = '';
                var postData = {
                    code : $(switcher).attr('data-code')
                };

                if (state){
                    // true 启用
                    reqUrl = Feng.ctxPath + "/teacher/resume";
                }else{
                    // false 停用
                    reqUrl = Feng.ctxPath + "/teacher/pause";
                }

                Teacher.doUpdate(reqUrl, postData);
            }
        });
    });
    Teacher.table = table.init();
});
