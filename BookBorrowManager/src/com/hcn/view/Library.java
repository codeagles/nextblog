package com.hcn.view;

import java.awt.Toolkit;
import java.awt.Desktop.Action;
import java.awt.event.*;
import java.awt.*;

import javax.print.DocFlavor.READER;
import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/*
 * 图书借阅管理系统主窗体
 * 实现菜单栏的设计
 * 顺序：从上到下，从左到右。
 * */
public class Library extends JFrame {
	// 序列化，为了保持版本的兼容性，即在版本升级时反序列化仍保持对象的唯一性。
	private static final long serialVersionUID = 1L;

	// 在此程序中，通过构造方法Library()中实现界面的初始化。
	public Library() {
		setSize(800, 600);
		setTitle("图书借阅系统");
		JMenuBar jmenuBar = createJMenuBar();// 创建菜单栏
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width / 6 + 50, height / 8 - 50);
		setJMenuBar(jmenuBar);// 添加菜单栏到主窗体，设置主窗体菜单栏。
		// 关闭窗口时退出程序
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	/*
	 * 此程序中，自定义方法createJMenuBar(),返回一个JMenuBar对象，来创建
	 * 菜单栏的功能，在构造方法中Library()中调用该方法。
	 */
	private JMenuBar createJMenuBar() {
		JMenuBar jMenuBar = new JMenuBar();// 菜单栏
		// 读者信息管理菜单
		JMenu readerManageJMenu = new JMenu("读者信息管理");
		JMenuItem readerAddJMI = new JMenuItem("读者信息添加");
		readerManageJMenu.add(readerAddJMI);
		JMenuItem readerSelUpdJMI = new JMenuItem("读者信息查询与修改");
		readerManageJMenu.add(readerSelUpdJMI);
		// 图书信息管理菜单
		JMenu bookManageJMenu = new JMenu("图书信息管理");
		JMenuItem bookAddJMI = new JMenuItem("图书信息添加");
		bookManageJMenu.add(bookAddJMI);
		JMenuItem bookSelUpdJMI = new JMenuItem("图书信息查询与修改");
		bookManageJMenu.add(bookSelUpdJMI);
		// 图书借阅菜单
		JMenu bookBorrowJMenu = new JMenu("图书借阅管理");
		JMenuItem bookBorrowJMI = new JMenuItem("图书借阅");
		JMenuItem bookReturnJMI = new JMenuItem("图书归还");
		bookBorrowJMenu.add(bookBorrowJMI);
		bookBorrowJMenu.add(bookReturnJMI);
		// 基础信息维护菜单
		JMenu baseInfoJMenu = new JMenu("基础信息维护");
		JMenuItem readerTypeJMI = new JMenuItem("读者类别设置");
		JMenuItem bookTypeJMI = new JMenuItem("图书类别设置");
		JMenuItem fineSetJMI = new JMenuItem("罚金设置");
		baseInfoJMenu.add(readerTypeJMI);
		baseInfoJMenu.add(bookTypeJMI);
		baseInfoJMenu.add(fineSetJMI);
		// 用户管理菜单
		JMenu userManageJMenu = new JMenu("用户管理");
		JMenuItem updPwdJMI = new JMenuItem("修改密码");
		userManageJMenu.add(updPwdJMI);
		JMenuItem userAddJMI = new JMenuItem("用户添加");
		userManageJMenu.add(userAddJMI);
		JMenuItem userDelJMI = new JMenuItem("用户删除");
		userManageJMenu.add(userDelJMI);
		// 将各个菜单添加到菜单栏中
		jMenuBar.add(bookManageJMenu);
		jMenuBar.add(readerManageJMenu);
		jMenuBar.add(bookBorrowJMenu);
		jMenuBar.add(baseInfoJMenu);
		jMenuBar.add(userManageJMenu);
		// createJMenuBar()返回jMenuBar对象
		readerAddJMI.addActionListener(new ReaderAddListener());
		readerSelUpdJMI.addActionListener(new ReaderSelectandUpdateListener());
		bookAddJMI.addActionListener(new BookAddListener());
		bookSelUpdJMI.addActionListener(new BookSelectandUpdateListener());
		bookReturnJMI.addActionListener(new ReturnInfoListener());
		bookBorrowJMI.addActionListener(new BorrowInfoListener());
		readerTypeJMI.addActionListener(new ReaderManagerListener());
		bookTypeJMI.addActionListener(new BookTypeListener());
		fineSetJMI.addActionListener(new FineListener());
		updPwdJMI.addActionListener(new UpdatePWDListener());
		userAddJMI.addActionListener(new AddUserListener());
		userDelJMI.addActionListener(new DeleteUserListener());
		return jMenuBar;

	}

	class ReaderAddListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new ReaderAdd();
		}
	}

	class ReaderManagerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new ReaderManager();
		}
	}

	class DeleteUserListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new DeleteUser();
		}
	}

	class UpdatePWDListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new UpdatePWD();
		}
	}

	class AddUserListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new AddUser();
		}
	}

	class ReaderSelectandUpdateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new ReaderSelectandUpdate();
		}
	}

	class BookAddListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new BookAdd();
		}
	}

	class BookSelectandUpdateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new BookSelectandUpdate();
		}
	}

	class BorrowInfoListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new BorrowInfo();
		}
	}

	class ReturnInfoListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new ReturnInfo();
		}
	}

	class BookTypeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new BookType();
		}
	}

	class FineListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new Fine();
		}
	}

	public static void main(String[] args) {
		new Library();

	}

}