package com.form.dao;

import java.util.List;
import java.util.Map;

import com.util.DbUtil;


public class OrderDao {
	private static String buyerTableName = "buyer";
	private static String cartTableName = "cart";
	private static String orderTableName = "form";
	private static String medicineTableName = "medicine";
	private static String addressTableName = "address";
	private static String selectAll = "*";
	private static String selectAllDistinct = "distinct *";
	// 查询所有订单
	// 查询未付款订单
	// 查询未发货订单
	// 查询未付款订单
	// 查询未付款订单
	public static List<Map<String, Object>> findOrder(String where){
		return DbUtil.findListByWhere(selectAll, orderTableName, where);
	}
	// 查询某一订单下的地址
	public static Map<String, Object> findOrderAddress(int addressId){
		return DbUtil.findById(addressTableName, addressId);
	}
	// 更改订单的状态位：（支付、收货功能）
	public static int updateOrderType(String set,String where){
		return DbUtil.update(orderTableName, set, where);
	}
	// 订单完成时，修改药品相应库存
	public static void updateCakeStock(String set,String where){
		DbUtil.update(medicineTableName, set, where);
	}
	// 订单支付界面生成新订单
	public static int insertNewOrder(String valuesName,String values){
		return DbUtil.insertInto(orderTableName, valuesName, values);
	}
	//根据MedicineId查询单个药品的信息
	public static Map<String, Object> findMedicineById(int medicineId) {
		return DbUtil.findById(medicineTableName, medicineId);
	}
}
