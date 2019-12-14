package com.form.service;

import java.util.List;

import com.entity.Address;
import com.entity.Order;
import com.form.dao.OrderDao;
import com.util.DbUtil;

public class OrderService {
	// 根据传入的 buyerId 以及查询的订单状态查询订单
		public List<Order> findOrderByBuyerIdAndType(int buyerId,int type){
			String where = "buyerId ="+buyerId;
			if (type != Order.SELECT_ALL) {
				where += " and type ="+type;
			}
			return DbUtil.changeIntoObj(Order.class, OrderDao.findOrder(where));
		}
		// 查询某个订单下选择的地址信息
		public Address findOrderAddress(int addressId){
			return DbUtil.changeIntoObj(Address.class, OrderDao.findOrderAddress(addressId));
		}
		// 修改相应蛋糕的状态位（由未付款-->待发货以及由 待发货-->已收货）
		public int updateOrderType(int orderId,int changeType,int medicineId,int count){
			String where = "id ="+orderId;
			String set = "type =";
			switch (changeType) {
				case Order.TYPE_UNPAY:// 如果传入的是未支付
					set += Order.TYPE_UNSEND;	// 修改为待发货状态
					break;
		
				case Order.TYPE_WAITGET:// 如果传入待收货状态
					set += Order.TYPE_FINISH;	// 修改为完成状态
					// 同时更新相应count
					String where1 = "id ="+medicineId;
					String set1 = "stock ="+count;
					OrderDao.updateCakeStock(set1, where1);
					break;
			}
			return OrderDao.updateOrderType(set, where);
		}
		// 订单支付界面生成新订单
		public int insertNewOrder(Order order){
			String valuesName = "cakeId , count , buyerId , sellerId , addressId , type";
			String values = order.getMedicineId()+","+order.getCount()+","+order.getBuyerId()+","+
					order.getDoctorId()+","+order.getAddressId()+","+order.getType();
			return OrderDao.insertNewOrder(valuesName, values);
		}
}
