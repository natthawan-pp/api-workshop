package th.co.cdgs.train.exam.persistence;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import th.co.cdgs.train.exam.entity.Customer;
import th.co.cdgs.train.exam.entity.OrderDetail;
import th.co.cdgs.train.exam.entity.OrderMaster;
import th.co.cdgs.train.exam.query.OnlineShopQuery;


@Stateless
public class OnlineShopPersistence {
	
    @PersistenceContext
    private EntityManager em;
    
    @EJB
    private OnlineShopQuery onlineShopQuery;
    
    public Customer insertCustomer(Customer customer) {
        if(customer != null) {
            String customerId = onlineShopQuery.queryNextCustomerId();
            customer.setCustomerId(customerId);
            em.persist(customer);    
        }
        return customer;
    }
    
    public Customer updateCustomer(Customer customer) {
        if(customer != null) {
            em.merge(customer);
        }
        return customer;
    }
    
    public OrderMaster insertOrderMaster(OrderMaster ordermaster) {
        if(ordermaster != null) {
            String orderId = onlineShopQuery.queryNextOrderId();
            ordermaster.setOrderId(orderId);
            em.persist(ordermaster);    
        }
        return ordermaster;
    }
    
    public OrderDetail insertOrderDetail(OrderDetail orderdetail) {
        if(orderdetail != null) {
            String orderDetailId = onlineShopQuery.queryNextOrderDetailId();
            orderdetail.setOrderDetailId(orderDetailId);
            em.persist(orderdetail);    
        }
        return orderdetail;
    }
    
	

}
