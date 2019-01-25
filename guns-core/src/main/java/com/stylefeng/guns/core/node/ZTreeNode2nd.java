package com.stylefeng.guns.core.node;

/**
 * 
 * jquery ztree 插件的节点
 * 
 * @author HH
 * @date 2018年12月29日 下午8:25:14
 */
public class ZTreeNode2nd {

	private String code;	 //节点id
	
	private String pcode;    //父节点id
	
	private String name;     //节点名称
	
	private Boolean open;    //是否打开节点
	
	private Boolean checked; //是否被选中

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public Boolean getIsOpen() {
		return open;
	}

	public void setIsOpen(Boolean open) {
		this.open = open;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
	public static ZTreeNode createParent(String topName){
		ZTreeNode zTreeNode = new ZTreeNode();
		zTreeNode.setChecked(true);
		zTreeNode.setId(0L);
		zTreeNode.setName(null == topName ? "顶级" : topName);
		zTreeNode.setOpen(true);
		zTreeNode.setpId(0L);
		return zTreeNode;
	}
}
