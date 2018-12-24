package com.stylefeng.guns.modular.system.model;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/12/24 23:41
 * @Version 1.0
 */
public enum ContentTypeEnum {

    Normal(1, "普通文章"),
    LinkArticle(2, "外链文章"),
    LinkVideo(3, "外链视频")
    ;

    public int code;
    public String text;

    ContentTypeEnum(int code, String text){
        this.code = code;
        this.text = text;
    }
}
