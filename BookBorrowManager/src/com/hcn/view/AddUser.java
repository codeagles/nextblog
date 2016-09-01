package com.hcn.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.hcn.db.UserDao;
import com.hcn.view.UpdatePWD.UpdatePWD_escListener;

public class AddUser extends JFrame {

	private JTextField userJTF, pwdJTF;
	private JPanel PWDJP, buttonJP;
	private JButton addJB,escJB;
	private JLabel userJL, pwdJL;
	public AddUser(){
		setBounds(400, 200, 300, 150);
		setTitle("添加用户");
		PWDJP = new JPanel();
		PWDJP.setLayout(new GridLayout(2, 2));
		buttonJP = new JPanel();
		//
		userJL = new JLabel("用户名：  ");
		userJL.setHorizontalAlignment(SwingConstants.CENTER);
		pwdJL = new JLabel("密码：  ");
		pwdJL.setHorizontalAlignment(SwingConstants.CENTER);
		userJTF = new JTextField();
		pwdJTF = new JTextField();
		PWDJP.add(userJL);
		PWDJP.add(userJTF);
		PWDJP.add(pwdJL);
		PWDJP.add(pwdJTF);
		addJB = new JButton("确认");
		 escJB = new JButton("取消");
		 buttonJP.add(addJB);
		 buttonJP.add(escJB);
		this.add(PWDJP,new BorderLayout().CENTER);
		this.add(buttonJP,new BorderLayout().SOUTH);
		this.setVisible(true);
		setResizable(false);
		escJB.addActionListener(new AddUser_escListener());
		addJB.addActionListener(new AddActionListener());
	}
	public class AddUser_escListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	class AddActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String user = userJTF.getText().trim();
			String password = pwdJTF.getText().trim();
			int i = UserDao.insertUser(user, password);
			if(i==1){
				JOptionPane.showMessageDialog(null, "插入成功");
				AddUser.this.setVisible(true);
			}
		}
		
	}
	public static void main(String[] args) {
		new AddUser();

	}

}
