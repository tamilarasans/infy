package com.chc.rewardapp.model;

//import java.sql.Date;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "customer_transactions")
public class CustomerTxns {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "customer_id")
	private Integer customerId;

	@Column(name = "purchase_details")
	private String purchaseDetails;

	@Column(name = "total_amount")
	private Double totalAmount;

	@Column(name = "purchase_date")
	private Date purchaseDate;

	@Column(name = "updated_date")
	private Date updatedDate;

	private YearMonth purchaseYearMonth;

	public YearMonth getPurchaseYearMonth() {
		this.purchaseYearMonth = YearMonth
				.from(this.purchaseDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		return purchaseYearMonth;
	}

}
