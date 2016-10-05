package com.rumbo.favs.data.utilities;

import java.text.DecimalFormat;

public class MyMath {

	public static String getRoundedFloat(float number){
			
			String aux;
			String separator = ",";
			String pattern = "00";
			
			DecimalFormat df = new DecimalFormat();
			
			df.setMaximumFractionDigits(2);
			df.setMinimumFractionDigits(2);
			
			aux = df.format(number);
			
			String[] parts = aux.split(separator);
			
			if (pattern.equals(parts[1])){
				return parts[0];
			}else{
				return aux;
			}		
		}
}
