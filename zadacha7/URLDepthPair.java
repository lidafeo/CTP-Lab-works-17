import java.io.*;
import java.net.*;
import java.util.*;
public class URLDepthPair
{
	private String url;
	private int h;
	public static final String URL_PREFIX = "http://";
	URLDepthPair(String str, int hh)
	{
		url=str;
		h=hh;
	}
	//Распечатывает содержимое пары
	public String toString()
	{
		return url+" "+h;
	}
	//Разбивает URL на протокол, хост и адрес
	public String[] parse()
	{
		String[] temp = new String[3];
        temp[0] = "";
        temp[1] = "";
        temp[2] = "";
        try 
		{
            java.net.URL u = new java.net.URL(url);
            temp[0] = u.getProtocol();
            temp[1] = u.getHost();
            temp[2] = u.getPath();
        }
		catch(MalformedURLException e)
		{
            System.out.println("Error");
            return temp;
        }
        return temp;
	}
	//Проверяет, равен ли протокол http
	public boolean check()
	{
		return url.startsWith(URL_PREFIX);
	}
	//Возвращает глубину поиска
	public int geth()
	{
		return h;
	}
}