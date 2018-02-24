//Описание класса
public class Palindrome
{
	//Описание метода
	public static void main(String[] args)
	{
		for(int i=0; i<args.length;i=i+1)
		{
			String s = args[i];
			System.out.print(" "+isPalindrome(s));
		}
	}
	//Метод, изменяющий символы в строке
	public static String reverseString(String s)
	{
		String t="";
		for(int i=s.length()-1;i>=0;i=i-1)
			t=t+s.charAt(i);
		return t;
	}
	//Метод, проверяющий, является ли строка палиндромом
	public static boolean isPalindrome(String s)
	{
		String s1 = reverseString(s);
		return s.equals(s1);
	}
}