package class17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Code031_PrintAllSubsquences {

	public static void main(String[] args) {
		List<String> ans = subs("abc");
		for (String an : ans){
			System.out.println(an);
		}
	}

	public static List<String> subs(String s) {
		char[] chars = s.toCharArray();
		List<String> ans = new ArrayList<>();
		String path = "";
		int index = 0;
		process(chars,index,ans,path);
		return ans;
	}

	public static void process(char[] chars,int index,List<String> ans,String path){
		if(index == chars.length){
			ans.add(path);
			return ;
		}

		process(chars,index+1,ans,path+chars[index]);
		process(chars,index+1,ans,path);
	}
}
