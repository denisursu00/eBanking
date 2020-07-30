package banking.model;

import java.math.BigDecimal;

public class TestAccount {

	private Integer id;
	private String name;
	private BigDecimal sum;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getSum() {
		return sum;
	}
	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}
	
	
}