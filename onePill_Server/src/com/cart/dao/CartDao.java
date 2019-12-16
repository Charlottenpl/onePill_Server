package com.cart.dao;


import java.util.List;
import java.util.Map;

import com.util.DbUtil;

public class CartDao {
	private static String cartTableName = "tbl_cart";
	private static String selectAll = "*";
	private static String medicineTableName="tbl_medicine";
	private String selectAllDistinct = "distinct *";
	// 添加商品至购物车
	public static int addIntoCart(int medicineid,int userid,int count){
		return DbUtil.insertInto(cartTableName, "medicineId , buyerId , count",medicineid+","+userid+","+count);
	}
	//根据MedicineId查询单个药品的信息
	public static Map<String, Object> findMedicineById(int medicineId) {
		return DbUtil.findById(medicineTableName, medicineId);
	}
	// 根据userid,medicineid查询购物车中是否有对应项
	public static Map<String,Object> checkCartIsExist(String where){
		return DbUtil.findOneByWhere(selectAll, cartTableName, where);
	}
	/**
	 * 根据userid查询所有的cartlist
	 * @param buyerId
	 * @return
	 */
	public static List<Map<String,Object>> findCartsByBuyerId(int buyerId) {
		return DbUtil.findListByWhere(selectAll, cartTableName, "userId = "+buyerId);
	}
	// 根据 CartId 删除某个cartItem
	public static int deleteCartById(String where){
		return DbUtil.deleteFrom(cartTableName, where);
	}
		
	// Buyer根据 CartId 修改cart中选择的数量
	public static int updateCartItemCount(String set,String where){
		return DbUtil.update(cartTableName, set, where);
	}
}