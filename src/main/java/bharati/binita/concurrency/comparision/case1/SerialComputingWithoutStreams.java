package bharati.binita.concurrency.comparision.case1;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import bharati.binita.concurrency.comparision.util.Util;

public class SerialComputingWithoutStreams {
	
	/**
	 * 
	 * @param containerListSize
	 * @param innerStreamSize
	 * @param startNumberBound
	 * @param endNumberBound
	 * @return List<IntStream> - each element of this list will require to be sorted.
	 */
	private static List<List<Integer>> constructRandomNumberList(int containerListSize, int innerStreamSize, 
			int startNumberBound, int endNumberBound) {
		List<List<Integer>> retList = new ArrayList();
		Random random = new Random();
		for (int i = 0 ; i < containerListSize ; i++) {
			retList.add(
					random.ints(innerStreamSize, startNumberBound, endNumberBound)
				   .boxed()
			       .collect(Collectors.toList()));
		}
		return retList;
	}
	
	private static void  sortSequentially(List<List<Integer>> inputList) {
		for (List<Integer> eachList : inputList) {
			eachList.sort(Comparator.naturalOrder());
		}
	}

	
	public static void main(String[] args) throws Exception {
		long startTime = System.currentTimeMillis();
		
		List<List<Integer>> randomNumberList = constructRandomNumberList(1_000_000, 10_000, 1, 1_000_000);			
		sortSequentially(randomNumberList);
		BufferedWriter writer = Util.initWriter(args[0]);

		int listIndex = 1;
		for (List<Integer> eachList : randomNumberList) {
			Util.writeToFile(writer, listIndex, eachList.toString());
			listIndex++;
		}
		long endTime = System.currentTimeMillis();
		System.out.println("tt = "+(endTime - startTime)/1000 +" secs");
		
	}
}
