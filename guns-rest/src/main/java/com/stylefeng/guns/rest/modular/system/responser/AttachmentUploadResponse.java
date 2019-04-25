package com.stylefeng.guns.rest.modular.system.responser;

import com.stylefeng.guns.rest.core.Responser;
import com.stylefeng.guns.rest.core.SimpleResponser;
import com.stylefeng.guns.rest.modular.member.responser.RegistResponse;
import io.swagger.annotations.ApiModel;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by 罗华.
 */
@ApiModel(value = "AttachmentUploadResponse", description = "附件上传结果")
public class AttachmentUploadResponse extends SimpleResponser {
    private static final long serialVersionUID = -2747717740531572791L;

    private List<Long> datas = new ArrayList<Long>();

    public List<Long> getDatas() {
        return datas;
    }

    public void setDatas(List<Long> datas) {
        this.datas = datas;
    }

    public static Responser me(String ids) {

        AttachmentUploadResponse response = new AttachmentUploadResponse();
        response.setCode(SUCCEED);

        StringTokenizer idTokenizer = new StringTokenizer(ids, ",");
        while(idTokenizer.hasMoreTokens()){
            String attachId = idTokenizer.nextToken();
            try{
                response.addData(Long.parseLong(attachId));
            }catch(Exception e){
                response.addData(null);
            }
        }
        return response;
    }

    private void addData(Long parseLong) {
        this.datas.add(parseLong);
    }
}
