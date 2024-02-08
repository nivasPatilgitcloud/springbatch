package com.example.springbatch.config;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.batch.item.ItemProcessor;

import com.example.springbatch.model.Foodtb;

public class CustomItemProcessor implements ItemProcessor<Foodtb, Foodtb> {

	@Override
	public Foodtb process(Foodtb item) {
//		System.out.println(item.getRegion());
//		int quantity = item.getQty();
//		double unitprice = item.getUnitp_rice();
//		double totalprice = quantity * unitprice;
//		item.setTotal_price(totalprice);
//		return item;
		
		try {
			Integer quantity = Integer.parseInt(item.getQty());
			Double unitprice = Double.parseDouble(item.getUnit_price());
			Double totalprice = quantity*unitprice;
			BigDecimal roundedTotalPrice = new BigDecimal(totalprice)
	                .setScale(2, RoundingMode.HALF_UP);
			item.setTotal_price(String.valueOf(roundedTotalPrice));
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		return item;
	}

}
