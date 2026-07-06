package day6.junit;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class LogAnalyzerTest {
	
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void createPrintStream() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void returnPrintStream() {
        System.setOut(originalOut);
        File summary = new File("resources/summary.txt");
        if (summary.exists()) {
        	summary.setWritable(true);
        }
    }

	/**
	 * Should output "Analysis complete. Summary written to summary.txt"
	 * and summary.txt file output should be correct.
	 */
	@Test
	void exec001() throws IOException {
		String expectedFile = Files.readString(Path.of("src/test/resources/exec001/summary.txt"));

		String file = "resources/server.log";
		
		LogAnalyzer.main(new String[] {file});
		
		String summaryFile = Files.readString(Path.of("resources/summary.txt"));
		
		String expected = "Analysis complete. Summary written to summary.txt" + System.lineSeparator();
		assertAll (
			() -> assertEquals(expected, outContent.toString()),
			() -> assertEquals(expectedFile, summaryFile)
		);
	}
	
	/**
	 * Should output "Skipping malformed line" feedback when a line
	 * has incorrect level format, and summary.txt should reflect that there
	 * is one missing WARN line.
	 */
	@Test
	void exec002() throws IOException {
		String expectedFile = Files.readString(Path.of("src/test/resources/exec002/summary.txt"));

		String file = "src/test/resources/exec002/server.log";
		
		LogAnalyzer.main(new String[] {file});
		
		String summaryFile = Files.readString(Path.of("resources/summary.txt"));
		
		String expected = "Skipping malformed line: [2024-05-10 09:00:15] WARNING: High memory usage detected (75%)"
				+ System.lineSeparator() + "Analysis complete. Summary written to summary.txt" + System.lineSeparator();
		assertAll (
			() -> assertEquals(expected, outContent.toString()),
			() -> assertEquals(expectedFile, summaryFile)
		);
	}
	
	/**
	 * Should output "Skipping malformed line" feedback when a line
	 * has incorrect date format (missing bracket), and summary.txt should 
	 * reflect that there is one missing INFO line.
	 */
	@Test
	void exec003() throws IOException {
		String expectedFile = Files.readString(Path.of("src/test/resources/exec003/summary.txt"));

		String file = "src/test/resources/exec003/server.log";
		
		LogAnalyzer.main(new String[] {file});
		
		String summaryFile = Files.readString(Path.of("resources/summary.txt"));
		
		String expected = "Skipping malformed line: 2024-05-10 09:00:18] INFO: Scheduled backup started"
				+ System.lineSeparator() + "Analysis complete. Summary written to summary.txt" + System.lineSeparator();
		assertAll (
			() -> assertEquals(expected, outContent.toString()),
			() -> assertEquals(expectedFile, summaryFile)
		);
	}
	
	/**
	 * Should output "Skipping malformed line" feedback when a line
	 * has no message, and summary.txt should reflect that there is 
	 * one missing ERROR line.
	 */
	@Test
	void exec004() throws IOException {
		String expectedFile = Files.readString(Path.of("src/test/resources/exec004/summary.txt"));

		String file = "src/test/resources/exec004/server.log";
		
		LogAnalyzer.main(new String[] {file});
		
		String summaryFile = Files.readString(Path.of("resources/summary.txt"));
		
		String expected = "Skipping malformed line: [2024-05-10 09:00:24] ERROR:"
				+ System.lineSeparator() + "Analysis complete. Summary written to summary.txt" + System.lineSeparator();
		assertAll (
			() -> assertEquals(expected, outContent.toString()),
			() -> assertEquals(expectedFile, summaryFile)
		);
	}
	
	/**
	 * Should still output summary.txt with 0 and null values
	 * when server.log is empty.
	 */
	@Test
	void exec005() throws IOException {
		String expectedFile = Files.readString(Path.of("src/test/resources/exec005/summary.txt"));

		String file = "src/test/resources/exec005/server.log";
		
		LogAnalyzer.main(new String[] {file});
		
		String summaryFile = Files.readString(Path.of("resources/summary.txt"));
		
		String expected = "Analysis complete. Summary written to summary.txt" + System.lineSeparator();
		assertAll (
			() -> assertEquals(expected, outContent.toString()),
			() -> assertEquals(expectedFile, summaryFile)
		);
	}
	
	/**
	 * Should output "Log file not found." when the log file to be read 
	 * could not be found (FileNotFoundException).
	 */
	@Test
	void exec006() throws IOException {
		String file = "";
		
		LogAnalyzer.main(new String[] {file});
				
		String expected = "Log file not found." + System.lineSeparator();
		assertEquals(expected, outContent.toString());
	}
	
	/**
	 * Should output "Error writing summary file." when output file 
	 * cannot be written into (IOException).
	 */
	@Test
	void exec007() {
	    File summary = new File("resources/summary.txt");
	    summary.setReadOnly();
	    
	    LogAnalyzer.main(new String[] {"resources/server.log"});
	    
	    String expected = "Error writing summary file." + System.lineSeparator();
		assertEquals(expected, outContent.toString());
	}
	
	/**
	 * Should output "Error reading file." when input file 
	 * is found but not read (IOException).
	 * NOTE: Not working yet
	 */
	@Test
	@Disabled
	void exec008() throws IOException {    
		File summary = new File("resources/server.log");
	    summary.createNewFile();
	    summary.setReadable(false, false);
	    
	    LogAnalyzer.main(new String[] {"resources/server.log"});
	    
	    String expected = "Error reading file." + System.lineSeparator();
		assertEquals(expected, outContent.toString());
		
	    summary.setReadable(true, false);
	}

}
