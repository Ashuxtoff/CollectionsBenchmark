package dicts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;


import baseDict.BaseDict;
import helpClasses.TwoTuple;

public class TreeSetDict extends BaseDict{

	private TreeSet<TwoTuple<String, Integer>> items = new TreeSet<TwoTuple<String, Integer>>();
	
	public int get(String key) {
		TwoTuple<String, Integer> item = items.tailSet(new TwoTuple<String, Integer>(key, 0)).first();
		return item.getSecond();
	}
	
	public void put(int value, String key) {
		TwoTuple<String, Integer> newItem = new TwoTuple<String, Integer>(key, value);
		items.add(newItem);
	}
	
	public void append(String key) {
		TwoTuple<String, Integer> floor = items.floor(new TwoTuple<String, Integer>(key, 0));
		if (floor == null || !floor.getFirst().equals(key))
			this.put(1, key);
		else
			floor.setSecond(floor.getSecond() + 1);
	}
	
	public ArrayList<String> lookFor(String prefix, int count) {
		prefix = prefix.toLowerCase();
		int counter = 0;
		ArrayList<Integer> helpList = new ArrayList<Integer>();
		HashMap<Integer, ArrayList<String>> helpHashMap = new HashMap<Integer, ArrayList<String>>();
		int prefixLength = prefix.length();
		for (TwoTuple<String, Integer> item: items) {
			String key = item.getFirst().toLowerCase();
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
		return items.size();
	}
	
	public String getName() {
		return "TreeSet";
	}

	public void clear() {
		items.clear();
		
	}
	
}
