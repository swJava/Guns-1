package com.stylefeng.guns.modular.system.transfer;

import com.stylefeng.guns.common.exception.ServiceException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * @Description //TODO
 * @Author 罗华
 * @Date 2018/11/19 15:52
 * @Version 1.0
 */
public class AttachmentInfo {
    Long[] ids = new Long[0];
    String[] categories = new String[0];
    String[] types = new String[0];
    String[] orgNames = new String[0];
    String[] filenames = new String[0];
    Long[] sizes = new Long[0];
    List<byte[]> contents;
    String[] descriptions = new String[0];
    String[] paths = new String[0];

    public AttachmentInfo() {
        ids = new Long[0];
        categories = new String[0];
        types = new String[0];
        orgNames = new String[0];
        contents = new ArrayList<byte[]>();
    }

    public static AttachmentInfo fromUpload(MultipartFile[] files) {
        if (null == files || files.length == 0)
            return null;

        AttachmentInfo uploadInfo = new AttachmentInfo();

        for(MultipartFile file : files){
            if (null == file)
                continue;

            try {
                uploadInfo.addContent(file.getBytes());
                uploadInfo.parseType(file.getContentType());
                uploadInfo.addOrgNames(file.getOriginalFilename());
                uploadInfo.addSize(file.getSize());
                uploadInfo.addDescription(file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return uploadInfo;
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public void addId(Long id){
        Long[] ids = new Long[this.ids.length + 1];
        System.arraycopy(this.ids, 0, ids, 0, this.ids.length);

        ids[this.ids.length] = id;

        this.ids = ids;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public void addCategory(String category){
        String[] categories = new String[this.categories.length + 1];
        System.arraycopy(this.categories, 0, categories, 0, this.categories.length);

        categories[this.categories.length] = category;

        this.categories = categories;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public void addType(String type) {
        String[] types = new String[this.types.length + 1];
        System.arraycopy(this.types, 0, types, 0, this.types.length);

        types[this.types.length] = type;

        this.types = types;
    }

    public String[] getOrgNames() {
        return orgNames;
    }

    public void setOrgNames(String[] orgNames) {
        this.orgNames = orgNames;
    }

    public void addOrgNames(String orgName) {
        String[] names = new String[orgNames.length + 1];
        System.arraycopy(this.orgNames, 0, names, 0, this.orgNames.length);

        names[this.orgNames.length] = orgName;

        this.orgNames = names;
    }

    public String[] getFilenames() {
        return filenames;
    }

    public void setFilenames(String[] filenames) {
        this.filenames = filenames;
    }

    public void addFilename(String filename){
        String[] filenames = new String[this.filenames.length + 1];
        System.arraycopy(this.filenames, 0, filenames, 0, this.filenames.length);

        filenames[this.filenames.length] = filename;

        this.filenames = filenames;
    }

    public Long[] getSizes() {
        return sizes;
    }

    public void setSizes(Long[] sizes) {
        this.sizes = sizes;
    }

    public void addSize(Long size) {
        Long[] sizes = new Long[this.sizes.length + 1];
        System.arraycopy(this.sizes, 0, sizes, 0, this.sizes.length);

        sizes[this.sizes.length] = size;

        this.sizes = sizes;
    }

    public List<byte[]> getContents() {
        return contents;
    }

    public void setContents(List<byte[]> contents) {
        this.contents = contents;
    }

    public void addContent(byte[] content) {
        contents.add(content);
    }

    public String[] getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String[] descriptions) {
        this.descriptions = descriptions;
    }

    public void addDescription(String desc) {
        String[] descriptions = new String[this.descriptions.length + 1];
        System.arraycopy(this.descriptions, 0, descriptions, 0, this.descriptions.length);

        descriptions[this.descriptions.length] = desc;

        this.descriptions = descriptions;
    }

    public String[] getPaths() {
        return paths;
    }

    public void setPaths(String[] paths) {
        this.paths = paths;
    }

    public void addPath(String path){
        String[] paths = new String[this.paths.length + 1];
        System.arraycopy(this.paths, 0, paths, 0, this.paths.length);

        paths[this.paths.length] = path;

        this.paths = paths;
    }

    public void parseType(String contentType) {
        String type = contentType.substring(0, contentType.indexOf("/"));

        addType(type);
    }

    public void validate() throws ServiceException {
        //TODO
    }

    public Iterator<Map<String, Object>> iterator() {
        int index = 0;

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (String name : orgNames){
            Map<String, Object> result = new HashMap<String, Object>();

            if (index < categories.length)
                result.put("category", categories[index]);
            if (index < contents.size())
                result.put("content", contents.get(index));
            if (index < sizes.length)
                result.put("size", sizes[index]);
            if (index < types.length)
                result.put("type", types[index]);
            if (index < filenames.length)
                result.put("filename", filenames[index]);
            if (index < paths.length)
                result.put("path", paths[index]);
            result.put("orgName", name);
            if (index < descriptions.length)
                result.put("description", descriptions[index]);

            resultList.add(result);
            index++;
        }
        return resultList.iterator();
    }

    public AttachmentInfo mergeAttachmentInfo(AttachmentInfo attachmentInfo) {
        if (null == attachmentInfo)
            return this;

        for(String category : attachmentInfo.getCategories())
            addCategory(category);

        for(String orgName : attachmentInfo.getOrgNames())
            addOrgNames(orgName);

        for(String filename : attachmentInfo.getFilenames())
            addFilename(filename);

        for(String type : attachmentInfo.getTypes())
            addType(type);

        for(String path : attachmentInfo.getPaths())
            addPath(path);

        for(String desc : attachmentInfo.getDescriptions())
            addDescription(desc);

        for(Long size : attachmentInfo.getSizes())
            addSize(size);

        for(Long id : attachmentInfo.getIds())
            addId(id);

        return this;
    }
}
