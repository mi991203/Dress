package com.lifeng.service;

import java.util.List;

import com.lifeng.entity.Order;
import com.lifeng.entity.OrderItem;


public interface OrdersService{
	
	// �ı䶩��״̬
	public void updateOrder(Order order);	
	// �½�����
	public void addOrder(Order order );	
	// �½�������
	public void addOrderItem(OrderItem orderItem); 	
	
	//����idѰ��
	public Order findOrderById(int id);
}
