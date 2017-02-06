package com.lanxi.weixin.test;

import java.util.Random;

public class TureTableTest {

	public static void main(String[] args) {
		show(100000);
	}
	
	public static void show(int number){
		int num0=0,num1=0,num2=0,num3=0,num4=0;
		for(int i=0;i<number;i++){
			float a0 = 0.00001f;
			float a1 = 0.00002f;
			float a2 = 0.0001f;
			float a3 = 0.0006f;
			float a4 = 1-a0-a1-a2-a3;
			float aSum = a0+a1+a2+a3+a4;
			Random random = new Random();
			float randomNum = random.nextFloat();
			float result = aSum * randomNum;
			if(result<=a0){
				num0 += 1;
			}else if(result>a0 && result<=(a0+a1)){
				num1 += 1;
			}else if(result>(a0+a1) && result<=(a0+a1+a2)){
				num2 += 1;
			}else if(result>(a0+a1+a2) && result<=(a0+a1+a2+a3)){
				num3 += 1;
			}else{
				num4 += 1;
			}
		}
		System.out.println("特等奖："+num0+"，一等奖："+num1+"，二等奖："+num2+"，三等奖："+num3+"，谢谢惠顾："+num4);
	}
}
