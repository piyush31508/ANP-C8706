package ANP;

import java.util.Scanner;
public class rev {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s;
		s=sc.nextLine();
		String r="";
		char c;
		for(int i=0;i<s.length();i++)
		{
			c=s.charAt(i);
			r=c+r;
		}
		System.out.println(r);
	}
}
