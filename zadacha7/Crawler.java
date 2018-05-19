import java.io.*;
import java.net.*;
import java.util.*;
public class Crawler extends Thread
{
	private LinkedList <URLDepthPair> found;
	private LinkedList <URLDepthPair> notfound;
	private static final String URL_PREFIX = "http";
	private int maxh;
	private int port = 80;
	Crawler(String str, int hh)
	{
		maxh=hh;
		found = new LinkedList <URLDepthPair>();
		notfound = new LinkedList <URLDepthPair>();
		notfound.add(new URLDepthPair(str,0));
	}
	//Возвращает список всех пар, которые были посещены
	public LinkedList<URLDepthPair> getSites()
	{
		  //Пока есть непроверенные сайты
        while(!notfound.isEmpty() && notfound.getFirst().geth()<=maxh)
		{
            try
			{
                String[] URL = notfound.getFirst().parse();
                if(!URL[0].equalsIgnoreCase(URL_PREFIX))
				{
                    notfound.removeLast();
                    continue;
                }
                //Пересохраняем глубину
                int h = notfound.getFirst().geth();
                //Создаём сокет
                Socket socket = new Socket(URL[1], port);
                //Время ожидания сокета
                socket.setSoTimeout(5000);
                OutputStream outStream = socket.getOutputStream();
                PrintWriter pWriter = new PrintWriter(outStream, true);
                //Отправляем сообщение серверу
                pWriter.println("GET "+URL[2]+" HTTP/1.1");
                pWriter.println("Host: "+URL[1]);
                pWriter.println("Connection: close");
                pWriter.println();
                InputStream in = socket.getInputStream();
                InputStreamReader inReader = new InputStreamReader(in);
                BufferedReader br = new BufferedReader(inReader);
                //Читаем первую строку ответа
                String line = br.readLine();
                while (line!=null)
				{
					//Пока есть ссылка
                    while(line.contains("href=\""))
					{
                        int start = line.indexOf("href=\"");
                        line = line.substring(start+"href=\"".length());
                        String subline = line.substring(0,line.indexOf("\""));
                        //Создаём пару и если ее нет в списках, сохраняем
                        URLDepthPair pair = new URLDepthPair(subline, h+1);
                        if (!notfound.contains(pair)&& !found.contains(pair))
                            notfound.add(pair);
                    }
                    // Читаем следующую строку
                    line=br.readLine();
                }
                //Переносим ссылку из ненайденного списка в найденные
                found.add(notfound.getFirst());
                notfound.removeFirst();
                socket.close();
            }
            // Если не получилось проанализировать ссылку
            catch(IOException e){
                // Выводим соответствующее сообщение
                System.out.println("Unable to connect");
                found.add(notfound.getFirst());
                notfound.removeFirst();
            }
        }
        // Возвращаем список найденных ссылок
        return found;
	}
	//Главный метод
	public static void main(String[] args)
	{
		   if (args.length < 2)
		   {
            System.out.println("Not enough arguments provided.");
            return;
			}
		String[] str = args;
        int h = Integer.parseInt(args[args.length-1]);
        String[] u= new String[str.length-1];
        for(int i=0;i<str.length-1;i++)
            u[i] = str[i];
        Crawler[] craw = new Crawler[u.length];
        for(int i=0;i<craw.length;i++)
		{
            craw[i] = new Crawler(u[i],h);
            craw[i].start();
		}
	}
	//Получает список и выводит его
	public void run() 
	{
        LinkedList<URLDepthPair> list = getSites();
        while(!list.isEmpty())
            System.out.println(list.removeFirst());
    }
}