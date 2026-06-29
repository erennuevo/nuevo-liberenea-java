package com.example.eren.javatraining;

import java.util.Scanner;

public class ZigzagPattern {
	
	public static void main(String[] args) {	
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int num = scanner.nextInt();
		
		int current = 1;
		
		for (int i = 1; i <= num; i++) {
			if (i % 2 != 0) {
				for (int j = 1; j <= num; j++) {
					System.out.print(current + "\t");
					current++;
				}
			} else {
				int backward = current + num - 1;
				for (int j = 1; j <= num; j++) {
					System.out.print(backward + "\t");
					backward--;
				}
				current += num;
			}
			System.out.print("\n");
		}
		
		scanner.close();
	}

}

