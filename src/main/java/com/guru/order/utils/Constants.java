package com.guru.order.utils;

import java.text.SimpleDateFormat;

public interface Constants {
	
	SimpleDateFormat sdf_yyyyddMMHHmmss = new SimpleDateFormat("yyyyddMMHHmmss");

	enum BUY_SELL_OPTION {
		
		BUY("BUY", 1), SELL("SELL", 2);

		private String optionType;
		private int option;

		BUY_SELL_OPTION(String optionType, int option) {
			this.optionType = optionType;
			this.option = option;
		}

		public String getOptionType() {
			return optionType;
		}

		public int getOption() {
			return option;
		}

	}

}
