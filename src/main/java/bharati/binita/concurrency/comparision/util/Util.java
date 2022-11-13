package bharati.binita.concurrency.comparision.util;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Util {
	
	public static void writeToFile(BufferedWriter writer, int listNumber, String content) throws Exception {
	    writer.append("List number "+listNumber + " start");
	    writer.append(content);
	    writer.append("List number "+listNumber + " end");
	}
	
	public static BufferedWriter initWriter(String filePath) throws Exception {
		return new BufferedWriter(new FileWriter(filePath, true));

	}
	
	public static void closeWriter(BufferedWriter bw) throws Exception {
		bw.close();
	}


}
