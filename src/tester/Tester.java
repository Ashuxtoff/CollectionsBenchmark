package tester;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import baseDict.BaseDict;


public class Tester {
	
	public static HashMap<Integer, Long> buildingResults = new HashMap<Integer, Long>();
	public static HashMap<Integer, Long> lookingForResults = new HashMap<Integer, Long>();	
	
	public static HashMap<String, Integer> scanFile(File file) { 
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		try { 
			Scanner scanner = new Scanner(file); 
			while (scanner.hasNext()) { 
				String next = scanner.next().toLowerCase(); 
				if (next.length() >= 3) { 
					if (!Character.isLetter(next.charAt(next.length()-1)))
						next = next.substring(0, next.length()-1);
					if (result.containsKey(next)) 
						result.put(next, result.get(next) + 1);
					else 
						result.put(next, 1); 
				}  
			} 
			scanner.close();
		} 
		catch (FileNotFoundException e) { 
			e.printStackTrace(); 
		} 
		return result; 
	} 
	
	public static HashMap<String, Integer> scanFile(File file, int count) {
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		int counter = 0;
		try {
			Scanner scanner = new Scanner(file);
			while (scanner.hasNext()) {
				String next = scanner.next().toLowerCase();
				if (next.length() >= 3) { 
					if (!Character.isLetter(next.charAt(next.length()-1)))
						next = next.substring(0, next.length()-1);
					if (result.containsKey(next))
						result.put(next, result.get(next) + 1);
					else {
						result.put(next, 1);
						counter++;
						if (counter == count)
							break;
					}
				}
			}
			scanner.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void measureBuildingTime(BaseDict dict, HashMap<String, Integer> allWords) {
		for(int i = 0; i < 100; i++) {
			int counter = 0;
			long startTime = System.currentTimeMillis();
			for (String key : allWords.keySet()) {
				dict.append(key);
				counter++;
				if (counter % 1000 == 0)
					if (buildingResults.containsKey(counter))
						buildingResults.put(counter, buildingResults.get(counter) + System.currentTimeMillis() - startTime);
					else
						buildingResults.put(counter, System.currentTimeMillis() - startTime);
			}
		}
		for (int key : buildingResults.keySet())
			buildingResults.put(key, buildingResults.get(key) / 100);
	}
	
	public static ArrayList<String> measureSearchTime(BaseDict dict, String mode, int count) {
		ArrayList<String> result;
		String prefix;
		long startTime;
		long finishTime;
		if (!mode.equals("test")) {
			Scanner scanner = new Scanner(System.in); 
			System.out.print("Введите префикс"); 
			prefix = scanner.next(); 
			startTime = System.currentTimeMillis(); 
			result = dict.lookFor(prefix, count); 
			finishTime = System.currentTimeMillis();
			if (!lookingForResults.containsKey(count))
				lookingForResults.put(count, finishTime - startTime); 
			else
				lookingForResults.put(count, lookingForResults.get(count) - startTime + finishTime);
			scanner.close(); 
		}
		else {
			prefix = "п";
			startTime = System.currentTimeMillis(); 
			result = dict.lookFor(prefix, count); 
			finishTime = System.currentTimeMillis(); 
			if (!lookingForResults.containsKey(count))
				lookingForResults.put(count, finishTime - startTime); 
			else
				lookingForResults.put(count, lookingForResults.get(count) - startTime + finishTime);
		}
		return result;
	}
	
	public static void makeOutput(String mode, String dictName) {
		File file = new File(dictName + mode + "_results.txt");
		HashMap<Integer, Long> resultDict = new HashMap<Integer, Long>();
		if (mode.equals("building"))
			resultDict = buildingResults;
		else
			resultDict = lookingForResults;
		StringBuilder sb = new StringBuilder();
		for (int key : resultDict.keySet()) {
			sb.append(String.valueOf(key));
			sb.append("   ");
			sb.append(String.valueOf(resultDict.get(key)));
			sb.append("\r\n");
		}
		try (FileWriter writer = new FileWriter(file, false)){
			String str = sb.toString();
			writer.write(str);
		}
		catch (IOException ex) {
			System.out.print(ex.getMessage());
		}		
	}
	
	public static void clearTester() {
		if (!buildingResults.isEmpty())
			buildingResults.clear();
		if (!lookingForResults.isEmpty())
			lookingForResults.clear();
	}
}

	


