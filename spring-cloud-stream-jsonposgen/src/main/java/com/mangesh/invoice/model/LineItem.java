package com.mangesh.invoice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LineItem {
	
	@JsonProperty("ItemCode")
	private String itemCode;
	@JsonProperty("ItemDescription")
	private String itemDescription;
	@JsonProperty("ItemPrice")
	private Double itemPrice;
	@JsonProperty("")
	private Integer itemQty;
	@JsonProperty("ItemQty")
	private Double totalValue;
	

}
