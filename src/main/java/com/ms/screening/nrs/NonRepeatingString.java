package com.ms.screening.nrs;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
/**
 * 
 * @author Sriram
 *
 */
public class NonRepeatingString {
	public static void main(String ar[]) {
		Scanner sc=new Scanner(System.in);  
		while(true) {
			System.out.println("Enter the String");
			String str = sc.next().toUpperCase();
			if(str.equalsIgnoreCase("EXIT")) {
				break;
			}
			try {
				System.out.println(findFirstNonRepeatingCharater(str));	
			} catch (RuntimeException ex) {
				System.out.println(ex.getMessage());
			}
			
		}
		
	}
	
	 public static char findFirstNonRepeatingCharater(String str) {
	        Map<Character,Integer> counts = new LinkedHashMap<>(str.length());
	        
	        for (char c : str.toCharArray()) {
	            counts.put(c, counts.containsKey(c) ? counts.get(c) + 1 : 1);
	        }
	        
	        for (Entry<Character,Integer> entry : counts.entrySet()) {
	            if (entry.getValue() == 1) {
	                return entry.getKey();
	            }
	        }
	        
	        throw new RuntimeException("Not getting any non repeating character in your input String ");
	    }
}
