package com.hp.cmcc.bboss.gprs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		List<BdcCdr> list = new LinkedList<BdcCdr>();
		for(int i = 10;i >= 0;i--) {
			BdcCdr cdr = new BdcCdr(10*i+"", 8*i+"", "男",1+i);
			list.add(cdr);
		}
		for(BdcCdr cdr : list) {
			System.out.println(cdr.toString());
		}
		System.out.println("-----------------------------");
		list = sortByIndex(list);
		for(BdcCdr cdr : list) {
			System.out.println(cdr.toString());
		}
	}
	
	public static List<BdcCdr> sortByIndex(List<BdcCdr> rule){
		List<BdcCdr> list = new LinkedList<>();
		list.clear();
		BdcCdr[] a = new BdcCdr[list.size()];
		BdcCdr[] arr = rule.toArray(a);
		
		for(int i = 0;i < arr.length;i++) {
			for(int j = i;j < arr.length;j++) {
				if(arr[i].getFieldBegIdx() > arr[j].getFieldBegIdx()) {
					BdcCdr cdr = arr[i];
					arr[i] = arr[j];
					arr[j] = cdr;
				}
			}
		}
		
		return Arrays.asList(arr);
	}
}
