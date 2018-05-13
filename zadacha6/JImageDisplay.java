import java.awt.*;
import javax.swing.JComponent;
import java.awt.image.BufferedImage;
public class JImageDisplay extends JComponent
{
	private BufferedImage image;
	//Конструктор
	public JImageDisplay(int Width, int Height)
	{
		image = new BufferedImage(Width,Height,BufferedImage.TYPE_INT_RGB);
		super.setPreferredSize(new java.awt.Dimension(Width, Height));
	}
	//Метод, который устанавливает
	//все пиксели в изображении к черному цвету
	public void clearImage()
	{
		int arr_len;
		if (image.getHeight() > image.getWidth())
			arr_len = image.getHeight();
		else                                 
			arr_len = image.getWidth();
		int[] col = new int[arr_len];
		image.setRGB(0,0,image.getWidth()-1,image.getHeight()-1,col,0,0);
	}
	//Метод, который устанавливает определенный цвет 
	//для пикселя
	public void drawPixel(int x, int y, int rgbColor)
	{
		image.setRGB(x,y,rgbColor);
	}
	//Метод, который выводит на экран данное изображение
	public void paintComponent( Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
	}
	//Возвращает рендерингованное изображение
	public BufferedImage getim()
	{
		return image;
	}
	
}