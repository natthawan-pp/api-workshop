package th.co.cdgs.train.exam.query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import th.co.cdgs.train.exam.entity.Category;
import th.co.cdgs.train.exam.entity.Customer;
import th.co.cdgs.train.exam.entity.OrderDetail;
import th.co.cdgs.train.exam.entity.OrderMaster;
import th.co.cdgs.train.exam.entity.Product;

@Stateless
@LocalBean
public class OnlineShopQuery {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Category> queryCategory(){
		String sql = "select category_code, category_name from category order by category_code";
		Query query = em.createNativeQuery(sql);
		List<Object[]> list = query.getResultList();
		List<Category> results = new ArrayList<>();
		for (Object[] obj : list) {
			Category category = new Category();
			category.setCategoryCode((String)obj[0]);
			category.setCategoryName((String)obj[1]);
			results.add(category);
		}
		return results;
	}
	
	public List<Product> queryProductByCategoryCode(String categoryCode){
        if(categoryCode == null) {
            throw new IllegalArgumentException("The 'categoryCode' is blank or null. ");
        }
        String sql = "select product_code, product_name, price, category_code, detail "
        		+ "from product where category_code = :categoryCode order by product_code";
        Query query = em.createNativeQuery(sql);
        query.setParameter("categoryCode", categoryCode);
  
        List<Object[]> list = query.getResultList();
		List<Product> results = new ArrayList<>();
		for (Object[] obj : list) {
			Product product = new Product();
	        product.setProductCode((String)obj[0]);
	        product.setProductName((String)obj[1]);
	        product.setPrice((Integer)obj[2]);
	        Category category = new Category();
	        category.setCategoryCode((String)obj[3]);
	        product.setCategory(category);
	        product.setDetail((String)obj[4]);
	        results.add(product);
		}
		return results;
	}
	
	public Customer queryCustomerById(String customerId) {
        if(customerId == null) {
            throw new IllegalArgumentException("The 'customerId' is blank or null. ");
        }
        String sql = "select customer_id, first_name, last_name, address from customer where customer_id = :customerId";
        Query query = em.createNativeQuery(sql);
        query.setParameter("customerId", customerId);
        
        Object[] obj = (Object[])query.getSingleResult();
        Customer customer = new Customer();
        customer.setCustomerId((String)obj[0]);
        customer.setFirstName((String)obj[1]);
        customer.setLastName((String)obj[2]);
        customer.setAddress((String)obj[3]);
        
        return customer;
	}
	
	public OrderMaster queryOrderMasterById(String orderId) {
        if(orderId == null) {
            throw new IllegalArgumentException("The 'orderId' is blank or null. ");
        }
        String sql = "select order_id, customer_id, order_date, total, amount from order_master where order_id = :orderId";
        Query query = em.createNativeQuery(sql);
        query.setParameter("orderId", orderId);
        
        Object[] obj = (Object[])query.getSingleResult();
        OrderMaster ordermaster = new OrderMaster();
        ordermaster.setOrderId((String)obj[0]);
        Customer customer = new Customer();
        customer.setCustomerId((String)obj[1]);
        ordermaster.setCustomer(customer);
        ordermaster.setOrdeeDate((Date)obj[2]);
        ordermaster.setTotal((int)obj[3]);
        ordermaster.setAmount((int)obj[4]);
        
        return ordermaster;
	}
	
	public List<OrderDetail> queryOrderDetailByOrderId(String orderId) {
        if(orderId == null) {
            throw new IllegalArgumentException("The 'orderId' is blank or null. ");
        }
        String sql = "select od.order_detail_id, od.order_id, od.product_code, od.product_amount" + 
        		", od.product_total, d.product_name, d.price " + 
        		"from order_detail od " + 
        		"join product d on (od.product_code = d.product_code) " + 
        		"where order_id = :orderId " + 
        		"order by od.order_detail_id ";
        Query query = em.createNativeQuery(sql);
        query.setParameter("orderId", orderId);
        
        List<Object[]> list = query.getResultList();
		List<OrderDetail> results = new ArrayList<>();
		for (Object[] obj : list) {
			OrderDetail orderdetail = new OrderDetail();
			orderdetail.setOrderDetailId((String)obj[0]);
			
			OrderMaster ordermaster = new OrderMaster();
			ordermaster.setOrderId((String)obj[1]);
			orderdetail.setOrder(ordermaster);
			
			Product product = new Product();
	        product.setProductCode((String)obj[2]);
	        product.setProductName((String)obj[5]);
	        product.setPrice((Integer)obj[6]);
			orderdetail.setProduct(product);
			
			orderdetail.setProductAmount((Integer)obj[3]);
			orderdetail.setProductTotal((Integer)obj[4]);
			results.add(orderdetail);
		}
		return results;
	}
	
	public String queryNextCustomerId() {
		String sql = "select coalesce(max(customer_id) + 1 , 1) from customer";
        Query query = em.createNativeQuery(sql);
        double maxCustomer = (double)query.getSingleResult();
		return String.format("%05d", (int)maxCustomer);
	}
	
	public String queryNextOrderId() {
		String sql = "select coalesce(max(order_id) + 1 , 1) from order_master";
        Query query = em.createNativeQuery(sql);
        double maxOrder = (double)query.getSingleResult();
		return String.format("%05d", (int)maxOrder);
	}
	
	public String queryNextOrderDetailId() {
		String sql = "select coalesce(max(order_detail_id) + 1 , 1) from order_detail";
        Query query = em.createNativeQuery(sql);
        double maxOrderDetail = (double)query.getSingleResult();
		return String.format("%05d", (int)maxOrderDetail);
	}
	
	public OrderMaster queryPrintOrder(String orderId) {
		Customer cus = queryCustomerById(orderId);
		OrderMaster ordermaster = new OrderMaster();
		if (orderId != null) {
			ordermaster.setCustomer(cus);
			List<OrderDetail> orderdetail = (queryOrderDetailByOrderId(orderId));
			ordermaster.setOrderDetail(orderdetail);
		}
	    return ordermaster;
	}
	
	
	
	
	
	

}
