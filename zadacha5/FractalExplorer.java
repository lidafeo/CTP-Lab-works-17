import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.imageio.ImageIO;
import java.io.IOException;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FractalExplorer
{
	private int size;
	private JImageDisplay im;
	private FractalGenerator fractal;
	private Rectangle2D.Double rect;
	private JComboBox<FractalGenerator> jbox;
	private JFrame frame;
	
	public FractalExplorer(int s)
	{
		size=s;
		rect = new Rectangle2D.Double();
		im = new JImageDisplay(s, s);
		fractal = new Mandelbrot();
        fractal.getInitialRange(rect);
		jbox = new JComboBox<>();
		frame = new JFrame("Fractal");
	}
	//Создание и реализация окна с фракталом
	void createAndShowGUI()
	{
		JPanel jp = new JPanel();
		
		im.addMouseListener( new mouse_listener());
		frame.add(im, BorderLayout.CENTER);
		//Кнопка для сброса
		JButton but = new JButton("Reset Display");
		but.setActionCommand("Reset");
		but.addActionListener(new act_listener());
		//Кнопка для сохранения
		JButton but2 = new JButton("Save Image");
		but2.setActionCommand("Save");
		but2.addActionListener(new act_listener());
		
		jp.add(but, BorderLayout.CENTER);
		jp.add(but2, BorderLayout.CENTER);
		frame.add(jp, BorderLayout.SOUTH);
		
		JPanel boxpanel = new JPanel();
		JLabel boxlabel = new JLabel("Fractal");
		jbox.addItem(new Mandelbrot());
		jbox.addItem(new Tricorn());
		jbox.addItem(new BurningShip());
		jbox.addActionListener(new act_listener());
		boxpanel.add(boxlabel, BorderLayout.CENTER);
		boxpanel.add(jbox, BorderLayout.CENTER);
		frame.add(boxpanel, BorderLayout.NORTH);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
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
	//Класс для обработки событий ActionListener от кнопки сброса и сохранения
	private class act_listener implements ActionListener
	{
		//Сброс диапазона отображения в начальное значение
		//или сохранение текущего изображения фрактала
		public void actionPerformed(ActionEvent e)
		{
			 String str = e.getActionCommand();
			if(e.getSource()==jbox)
			{
				fractal = (FractalGenerator)jbox.getSelectedItem();
				fractal.getInitialRange(rect);
				im.clearImage();
				drawFractal();
			}
			 else if (str.equals("Reset")) 
			{
                fractal.getInitialRange(rect);
                im.clearImage();
                drawFractal();
            }
			else if (str.equals("Save"))
			{
				JFileChooser chooser = new JFileChooser();
				FileFilter filter = new FileNameExtensionFilter("PNG Images","png");
				chooser.setFileFilter(filter);
				chooser.setAcceptAllFileFilterUsed(false);
				if (chooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION)
				{
					try
					{ImageIO.write(im.getim(),"png",chooser.getSelectedFile());}
					catch (IOException io)
					{JOptionPane.showMessageDialog(frame,io.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);}
				}
			}
			else
				return;
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