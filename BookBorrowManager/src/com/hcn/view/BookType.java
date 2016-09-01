package com.hcn.view;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.List;

import com.hcn.db.BookTypeDao;
import com.hcn.db.ReaderTypeDao;
import com.hcn.model.*;
import com.hcn.view.ReaderManager.JTableListener;

public class BookType extends JFrame {
	private JPanel selectJP, select_conditionJP, select_resultJP, buttonJP,
			manageJP;
	private JLabel bookTypeJL, bookIDJL, bookTypeNameJL;
	private JButton selectJB, addJB, updateJB, deleteJB, exitJB;
	private JTextField bookTypeJTF, bookIDJTF, bookTypeNameJTF;
	private JScrollPane jscrollPane;
	private JTable jtable;
	private String[] booktypereach = { "图书类型编号", "图书类型名称" };

	private Object[][] getSSelect(List<com.hcn.model.BookType> list) {
		Object[][] results = new Object[list.size()][booktypereach.length];
		for (int i = 0; i < list.size(); i++) {
			com.hcn.model.BookType booktype = list.get(i);
			results[i][0] = booktype.getTypeid();
			results[i][1] = booktype.getTypename();
		}
		return results;
	}

	public BookType() {
		setBounds(300, 100, 600, 400);
		setTitle("图书类型管理");
		selectJP = new JPanel();
		selectJP.setLayout(new BorderLayout());
		manageJP = new JPanel();
		manageJP.setLayout(new GridLayout(2, 2));
		buttonJP = new JPanel();
		//
		select_conditionJP = new JPanel();
		bookTypeJL = new JLabel("图书类型：  ");
		bookTypeJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookTypeJTF = new JTextField();
		bookTypeJTF.setColumns(20);
		selectJB = new JButton("查询");
		select_conditionJP.add(bookTypeJL);
		select_conditionJP.add(bookTypeJTF);
		select_conditionJP.add(selectJB);
		selectJP.add(select_conditionJP, BorderLayout.NORTH);
		//
		select_resultJP = new JPanel();
		jscrollPane = new JScrollPane();
		jscrollPane.setPreferredSize(new Dimension(300, 200));
		Object[][] results = getSSelect(BookTypeDao.selectBookType());

		jtable = new JTable(results, booktypereach);
		jscrollPane.setViewportView(jtable);
		jtable.getTableHeader().setReorderingAllowed(false);
		// jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		select_resultJP.add(jscrollPane);
		selectJP.add(select_resultJP);
		//
		bookIDJL = new JLabel("图书类型编号：  ");
		bookIDJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookTypeNameJL = new JLabel("图书类型名称：  ");
		bookTypeNameJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookIDJTF = new JTextField();

		bookTypeNameJTF = new JTextField();
		manageJP.add(bookIDJL);
		manageJP.add(bookIDJTF);
		manageJP.add(bookTypeNameJL);
		manageJP.add(bookTypeNameJTF);
		// 按钮面板设计
		addJB = new JButton("添加");
		updateJB = new JButton("修改");
		deleteJB = new JButton("删除");
		exitJB = new JButton("退出");
		buttonJP.add(addJB);
		buttonJP.add(updateJB);
		buttonJP.add(deleteJB);
		buttonJP.add(exitJB);
		this.add(selectJP, new BorderLayout().NORTH);
		this.add(manageJP, new BorderLayout().CENTER);
		this.add(buttonJP, new BorderLayout().SOUTH);
		this.setVisible(true);
		setResizable(false);
		addJB.addActionListener(new AddActionListener());
		deleteJB.addActionListener(new DeleteActionListener());
		selectJB.addActionListener(new SelectActionListener());
		jtable.addMouseListener(new JTableActionListener());
		exitJB.addActionListener(new BookType_exitListener());
		updateJB.addActionListener(new UpdateActionListener());
	}

	public class BookType_exitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}

	class JTableActionListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			int selRow = jtable.getSelectedRow();
			bookIDJTF.setText(jtable.getValueAt(selRow, 0).toString().trim());
			bookTypeNameJTF.setText(jtable.getValueAt(selRow, 1).toString()
					.trim());
		}
	}
	class AddActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Integer bookid = Integer.parseInt(bookIDJTF.getText().trim());
			String booktypename = bookTypeNameJTF.getText().trim();
			int i = BookTypeDao.insertBookType(bookid, booktypename);
			if(i==1){
				JOptionPane.showMessageDialog(null, "添加成功");
				Object[][] results = getSSelect(BookTypeDao
						.selectBookType());
				jtable = new JTable(results, booktypereach);
				jscrollPane.setViewportView(jtable);
				jtable.getTableHeader().setReorderingAllowed(false);
				// jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtable.addMouseListener(new JTableActionListener());
			}
		}
		
	}
	class UpdateActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Integer bookid = Integer.parseInt(bookIDJTF.getText().trim());
			String booktypename = bookTypeNameJTF.getText().trim();
			int i = BookTypeDao.updateBookType(bookid, booktypename);
			if(i==1){
				JOptionPane.showMessageDialog(null, "修改成功");
				Object[][] results = getSSelect(BookTypeDao
						.selectBookType());
				jtable = new JTable(results, booktypereach);
				jscrollPane.setViewportView(jtable);
				jtable.getTableHeader().setReorderingAllowed(false);
				// jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtable.addMouseListener(new JTableActionListener());
			}
			
		}
		
	}
	class DeleteActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Integer id = Integer.parseInt(bookIDJTF.getText().trim());
			int i = BookTypeDao.deleteBookType(id);
			if (i == 1) {
				JOptionPane.showMessageDialog(null, "删除成功");
				Object[][] results = getSSelect(BookTypeDao
						.selectBookType());
				jtable = new JTable(results, booktypereach);
				jscrollPane.setViewportView(jtable);
				jtable.getTableHeader().setReorderingAllowed(false);
				jtable.addMouseListener(new JTableActionListener());

			}
		}
		
	}

	class SelectActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object[][] results = getSSelect(BookTypeDao
					.selectBookTypeByname(bookTypeJTF.getText().trim()));
			jtable = new JTable(results, booktypereach);
			jscrollPane.setViewportView(jtable);
			jtable.getTableHeader().setReorderingAllowed(false);
			// jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			jtable.addMouseListener(new JTableActionListener());
		}

	}

	public static void main(String[] args) {
		new BookType();

	}

}
