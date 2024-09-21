package class19;

// 这个问题leetcode上可以直接测
// 链接：https://leetcode.com/problems/longest-common-subsequence/
public class Code041_LongestCommonSubsequence {

	public static void main(String[] args) {
		int num = longestCommonSubsequence1("lsdjfsdkjdf","sdfdslkkkkkk");
		System.out.println(num);
	}

	public static int longestCommonSubsequence1(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
			return 0;
		}
		char[] str1 = s1.toCharArray();
		char[] str2 = s2.toCharArray();
		// 尝试
		return process1(str1, str2, str1.length - 1, str2.length - 1);
	}

	public static int process1(char[] str1,char[] str2,int p1,int p2){
		if(p1 == 0 && p2 == 0){
			return str1[p1] == str2[p2] ? 1:0;
		}else if(p1 == 0){
			if(str1[p1]==str2[p2]){
				return 1;
			}else{
				return process1(str1,str2,p1,p2-1);
			}
		}else if(p2 == 0){
			if(str1[p1]==str2[p2]){
				return 1;
			}else{
				return process1(str1,str2,p1-1,p2);
			}
		}else{
			int s1 = process1(str1,str2,p1-1,p2);
			int s2 = process1(str1,str2,p1,p2-1);
			int s3 = str1[p1]==str2[p2]?1+process1(str1,str2,p1-1,p2-1):0;
            return Math.max(s1,Math.max(s2,s3));
		}
	}

}
