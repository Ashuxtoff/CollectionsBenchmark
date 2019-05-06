package dicts;

import java.util.ArrayList;

import baseDict.BaseDict;

public class DictsTester {

	public static void main(String[] args) {
		ArrayListDict ALDict = new ArrayListDict();
		LinkedListDict LLDict = new LinkedListDict();
		HashMapDict HTDict = new HashMapDict();
		TreeSetDict TSDict = new TreeSetDict();
		
		TSDict.append("aaaa");
		TSDict.append("abbbb");
		TSDict.append("a");
		TSDict.append("asse");
		TSDict.append("aas");
		TSDict.append("ar");
		TSDict.append("afv");
		TSDict.append("aaa");
		TSDict.append("aa");
		TSDict.append("aaaa");
		TSDict.append("aaaabb");
		TSDict.append("aabaab");
		TSDict.append("aaaaabbb");
		TSDict.append("aaaaarb");
		
		ArrayList<String> res = TSDict.lookFor("a", 12);
		int a = 1;
		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i));
			if (i == 9) break;
		}
	}		
}