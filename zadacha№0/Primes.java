//Класс Primes
public class Primes 
{
	//Гланый метод, выводящий простые числа
	public static void main(String[] args)
	{
		//Реализация программы
		for(int i=2;i<=100;i++)
			if(isPrime(i)==true)
			System.out.print(" "+i);
	}
	public static boolean isPrime(int n)
	{
		//Проверка числа на простое
		for(int i=2;i<n;i=i+1)
		{
			if(n%i==0)
				return false;
			}
			return true;
			}
}
