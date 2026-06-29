package com.example.eren.javatraining;

import java.util.Scanner;

public class Assignment {
	
	public enum Day {
		Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
	}
	
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
	
	public static String daySwitch(int input) {
        System.out.println("-- Days of the Week: Switch --");
        
		switch (input) {
			case 1: 
				return "Monday";
			case 2:
				return "Tuesday";
			case 3:
				return "Wednesday";
			case 4:
				return "Thursday";
			case 5:
				return "Friday";
			case 6:
				return "Saturday";
			case 7:
				return "Sunday";
			default:
				return "Invalid day number.";
		}
	}
	
	public static Day dayPattern(int input) {
        System.out.println("-- Days of the Week: Pattern Matching --");

		return switch (input) {
			case 1 -> Day.Monday;
			case 2 -> Day.Tuesday;
			case 3 -> Day.Wednesday;
			case 4 -> Day.Thursday;
			case 5 -> Day.Friday;
			case 6 -> Day.Saturday;
			case 7 -> Day.Sunday;
		    default -> {
		        System.out.println("Invalid day number.");
		        yield null;
		    }
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
	
	// Helper functions to print outputs
	public static void runBlackjack(Scanner scanner) {
        System.out.print("Enter a: ");
        int a = scanner.nextInt();
        
        System.out.print("Enter b: ");
        int b = scanner.nextInt();
        
        System.out.println(blackjack(a, b));
	}
	
	public static void runPatternMatching(Scanner scanner) {
    	System.out.print("Enter an integer from 1-7: ");
        int bInput = scanner.nextInt();
        
        System.out.println(daySwitch(bInput));
        dayPattern(bInput);
	}
	
	public static void runPrintPyramid(Scanner scanner) {
		int cInput = 0;
		
    	while (cInput <= 0 || cInput > 20) {
            System.out.print("Enter an integer to create a number pyramid: ");
            cInput = scanner.nextInt();
        }
        
        printForPyramid(cInput); 
        printWhilePyramid(cInput);
        printDoWhilePyramid(cInput);
	}
	
	public static void main(String args[]) {		
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Which program will be tested? \n(A) Blackjack \n(B) Pattern Matching \n(C) Number Pyramid \nEnter choice: ");
        String selection = scanner.nextLine();
            
        switch (selection) {
            case "A" -> runBlackjack(scanner);
            case "B" -> runPatternMatching(scanner);
            case "C" -> runPrintPyramid(scanner);
            default -> System.out.println("Invalid selection, please try again.\n");
        }
        
        scanner.close();
	}
}
