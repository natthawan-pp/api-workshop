package th.co.cdgs.train.exam.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import th.co.cdgs.train.exam.entity.Customer;
import th.co.cdgs.train.exam.entity.OrderDetail;
import th.co.cdgs.train.exam.entity.OrderMaster;
import th.co.cdgs.train.exam.persistence.OnlineShopPersistence;
import th.co.cdgs.train.exam.query.OnlineShopQuery;

@Stateless
@LocalBean
public class OnlineShopBusiness {
	
    @PersistenceContext
    private EntityManager em;
	
    @EJB
    private OnlineShopBusiness onlineShopBusiness;
    
    @EJB
    private OnlineShopPersistence onlineShopPersistence;
    
    @EJB
    private OnlineShopQuery onlineShopQuery;
	
	public OrderMaster makeOrder(OrderMaster order) {
        if(order == null) {
            throw new IllegalArgumentException("The 'orderMaster' is blank or null. ");
        } 
        if (order.getOrderDetailList() == null) {
        	throw new IllegalArgumentException("The 'orderDetailList' is blank or null. ");
        }
        
        if (order.getCustomer().getCustomerId() == null){
        	onlineShopBusiness.insertCustomer(order.getCustomer());
        } else {
        	Customer cus = onlineShopQuery.queryCustomerById(order.getCustomer().getCustomerId());
        	if (cus != null) {
        		onlineShopPersistence.updateCustomer(order.getCustomer());
        	}else {
        		onlineShopBusiness.insertCustomer(order.getCustomer());
        	}
        }
        
        OrderMaster ordermaster = onlineShopPersistence.insertOrderMaster(order);	
		List<OrderDetail> list = order.getOrderDetailList();
		for (OrderDetail obj : list) {
			OrderDetail orderdetail = onlineShopPersistence.insertOrderDetail(obj);
			orderdetail.setOrder(ordermaster);
			onlineShopPersistence.insertOrderDetail(orderdetail);
		}
		return ordermaster;
	}
	
    public Customer insertCustomer(Customer customer) {
        if(customer != null) {
            Customer cus = onlineShopPersistence.insertCustomer(customer);
            em.persist(customer);    
        }
        return customer;
    }

}
