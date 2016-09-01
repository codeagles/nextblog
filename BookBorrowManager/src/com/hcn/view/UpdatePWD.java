package com.hcn.view;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import com.hcn.model.*;
import com.hcn.db.UserDao;

public class UpdatePWD extends JFrame {

	private JLabel userJL, passPWDJL, newPWDJL, confirmPWDJL;
	private JPanel PWDJP, buttonJP;
	private JTextField userJTF, passPWDJTF, newPWDJTF, confirmPWDJTF;
	private JButton okJB,escJB;

	public UpdatePWD() {
		setBounds(400, 200, 300, 200);
		setTitle("修改密码");
		
		PWDJP = new JPanel();
		PWDJP.setLayout(new GridLayout(4, 2));
		buttonJP = new JPanel();
		//
		userJL = new JLabel("用户名：  ");
		userJL.setHorizontalAlignment(SwingConstants.CENTER);
		passPWDJL = new JLabel("原密码：  ");
		passPWDJL.setHorizontalAlignment(SwingConstants.CENTER);
		newPWDJL = new JLabel("新密码：  ");
		newPWDJL.setHorizontalAlignment(SwingConstants.CENTER);
		confirmPWDJL = new JLabel("确认密码：  ");
		confirmPWDJL.setHorizontalAlignment(SwingConstants.CENTER);
		userJTF = new JTextField();
		userJTF.setEditable(false);
		passPWDJTF = new JTextField();
		newPWDJTF = new JTextField();
		confirmPWDJTF = new JTextField();
		PWDJP.add(userJL);
		PWDJP.add(userJTF);
		PWDJP.add(passPWDJL);
		PWDJP.add(passPWDJTF);
		PWDJP.add(newPWDJL);
		PWDJP.add(newPWDJTF);
		PWDJP.add(confirmPWDJL);
		PWDJP.add(confirmPWDJTF);
		
		 okJB = new JButton("确认");
		 escJB = new JButton("取消");
		 buttonJP.add(okJB);
		 buttonJP.add(escJB);
		 this.setAlwaysOnTop(true);
		this.add(PWDJP,new BorderLayout().CENTER);
		this.add(buttonJP,new BorderLayout().SOUTH);
		this.setVisible(true);
		setResizable(false);
		okJB.addActionListener(new ConfirmActionListener());
		escJB.addActionListener(new UpdatePWD_escListener());
		userJTF.setText(Login.operator);
		
	}
	public class UpdatePWD_escListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}
	class ConfirmActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String name = userJTF.getText().trim();
			String oldpassword = passPWDJTF.getText().trim();
			String newpassword = newPWDJTF.getText().trim();
			String confirmpassword = confirmPWDJTF.getText().trim();
			if(!oldpassword.equals(UserDao.SelectOldPwd(name))){
				JOptionPane.showMessageDialog(null,"原始密码不正确!");
				return;
			}
			if(!newpassword.equals(confirmpassword)){
				JOptionPane.showMessageDialog(null,"两次密码不一致！!");
				return;
			}
			int i=UserDao.ChangePwd(newpassword, name);
			if(i!=0){
				JOptionPane.showMessageDialog(null,"修改成功！!");
			}
			
		}
		
	}
 	public static void main(String[] args) {
		new UpdatePWD();

	}

}
