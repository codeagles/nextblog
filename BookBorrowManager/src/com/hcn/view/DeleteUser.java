package com.hcn.view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;

import com.hcn.db.*;
import com.hcn.model.*;

public class DeleteUser extends JFrame {
	private JPanel buttonJP,deleteJP;
	private JScrollPane jscrollPane;
	private JTable jtable;
	private JButton delJB,exitJB;
	private String[] userrearch = {"编号","用户名","密码"};
	private Object[][] getSSelect(List<Users>list){
		Object[][] results = new Object[list.size()][userrearch.length];
		for(int i=0;i<list.size();i++){
			Users users = list.get(i);
			results[i][0]=users.getId();
			results[i][1]=users.getName();
			results[i][2]=users.getPassword();
		}
		return results;
	}
	public DeleteUser(){
		setBounds(300, 100, 550, 300);
		setTitle("用户删除");
		deleteJP = new JPanel();
		buttonJP = new JPanel();
		jscrollPane = new  JScrollPane();
		jscrollPane.setPreferredSize(new Dimension(340, 200));
		Object[][] results = getSSelect(UserDao.selectUser());
		jtable = new JTable(results,userrearch);
//		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jscrollPane.setViewportView(jtable);
		deleteJP.add(jscrollPane);
		delJB = new JButton("删除");
		exitJB= new JButton("退出");
		buttonJP.add(delJB);
		buttonJP.add(exitJB);
		this.add(deleteJP,new BorderLayout().CENTER);
		this.add(buttonJP,new BorderLayout().SOUTH);
		this.setVisible(true);
		setResizable(false);
		exitJB.addActionListener(new DeleteUser_exitListener());
		delJB.addActionListener(new DeleteActionListener());
	}
	
	public class DeleteUser_exitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}
	class DeleteActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			int selRow = jtable.getSelectedRow();
			int i  = UserDao.deleteUser(Integer.parseInt(jtable.getValueAt(selRow, 0).toString().trim()));
			if (i == 1){
				JOptionPane.showMessageDialog(null, "删除成功");
				Object[][] results = getSSelect(UserDao.selectUser());
				jtable = new JTable(results,userrearch);
				//jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jscrollPane.setViewportView(jtable);
			}
			
		}
		
	}	
	public static void main(String[] args) {
		new DeleteUser();

	}

}
