package day5.filehandling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CopyFile {
	public static void main(String args[]) {
		
        Map<String, List<String>> studentMap = new HashMap<>();
        String[] parameters = null;
		
		try (BufferedReader br = new BufferedReader(new FileReader("student.csv"))
			) {
            String line;
            
            line = br.readLine(); 
            parameters = line.split(",");
            
            while ((line = br.readLine()) != null) {
                String[] entries = line.split(",");
                
                if (entries.length == 3) {
                	List<String> studentDetails = new ArrayList<>();
                	
                    studentDetails.add(entries[1].trim());
                    studentDetails.add(entries[2].trim());
                    studentMap.put(entries[0].trim(), studentDetails);
                }
            }
            
            System.out.println("File read successfully");
        } catch (IOException e) {
        	System.out.println("File error: " + e.getMessage());
            e.printStackTrace();
        }
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("student.json"))
			) {
	        boolean isFirstLine = true;
	            
	        bw.write("{");
	        bw.newLine();
	            
	        for(Map.Entry<String, List<String>> entry : studentMap.entrySet()) {                        
		            if (!isFirstLine) {
		               bw.write(",");
		               bw.newLine();
		            }
		            isFirstLine = false;
	
		            bw.write("\t{");
		            bw.newLine();
		            bw.write("\t\t\"" + parameters[0].trim() + "\": \"" + entry.getKey() + "\",");
		            bw.newLine();
		            bw.write("\t\t\"" + parameters[1].trim() + "\": " + entry.getValue().get(0));
		            bw.newLine();
		            bw.write("\t\t\"" + parameters[2].trim() + "\": " + entry.getValue().get(1));
		            bw.newLine();
		            bw.write("\t}");
	            }
	        
	            bw.newLine();
	            bw.write("}");
	            System.out.println("File written successfully");
	        } catch (IOException e) {
	        	System.out.println("File error: " + e.getMessage());
	            e.printStackTrace();
	        }

	}

}
