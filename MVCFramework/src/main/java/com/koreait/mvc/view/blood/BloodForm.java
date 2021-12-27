package com.koreait.mvc.view.blood;

import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.koreait.mvc.model.blood.BloodManager;

public class BloodForm extends JFrame{
	Choice ch;
	JButton bt;
	BloodManager bloodManager;
	
	public BloodForm() {
		ch = new Choice();
		bt = new JButton("결과보기");
		bloodManager = new BloodManager();
		
		ch.add("A");
		ch.add("B");
		ch.add("AB");
		ch.add("O");
		
		//스타일 
		setLayout(new FlowLayout());
		
		//조립
		add(ch);
		add(bt);
		
		//이벤트리스너 연결
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getResult();
			}
		});
		
		setSize(300,150);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void getResult() {
		String msg=bloodManager.getAdvice(ch.getSelectedItem());
		JOptionPane.showMessageDialog(this, msg);
	}
	
	public static void main(String[] args) {
		new BloodForm();
	}
}




