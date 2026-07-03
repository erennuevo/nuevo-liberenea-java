package day5.filehandling;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogFileAnalyzer {
	
    static int totalCount;
	
	static List<String> errorList = new ArrayList<>();
	static Map<String, Integer> levelCounts = new LinkedHashMap<>();
	
	static LocalDateTime earliest = null;
	static LocalDateTime latest = null;
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public static class MalformedLogEntryException extends RuntimeException {
	    public MalformedLogEntryException(String message) {
	        super(message);
	    }
	}
	
	private static void updateCountAndErrorList(String level, String message) {
	    if (levelCounts.containsKey(level)) {
	        int currentCount = levelCounts.get(level);
	        levelCounts.put(level, currentCount + 1);
	    } else {
	        levelCounts.put(level, 1);
	    }
	    
	    if (level.equals("ERROR")) {
	        errorList.add(message);
	    }
	    
		totalCount++;
	}
	
	private static void updateEarliestLatest(LocalDateTime timestamp) {
		if (earliest == null) {
		    earliest = timestamp;
		    latest = timestamp;
		} else {
			if (timestamp.isBefore(earliest)) earliest = timestamp;
			if (timestamp.isAfter(latest)) latest = timestamp;
		}
	}

	public static void main(String[] args) {
		
		try (BufferedReader br = new BufferedReader(new FileReader("server.log"))
		  ) {
			String line;
			final String regex = "^\\[(.*?)\\] (INFO|WARN|ERROR): (.*)$";
						
			Pattern pattern = Pattern.compile(regex);
			
			while ((line = br.readLine()) != null) { 
				Matcher matcher = pattern.matcher(line);
				
				if (matcher.matches()) {
					LocalDateTime timestamp = LocalDateTime.parse(matcher.group(1), formatter);
					
					updateCountAndErrorList(matcher.group(2), matcher.group(3));
					updateEarliestLatest(timestamp);

				} else {
					throw new MalformedLogEntryException("Line has invalid format: " + line);
				}
			}
			
			System.out.println("File read successfully");
			
		} catch (FileNotFoundException e) {
        	System.out.println("File not found: " + e.getMessage());
		} catch (IOException e) {
        	System.out.println("IO error: " + e.getMessage());
		}
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("summary.txt"))
		) {
			bw.write("Log Summary Report\n------------------\n");
			
			bw.write("Total Entries: " + totalCount);
			bw.newLine();
            for (Map.Entry<String, Integer> levelEntry : levelCounts.entrySet()) {
                bw.write(levelEntry.getKey() + ": " + levelEntry.getValue() + "\n");
            }
			
			bw.write("\nError Messages: ");
			bw.newLine();
			for (String error : errorList) {
				bw.write(" - " + error);
				bw.newLine();
			}
			
			bw.write("\nEarliest Timestamp: " + earliest.format(formatter));
			bw.newLine();
			bw.write("Latest Timestamp: " + latest.format(formatter));
			
			System.out.println("File written successfully");
		} catch (FileNotFoundException e) {
        	System.out.println("File not found: " + e.getMessage());
		} catch (IOException e) {
        	System.out.println("IO error: " + e.getMessage());
		}
	}
}
