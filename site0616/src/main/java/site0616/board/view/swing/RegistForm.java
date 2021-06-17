package site0616.board.view.swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import site0616.board.model.dao.BoardDAO;
import site0616.model.domain.Board;

//우리가 정의한 DAO가 정말로 웹또는 응용분야 모두에서 사용가능한 중립적 클래스인지 
//테스트해보기 위한 Swing 프로그램 구현
public class RegistForm extends JFrame{
	JTextField t_title;
	JTextField t_writer;
	JTextArea t_content;
	JButton bt;
	BoardDAO boardDAO;
	
	public RegistForm() {
		t_title = new JTextField(20);
		t_writer = new JTextField(20);
		t_content = new JTextArea(12,20);
		bt = new JButton("등록");
		boardDAO = new BoardDAO();
		
		setLayout(new FlowLayout());
		add(t_title);
		add(t_writer);
		add(t_content);
		add(bt);
		
		//버튼과 리스너 연결
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				regist();
			}
		});
	
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(270,350);
	}
	
	public void regist() {
		Board board = new Board(); //empty상태
		board.setTitle(t_title.getText());
		board.setWriter(t_writer.getText());
		board.setContent(t_content.getText());
		
		int result = boardDAO.insert(board);
		
		if(result==0) {
			JOptionPane.showMessageDialog(this,"등록 실패");
		}else {
			JOptionPane.showMessageDialog(this,"등록 성공");
		}
	}
	
	public static void main(String[] args) {
		new RegistForm();
	}
	
}
