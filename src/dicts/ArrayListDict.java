package dicts;

import java.util.ArrayList;
import java.util.Locale;
import java.util.HashMap;

import baseDict.BaseDict;

public class ArrayListDict extends BaseDict{

private ArrayList<String> keys = new ArrayList<String>();
private ArrayList<Integer> values = new ArrayList<Integer>();


	public void append(String key) {
		if (keys.contains(key))
			put(this.get(key) + 1, key);
		else {
			keys.add(key);
			values.add(1);
		}
	}

	public ArrayList<String> lookFor(String prefix, int count) {
		prefix = prefix.toLowerCase();
		int counter = 0;
		ArrayList<Integer> helpList = new ArrayList<Integer>();
		HashMap<Integer, ArrayList<String>> helpHashMap = new HashMap<Integer, ArrayList<String>>();
		int prefixLength = prefix.length();
		for (String key : keys) {
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

	public int get(String key) {
		return values.get(keys.indexOf(key));		
	}

	public void put(int value, String key) {
		values.set(keys.indexOf(key), value);
	}

	public int getLength() {
		return keys.size();
	}
	
	public String getName() {
		return "ArrayList";
	}

	public void clear() {
		keys.clear();
		values.clear();		
	}
}
