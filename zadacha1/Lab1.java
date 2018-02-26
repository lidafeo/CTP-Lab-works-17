import java.util.Scanner;
//�����, ����������� ������� ������������
public class Lab1
{
	//���� 3�� ����� � ���������� �������
	public static void main(String[] args)
	{
		Point3d[] po = new Point3d[]{new Point3d(), new Point3d(), new Point3d()};
		Scanner scanner = new Scanner(System.in);
		for(int i =0;i<3;i++)
		{
			System.out.print("������� ���������� �����\n");
			po[i].setX(Double.parseDouble(scanner.next()));
			po[i].setY(Double.parseDouble(scanner.next()));
			po[i].setZ(Double.parseDouble(scanner.next()));
		}
		//��������� �����
		if(po[0].ravno(po[1])==true || po[0].ravno(po[2])==true || po[1].ravno(po[2])==true)
			System.out.print("����� �����\n");
		//����� �������
		else
			System.out.print("������� ����� "+ computeArea(po[0],po[1],po[2]));
	}
	//���������� ������� ������������
	public static double computeArea(Point3d a, Point3d b, Point3d c)
	{
		double ab, ac, bc;
		ab=a.distanceTo(b);
		ac=a.distanceTo(c);
		bc=b.distanceTo(c);
		double p=0.5*(ab+ac+bc);
		return Math.sqrt(p*(p-ab)*(p-ac)*(p-bc));
	}
}