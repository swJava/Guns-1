@/*
    头像参数的说明:
    label : 标签
    name : 名称
    id : 头像的id
    displayWidth : 显示宽度
    displayHeight : 显示高度
@*/
<div class="form-group">
    <label class="col-sm-3 control-label head-scu-label">${label}</label>
    <div class="col-sm-4">
        <div id="${name}PreId">
            <div><img width="${displayWidth!100}px" height="${displayHeight!100}px" src="${ctxPath}/attachment/view/icon/${id}"></div>
        </div>
    </div>
    <div class="col-sm-2">
        <div class="head-scu-btn upload-btn" id="${name}BtnId">
            <i class="fa fa-upload"></i>&nbsp;上传
        </div>
    </div>
    <input type="hidden" id="masterCode" value=""/>
    <input type="hidden" id="masterName" value=""/>
</div>
@if(isNotEmpty(underline) && underline == 'true'){
    <div class="hr-line-dashed"></div>
@}


