package dicts;

import java.util.ArrayList;
import java.util.HashMap;

import baseDict.BaseDict;

public class HashMapDict extends BaseDict{

	private HashMap<String, Integer> dict = new HashMap<String, Integer>();	
	
	public int get(String key) {
		return dict.get(key);
	}

	public void put(int value, String key) {
		dict.put(key, value);
	}

	public void append(String key) {
		if (dict.containsKey(key))
			this.put(dict.get(key) + 1, key);
		else
			this.put(1, key);
	}

	public ArrayList<String> lookFor(String prefix, int count) {
		prefix = prefix.toLowerCase();
		int counter = 0;
		ArrayList<Integer> helpList = new ArrayList<Integer>();
		HashMap<Integer, ArrayList<String>> helpHashMap = new HashMap<Integer, ArrayList<String>>();
		int prefixLength = prefix.length();
		for (String key : dict.keySet()) {
			key = key.toLowerCase();
			if (key.length() >= prefixLength && prefix.equals(key.substring(0, prefixLength))) {
				helpList.add(this.get(key));
				if (!helpHashMap.containsKey(this.get(key))) 
					helpHashMap.put(this.get(key), new ArrayList<String>());
				helpHashMap.get(this.get(key)).add(key);
				counter++;
				if (counter == count)
					break;
			}		
		}
		return super.processData(helpList, helpHashMap);
	}

	public int getLength() {
		return dict.size();
	}
	
	public String getName() {
		return "HashMap";
	}

	public void clear() {
		dict.clear();		
	}
	
	
}
