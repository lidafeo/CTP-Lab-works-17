import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class FractalExplorer
{
	private int size;
	private JImageDisplay im;
	private FractalGenerator fractal;
	private Rectangle2D.Double rect;
	
	public FractalExplorer(int s)
	{
		size=s;
		rect = new Rectangle2D.Double();
		im = new JImageDisplay(s, s);
		fractal = new Mandelbrot();
        fractal.getInitialRange(rect);
	}
	//Создание и реализация окна с фракталом
	void createAndShowGUI()
	{
		JFrame f = new JFrame("Fractal");
		im.addMouseListener( new mouse_listener());
		f.add(im, BorderLayout.CENTER);
		JButton but = new JButton("Reset");
		but.addActionListener(new act_listener());
		f.add(but, BorderLayout.SOUTH);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setVisible(true);
		f.setResizable(false);
	}
	//Прорисовка фрактала
	void drawFractal()
	{
		float hue;
		int rgbColor, numIters;
		double xCoord, yCoord;
		for(int x=0;x<size;x++)
			for(int y=0;y<size;y++)
			{
				xCoord = FractalGenerator.getCoord(rect.x, rect.x+rect.width, size, x);
				yCoord = FractalGenerator.getCoord(rect.y, rect.y+rect.width, size, y);
				numIters = fractal.numIterations(xCoord, yCoord);
				if(numIters==-1)
					rgbColor = 0;
				else
				{
					hue = 0.7f+(float)numIters/200f;
					rgbColor = Color.HSBtoRGB(hue,1f,1f);
				}
				im.drawPixel(x,y,rgbColor);
			}
		im.repaint();
	}
	//класс для обработки событий ActionListener от кнопки сброса
	private class act_listener implements ActionListener
	{
		//Сброс диапазона отображения в начальное значение
		//и перерисовка фрактала
		public void actionPerformed(ActionEvent e)
		{
			fractal.getInitialRange(rect);
			im.clearImage();
			drawFractal();
		}
	}
	//Класс для обработки событий MouseListener (обработка щелчка мыши)
	private class mouse_listener extends MouseAdapter
	{
		//Преобразование координат точки изображения в отображаемую
		//область фрактала
		public void mouseClicked(MouseEvent e)
		{
			super.mouseClicked(e);
			int x,y;
			double xCoord,yCoord;
			x=e.getX();
			y=e.getY();
			xCoord = FractalGenerator.getCoord(rect.x, rect.x+rect.width, size, x);
			yCoord = FractalGenerator.getCoord(rect.y, rect.y+rect.height, size, y);
			fractal.recenterAndZoomRange(rect,xCoord,yCoord,0.5);
			im.clearImage();
			drawFractal();
		}
	}
	//Главный метод
	public static void main(String[] args)
	{
		FractalExplorer ex = new FractalExplorer(600);
		ex.createAndShowGUI();
		ex.drawFractal();
	}
}