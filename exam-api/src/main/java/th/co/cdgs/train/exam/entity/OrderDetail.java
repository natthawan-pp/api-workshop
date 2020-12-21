package th.co.cdgs.train.exam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
	
	private String orderDetailId;
	private OrderMaster order;
	private Product product;
	private Integer productAmount;
	private Integer productTotal;
	
	@Id
	@Column(name = "order_detail_id", unique = true, nullable = false, length = 5)
	public String getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "order_id")
	public OrderMaster getOrder() {
		return order;
	}
	public void setOrder(OrderMaster order) {
		this.order = order;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_code")
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Column(name = "product_amount", length = 11)
	public Integer getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(Integer productAmount) {
		this.productAmount = productAmount;
	}
	
	@Column(name = "product_total", length = 11)
	public Integer getProductTotal() {
		return productTotal;
	}
	public void setProductTotal(Integer productTotal) {
		this.productTotal = productTotal;
	}
	
	

}
