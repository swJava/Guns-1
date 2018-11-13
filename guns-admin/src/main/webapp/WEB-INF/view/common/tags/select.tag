@/*
    select标签中各个参数的说明:
    name : select的名称(必填)
    id : select的id(必填)
    underline : 是否带分割线
    dictType : 字典类型(必填)
    required : 是否必填
    clickFun : 事件
    disabled : 不可选
    value : 值(不填默认空)
@*/

@if(isEmpty(value)){
@   value = "";
@}
<div class="form-group">
    <label class="col-sm-3 control-label">${name}</label>
    <div class="col-sm-9">
        <select class="form-control" id="${id}" name="${id}"
                @if(isNotEmpty(required)){
                    required="${required}"
                @}
                @if(isNotEmpty(clickFun)){
                    onclick="${clickFun}"
                @}
                @if(isNotEmpty(disabled)){
                    disabled="${disabled}"
                @}
                @if(isNotEmpty(readonly)){
                readonly="${readonly}"
                @}
        >
            <option value="" >请选择</option>
            @if(isNotEmpty(dictType)){
                @for(item in bind.findInDict(dictType)){
                    <option value="${item.num}">${item.name}</option>
                @}
            @}else{
                ${tagBody!}
            @}
        </select>
        @if(isNotEmpty(hidden)){
            <input class="form-control" type="hidden" id="${hidden}" value="${hiddenValue!}" name="${id}">
        @}
    </div>
</div>
@if(isNotEmpty(underline) && underline == 'true'){
    <div class="hr-line-dashed"></div>
@}

<script type="text/javascript">
    <%--修改回显初始化--%>
    if(Feng.isNotEmpty(${value})){
        $('#${id}').val(${value});
    }
</script>
