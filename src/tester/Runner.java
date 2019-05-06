package tester;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import baseDict.BaseDict;
import dicts.ArrayListDict;
import dicts.HashMapDict;
import dicts.LinkedListDict;
import dicts.TreeSetDict;

public class Runner {

	public static void main(String[] args) {
		ArrayListDict ALDict = new ArrayListDict();
		LinkedListDict LLDict = new LinkedListDict();
		HashMapDict HTDict = new HashMapDict();
		TreeSetDict TSDict = new TreeSetDict();		
		
		ArrayList<BaseDict> dicts = new ArrayList<BaseDict>();
		dicts.add(ALDict);
		dicts.add(LLDict);
		dicts.add(HTDict);
		dicts.add(TSDict);
		
		File WarAndPeace = new File("C:\\Users\\Александр\\eclipse-workspace\\CollectionsBenchmark\\src\\tester", "WarAndPeace.txt");
		HashMap<String, Integer> fullFreqDict = Tester.scanFile(WarAndPeace, 50000);
		
		int countSearchingWords = 0;
		for (String key : fullFreqDict.keySet()) {
			if (key.toLowerCase().charAt(0) == 'п')
				countSearchingWords++;
		}		
				
		int fullSize = fullFreqDict.size();
		for (BaseDict dict : dicts) {
			Tester.measureBuildingTime(dict, fullFreqDict);
			Tester.makeOutput("building", dict.getName());
			Tester.clearTester();
		}
		
		for (BaseDict dict : dicts) {
			for (String key : fullFreqDict.keySet())
				dict.append(key);
			for (int countSearching = 500; countSearching <= countSearchingWords; countSearching += 500) {
				for (int i = 0; i < 100; i++)
					Tester.measureSearchTime(dict, "test", countSearching);
			}
			for (int key : Tester.lookingForResults.keySet())
				Tester.lookingForResults.put(key, Tester.lookingForResults.get(key) / 100);
			Tester.makeOutput("search", dict.getName());
		}
	}
}
