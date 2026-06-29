package com.example.eren.javatraining;

import java.util.Scanner;

public class Assignment {
	
	public static int blackjack(int a, int b) {
		if (a > 21 && b > 21) {
			return 0;
		} else if (a > 21) {
			return b;
		} else if (b > 21) {
			return a;
		} else if (a > b) {
			return a;
		} else {
			return b;
		}
	}
	
	public static String getDay(int input) {
		return switch (input) {
			case 1 -> "Monday";
			case 2 -> "Tuesday";
			case 3 -> "Wednesday";
			case 4 -> "Thursday";
			case 5 -> "Friday";
			case 6 -> "Saturday";
			case 7 -> "Sunday";
			default -> "Invalid day number.";
		};
	}
	
	public static void printForPyramid(int input) { 
		System.out.println("-- For Loop --");
		
        for (int i = 1; i <= input; i++) {
        	for (int j = 1; j <= i; j++) {
        		System.out.print(j + "\t");
        	}
        	System.out.println();
        }  
	}
	
	public static void printWhilePyramid(int input) { 
        System.out.println("-- While Loop --");
        
		int i = 1;
		
        while (i <= input) {
        	int j = 1;
        	while (j <= i) {
        		System.out.print(j + "\t");
        		j++;
        	}
        	System.out.println();
        	i++;
        }  
	}
	
	public static void printDoWhilePyramid(int input) { 
        System.out.println("-- Do While Loop --");
        
		int i = 1;
		
        do {
        	int j = 1;
        	
        	do {
        		System.out.print(j + "\t");
        		j++;
        	} while (j <= i);
        	
        	System.out.println();
        	i++;
        } while (i <= input);
	}
	
	public static void main(String args[]) {
		int cInput = 0;
		boolean isValid = false;
		
        Scanner scanner = new Scanner(System.in);
        
        while (!isValid) {
        	System.out.print("Which program will be tested? \n(A) Blackjack \n(B) Pattern Matching \n(C) Number Pyramid \nEnter choice: ");
            String selection = scanner.nextLine();
            
            switch (selection) {
            case "A":
                System.out.print("Enter a: ");
                int a = scanner.nextInt();
                
                System.out.print("Enter b: ");
                int b = scanner.nextInt();
                
                System.out.println(blackjack(a, b));
                isValid = true;
                break;
            case "B":
            	System.out.print("Enter an integer from 1-7: ");
                int bInput = scanner.nextInt();
                
                System.out.println(getDay(bInput));
                isValid = true;
                break;
            case "C":
            	while (cInput <= 0 || cInput > 20) {
                    System.out.print("Enter an integer to create a number pyramid: ");
                    cInput = scanner.nextInt();
                }
                
                printForPyramid(cInput); 
                printWhilePyramid(cInput);
                printDoWhilePyramid(cInput);
                
                isValid = true;
                break;
            default:
            	System.out.println("Invalid selection, please try again.\n");
            	break;
            }
        }
        scanner.close();
	}
}
