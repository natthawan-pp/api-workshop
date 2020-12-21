package th.co.cdgs.train.exam.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "order_master")
public class OrderMaster {
	private String orderId;
	private Customer customer;
	private Date ordeeDate;
	private Integer total;
	private Integer amount;
	private List<OrderDetail> orderDetailList;
	
	@Id
	@Column(name = "order_id", unique = true, nullable = false, length = 5)
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id")
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Column(name = "order_date")
	public Date getOrdeeDate() {
		return ordeeDate;
	}
	public void setOrdeeDate(Date ordeeDate) {
		this.ordeeDate = ordeeDate;
	}
	
	@Column(name = "total", length = 11)
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	@Column(name = "amount", length = 11)
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	@Transient
	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}
	public void setOrderDetail(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
	
	
	

}
