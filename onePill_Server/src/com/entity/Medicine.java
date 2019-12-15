package com.entity;

public class Medicine {
	private int id;
	private String generalName;
	private String medicine;
	private String price;
	private String overview;
	private String function;
	private String introduction;
	private String side_effect;
	private String forbiddance;
	private int doctor_id;
	private String img;
	private String standard;
	
	public Medicine(int id, String generalName, String medicine, String price, String overview, String function, String introduction, String side_effect, String forbiddance, int doctor_id, String img, String standard) {
        this.id = id;
        this.generalName = generalName;
        this.medicine = medicine;
        this.price = price;
        this.overview = overview;
        this.function = function;
        this.introduction = introduction;
        this.side_effect = side_effect;
        this.forbiddance = forbiddance;
        this.doctor_id = doctor_id;
        this.img = img;
        this.standard = standard;
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGeneralName() {
		return generalName;
	}
	public void setGeneralName(String generalName) {
		this.generalName = generalName;
	}
	public String getMedicine() {
		return medicine;
	}
	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public String getFunction() {
		return function;
	}
	public void setFunction(String function) {
		this.function = function;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getSide_effect() {
		return side_effect;
	}
	public void setSide_effect(String side_effect) {
		this.side_effect = side_effect;
	}
	public String getForbiddance() {
		return forbiddance;
	}
	public void setForbiddance(String forbiddance) {
		this.forbiddance = forbiddance;
	}
	public int getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
}
