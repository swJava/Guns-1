@/*
    头像参数的说明:
    label : 标签
    name : 名称
    id : 头像的id
@*/
<div class="form-group">
    <label class="col-sm-3 control-label head-scu-label">${label}</label>
    <div class="col-sm-4">
        <div id="${name}PreId">
            <div><img width="100px" height="100px" src="${ctxPath}/attachment/view/icon/${id}"></div>
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


