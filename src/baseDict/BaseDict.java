package baseDict;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public abstract class BaseDict {
	
	public abstract int getLength();
	
	public abstract void clear();
	
	public abstract String getName();
	
	public abstract int get(String key);
	
	public abstract void put(int value, String key);
	
	public abstract void append(String key);
	
	public abstract ArrayList<String> lookFor(String prefix, int count);
	
	protected ArrayList<String> processData(ArrayList<Integer> helpList, HashMap<Integer, ArrayList<String>> helpHashMap){
		ArrayList<String> result = new ArrayList<String>();
		Set<Integer> helpSet = new HashSet<Integer>();
		helpSet.addAll(helpList);
		helpList.clear();
		helpList.addAll(helpSet);
		Collections.sort(helpList);
		for (int collisions: helpList) {
			ArrayList<String> current = helpHashMap.get(collisions);
			Collections.sort(current);
			for (String item : current)
				result.add(item);
		}
		return result;
	}
}

