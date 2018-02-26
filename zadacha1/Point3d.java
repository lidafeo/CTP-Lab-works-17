//��������� ����� �����
public class Point3d
{
	private double xCoord;
	private double yCoord;
	private double zCoord;
	//�����������, ������� �������������� �����
	public Point3d(double x, double y, double z)
	{
		xCoord=x;
		yCoord=y;
		zCoord=z;
	}
	//����������� ��� ����������
	public Point3d()
	{
		xCoord=0;
		yCoord=0;
		zCoord=0;
	}
	//��������� ���������� �����
	public double getX()
	{ return xCoord; }
	public double getY()
	{ return yCoord; }
	public double getZ()
	{ return zCoord; }
	//����� ���������� �����
	public void setX(double v)
	{ xCoord=v; }
	public void setY(double v)
	{ yCoord=v; }
	public void setZ(double v)
	{ zCoord=v; }
	//��������� ���� �����
	public boolean ravno(Point3d a)
	{
		if(xCoord!=a.getX() || yCoord!=a.getY() || zCoord!=a.getZ())
			return false;
		return true;
	}
	//���������� ���������� ����� ����� �������
	public double distanceTo(Point3d a)
	{
		return Math.sqrt(Math.pow(a.getX()-xCoord,2) + Math.pow(a.getY()-yCoord,2) + Math.pow(a.getZ()-zCoord,2));
	}
}