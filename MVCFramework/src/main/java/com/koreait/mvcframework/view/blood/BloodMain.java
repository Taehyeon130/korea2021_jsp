package com.koreait.mvcframework.view.blood;

import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.koreait.mvcframework.model.blood.BloodService;

public class BloodMain extends JFrame{
	JComboBox<String> ch;
	JButton bt;
	String[] arr = {"A형","B형","O형","AB형"};
	BloodService service;
	
	
	public BloodMain() {
		ch = new JComboBox<String>(arr);
		bt = new JButton("결과 보기");
		service = new BloodService();
		
		setLayout(new FlowLayout());
		
		add(ch);
		add(bt);
		
		
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = service.getAdvice((String)ch.getSelectedItem());
				JOptionPane.showMessageDialog(BloodMain.this, msg);
			}
		});
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300,200);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new BloodMain();
	}
}
