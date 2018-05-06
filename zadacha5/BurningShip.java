import java.awt.geom.Rectangle2D;
public class BurningShip extends FractalGenerator
{
	public static final int MAX_ITERATIONS=2000;
	//Указывает генератору фрактала, какая часть комплексной области
	//"интересна" для вычисления фрактала
	 public void getInitialRange(Rectangle2D.Double rec)
	{
		rec.x=-2;
		rec.y=-2.5;
		rec.width=4;
		rec.height=4;
	}
	//Реализация итеративной функции фрактала "Горящий корабль"
	 public int numIterations(double x, double y)
	{
		double re=0, re1;
		double im=0, im1;
		for(int i=0;i<MAX_ITERATIONS;i++)
		{
			re1=re*re-im*im+x;
			im1=Math.abs(2*re*im)+y;
			if((im*im+re*re)>4)
				return i;
			re=re1;
			im=im1;
		}
	return -1;
	}
	//Возвращает название фрактала
	public String toString()
	{
		return "Burning Ship";
	}
}