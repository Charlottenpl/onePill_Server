package com.entity;

public class focus {
	private int id;//Id自增
	private int userId;//用户ID
	private int userType;//用户类型（1：医生2：用户）
	private int type;//关注类型（1：医生2：药品）
	private int typeId;//关注对象Id
	private String img;//展示图片
	private String name;//名称
	private String more;//介绍
	
	public focus(int id, int userId, int userType, int type, int typeId, String img, String name, String more) {
        this.id = id;
        this.userId = userId;
        this.userType = userType;
        this.type = type;
        this.typeId = typeId;
        this.img = img;
        this.name = name;
        this.more = more;
    }

    public focus(int id, int userId, int userType, int type, int typeId) {
        this.id = id;
        this.userId = userId;
        this.userType = userType;
        this.type = type;
        this.typeId = typeId;
    }
	
	public focus(int userId2, int userType2, int type2, int typeId2) {
		// TODO Auto-generated constructor stub
		this.userId = userId2;
		this.userType = userType2;
		this.type = type2;
		this.typeId = typeId2;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMore() {
		return more;
	}
	public void setMore(String more) {
		this.more = more;
	}

}
