package July24;
class Ques_64 {

public static void main(String s[]) {

String str1 = "abc";

String str2 = new String (str1);

String str3 = new String ("abc");

String str4 = new String ("Abc");

System.out.print((str1 == str3) + “ “);

System.out.print(str4.equalsIgnoreCase(str1) + “ “);

System.out.print((str2 == str3) + “ “);

System.out.print(str2 == str4);

}

}