package test;
//import javax.swing.JButton; //rt.jar jdk��ġ�Ҷ� �̹� jar�� ����
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//jsp���̵�, ���������� ����� ������ �� �ְ� �Ʒ��� ���� �������� �ؼ� �� ����Ǵ� Ŭ������ ������ ����(Servlet)�̶� �ϸ�
//javaEE

//���� Ŭ������ ���������� ����Ǹ� Ŭ���̾�Ʈ�� ����û�� �ް� ������ ó���ϴ� ���� Ŭ�����̴�!!
//���� jsp��� ���������ε� �� ������ �����ϴ�!!

//������ �����ֱ� �޼���� �Ҹ��� �ֿ� �޼��尡 �����ϸ� �� �����ֱ� �޼���� �ϳ��� ���� ��ü��
//�¾�� ���ϸ� �Ҹ��ϴ� ������ ������ �ֿ� �޼����̴�

//���� Ŭ������ ��ġ�� WEB-INF/classes�̹Ƿ� ���������� url�� ���� ȣ���� �� ����
//���� ����(mapping)�� �̿��Ѵ�
public class MyServlet extends HttpServlet{
	//���� �ν��Ͻ��� �¾ ��, ������ �ʱ�ȭ �۾��� ȣ��Ǵ� �޼���
	public void init(){
		System.out.println("���� ���� �� �ʱ�ȭ �Ϸ�");
	}

	//�ʱ�ȭ�� �Ϸ��� ������ ��Ŭ���̾�Ʈ�� ��û�� ó���� �� �����ϴ� �޼���
	public void service(HttpServletRequest request, HttpServletResponse response){
		System.out.println("Ŭ���̾�Ʈ�� ��û ó��");
	}

	//������ �Ҹ��� �� ȣ��Ǵ� �޼���
	public void destroy(){
		System.out.println("���� ����");
	}
	
}