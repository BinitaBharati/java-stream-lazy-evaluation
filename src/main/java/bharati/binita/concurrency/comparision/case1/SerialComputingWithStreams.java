package bharati.binita.concurrency.comparision.case1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import bharati.binita.concurrency.comparision.util.Util;

public class SerialComputingWithStreams {
	
	/**
	 * 
	 * @param containerListSize
	 * @param innerStreamSize
	 * @param startNumberBound
	 * @param endNumberBound
	 * @return List<IntStream> - each element of this list will require to be sorted.
	 */
	private static List<IntStream> constructRandomNumberList(int containerListSize, int innerStreamSize, 
			int startNumberBound, int endNumberBound) {
		List<IntStream> retList = new ArrayList();
		Random random = new Random();
		for (int i = 0 ; i < containerListSize ; i++) {
			retList.add(random.ints(innerStreamSize, startNumberBound, endNumberBound));
		}
		return retList;
	}
	
	private static List<IntStream> sortSequentially(List<IntStream> inputList) {
		List<IntStream> sortedList = new ArrayList();
		inputList.forEach(eachIs -> {
			sortedList.add(eachIs.sorted());
		});
		
		return sortedList;
	}
	
	
	
	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();
		List<IntStream> randomNumberList = constructRandomNumberList(1_000_000, 10_000, 1, 1_000_000);
		List<IntStream> sortedList = sortSequentially(randomNumberList);
		BufferedWriter bw = Util.initWriter(args[0]);
		int isIndex = 1;
		for(IntStream eachIs : sortedList) {
			List<Integer> temp = new ArrayList();
			eachIs.forEach(eachStreamItem -> {
				temp.add(eachStreamItem);
			});
			Util.writeToFile(bw, isIndex, temp.toString());
			isIndex++;
		}
		Util.closeWriter(bw);
		long endTime = System.currentTimeMillis();
		System.out.println("Total time taken = "+(endTime - startTime)/1000 + " secs");
		
		
		
	}
}
