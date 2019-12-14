package com.entity;

public class Order {
	private int id;
	private int userid;
	private int buyerId;
	private int doctorId;
	private int medicineId;
	private int count;
	private int price;
	private int type;
	private String medicineName;
	private int addressId;
	public static final int SELECT_ALL = -1;			// 查询全部的状态位
	public static final int TYPE_UNPAY = 0;         // 订单状态为 0 未付款
    public static final int TYPE_UNSEND = 1;    // 订单状态为 1 未发货
    public static final int TYPE_WAITGET = 2;     // 订单状态为 2 已发货
    public static final int TYPE_FINISH = 3;        // 订单状态为 3 已完成
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public int getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(int buyerId) {
		this.buyerId = buyerId;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int address) {
		this.addressId = address;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
