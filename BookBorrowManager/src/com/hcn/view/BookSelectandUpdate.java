package com.hcn.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.hcn.db.BookDao;
import com.hcn.db.BookTypeDao;
import com.hcn.model.Book;

public class BookSelectandUpdate extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel selectJP, updateJP, buttonJP, select_conditionJP,
			select_resultJP;
	private JTabbedPane jtabbedPane;
	private JButton selectJB, exitJB;
	private JComboBox select_conditionJBC;
	private JScrollPane jscrollPane;
	private JTable jtable;
	private JTextField conditionJTF;

	private JLabel ISBNJL, categoryJL, bookNameJL, authorJL, publishJL,
			publishDateJL, printTimesJL, priceJL;
	private JPanel bookJP, button1JP;
	private JTextField ISBNJTF, bookNameJTF, authorJTF, publishJTF,
			publishDateJTF, printTimesJTF, priceJTF;
	private JComboBox categoryJCB;
	private JButton updateJB, closeJB;
	private String[] booksearch = { "ISBN", "图书类型", "图书名称", "作者", "出版社",
			"出版日期", "出版次数", "单价" };

	private Object[][] getSSelect(List<Book> list) {
		Object[][] results = new Object[list.size()][booksearch.length];
		for (int i = 0; i < list.size(); i++) {
			Book book = list.get(i);
			results[i][0] = book.getISBN();
			results[i][1] = book.getTypename();
			results[i][2] = book.getBookname();
			results[i][3] = book.getAuthor();
			results[i][4] = book.getPublish();
			results[i][5] = book.getPublishdate();
			results[i][6] = book.getPrinttime();
			results[i][7] = book.getUnitprice();
		}
		return results;

	}

	public BookSelectandUpdate() {
		setBounds(300, 100, 700, 500);
		setTitle("图书信息查询与修改");
		// 创建图书查询面板
		selectJP = new JPanel();
		updateJP = new JPanel();
		updateJP.setLayout(new BorderLayout());
		selectJP.setLayout(new BorderLayout());
		// 设计按钮面板 并添加到图书查询面板中
		buttonJP = new JPanel();
		selectJB = new JButton("查询");
		selectJB.setHorizontalAlignment(SwingConstants.CENTER);
		buttonJP.add(selectJB);
		exitJB = new JButton("退出");
		exitJB.setHorizontalAlignment(SwingConstants.CENTER);
		buttonJP.add(exitJB);
		selectJP.add(buttonJP, BorderLayout.SOUTH);
		// 创建图书查询条件面板并添加其相关组件 并添加到图书查询面板中
		select_conditionJP = new JPanel();
		select_conditionJP.setLayout(new FlowLayout());
		select_conditionJBC = new JComboBox();
		String array[] = { "ISBN", "图书名称", "图书类别", "作者", "出版社" };
		for (int i = 0; i < array.length; i++) {
			select_conditionJBC.addItem(array[i]);
		}

		select_conditionJP.add(select_conditionJBC);
		conditionJTF = new JTextField();
		select_conditionJP.add(conditionJTF);
		conditionJTF.setColumns(20);
		selectJP.add(select_conditionJP, BorderLayout.NORTH);
		// 创建结果返回面板 并添加 表格、滚动条等组件，然后添加到图书信息查询中
		select_resultJP = new JPanel();
		jscrollPane = new JScrollPane();
		jscrollPane.setPreferredSize(new Dimension(525,300));
		Object[][] results = getSSelect(BookDao.selectBook());
		jtable = new JTable(results, booksearch);
		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jscrollPane.setViewportView(jtable);
		select_resultJP.add(jscrollPane);
		selectJP.add(select_resultJP, BorderLayout.CENTER);

		// 创建选项卡
		jtabbedPane = new JTabbedPane();
		jtabbedPane.addTab("图书信息查询", selectJP);
		jtabbedPane.addTab("图书信息修改", updateJP);
		/*
		 * 创建选项卡修改部分
		 */

		// 设计按钮面板
		button1JP = new JPanel();
		updateJB = new JButton("修改");
		closeJB = new JButton("关闭");
		button1JP.add(updateJB);
		button1JP.add(closeJB);
		updateJP.add(button1JP, BorderLayout.SOUTH);
		// 图书信息面板设计
		bookJP = new JPanel();
		bookJP.setLayout(new GridLayout(8, 2));
		ISBNJL = new JLabel("ISBN：  ");
		ISBNJL.setHorizontalAlignment(SwingConstants.CENTER);
		ISBNJTF = new JTextField();
		bookJP.add(ISBNJL);
		bookJP.add(ISBNJTF);
		//
		categoryJL = new JLabel("类别");
		categoryJL.setHorizontalAlignment(SwingConstants.CENTER);

		bookJP.add(categoryJL);

		// 图书 类别下拉列表
		categoryJCB = new JComboBox();
		// //////////
		List<com.hcn.model.BookType> list = BookTypeDao.selectBookType();
		for (int i = 0; i < list.size(); i++) {
			com.hcn.model.BookType rt = list.get(i);
			categoryJCB.addItem(rt.getTypename());
		}
		bookJP.add(categoryJCB);
		//
		bookNameJL = new JLabel("书名：  ");
		bookNameJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookNameJTF = new JTextField();
		bookJP.add(bookNameJL);
		bookJP.add(bookNameJTF);
		//
		authorJL = new JLabel("作者：  ");
		authorJL.setHorizontalAlignment(SwingConstants.CENTER);
		authorJTF = new JTextField();
		bookJP.add(authorJL);
		bookJP.add(authorJTF);
		//
		publishJL = new JLabel("出版社：  ");
		publishJL.setHorizontalAlignment(SwingConstants.CENTER);
		publishJTF = new JTextField();
		bookJP.add(publishJL);
		bookJP.add(publishJTF);
		//
		publishDateJL = new JLabel("出版日期：  ");
		publishDateJL.setHorizontalAlignment(SwingConstants.CENTER);
		publishDateJTF = new JTextField();
		bookJP.add(publishDateJL);
		bookJP.add(publishDateJTF);
		//
		printTimesJL = new JLabel("印刷次数：  ");
		printTimesJL.setHorizontalAlignment(SwingConstants.CENTER);
		printTimesJTF = new JTextField();
		bookJP.add(printTimesJL);
		bookJP.add(printTimesJTF);
		//
		priceJL = new JLabel("单价：  ");
		priceJL.setHorizontalAlignment(SwingConstants.CENTER);
		priceJTF = new JTextField();
		bookJP.add(priceJL);
		bookJP.add(priceJTF);
		updateJP.add(bookJP);
		this.add(jtabbedPane);
		this.setVisible(true);
		exitJB.addActionListener(new select_exitListener());
		closeJB.addActionListener(new update_closeListener());
		jtable.addMouseListener(new JTableListener());
		updateJB.addActionListener(new BookUpdAction());
		selectJB.addActionListener(new SelectActionListener() );
	}

	public class select_exitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}

	public class update_closeListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}

	class SelectActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String condition = (String) select_conditionJBC.getSelectedItem();
			if (condition.equals("ISBN")) {
				Object[][] results = getSSelect(BookDao
						.selectBookByISNB(conditionJTF.getText().trim()));
				
				jtable = new JTable(results, booksearch);
				
				
				
				jscrollPane.setViewportView(jtable);
				jtable.getTableHeader().setReorderingAllowed(false);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtable.addMouseListener(new JTableListener());
				select_resultJP.add(jscrollPane);
				
			} else if (condition.equals("图书名称")) {
				Object[][] results = getSSelect(BookDao
						.selectBookByBookName(conditionJTF.getText().trim()));
				jtable = new JTable(results, booksearch);
				jtable.getTableHeader().setReorderingAllowed(false);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jscrollPane.setViewportView(jtable);
				jtable.addMouseListener(new JTableListener());
				select_resultJP.add(jscrollPane);
			} else if (condition.equals("图书类别")) {
				Object[][] results = getSSelect(BookDao
						.selectBookByType(conditionJTF.getText().trim()));
				jtable = new JTable(results, booksearch);		
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtable.getTableHeader().setReorderingAllowed(false);
				jscrollPane.setViewportView(jtable);
				jtable.addMouseListener(new JTableListener());
				select_resultJP.add(jscrollPane);
			} else if (condition.equals("出版社")) {
				Object[][] results = getSSelect(BookDao
						.selectBookByPublish(conditionJTF.getText().trim()));
				jtable = new JTable(results, booksearch);
				jtable.getTableHeader().setReorderingAllowed(false);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jscrollPane.setViewportView(jtable);
				jtable.addMouseListener(new JTableListener());
				select_resultJP.add(jscrollPane);
			} else if (condition.equals("作者")) {
				Object[][] results = getSSelect(BookDao
						.selectBookByAuthor(conditionJTF.getText().trim()));
				jtable = new JTable(results, booksearch);
				jtable.getTableHeader().setReorderingAllowed(false);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jscrollPane.setViewportView(jtable);
				jtable.addMouseListener(new JTableListener());
				select_resultJP.add(jscrollPane);
			}
			

		}

	}


	//
	class JTableListener extends MouseAdapter {
		public void mouseClicked(final MouseEvent e) {
			int selRow = jtable.getSelectedRow();
			ISBNJTF.setText(jtable.getValueAt(selRow, 0).toString().trim());
			categoryJCB.setSelectedItem(jtable.getValueAt(selRow, 1).toString()
					.trim());
			bookNameJTF.setText(jtable.getValueAt(selRow, 2).toString().trim());
			authorJTF.setText(jtable.getValueAt(selRow, 3).toString().trim());

			publishJTF.setText(jtable.getValueAt(selRow, 4).toString().trim());
			publishDateJTF.setText(jtable.getValueAt(selRow, 5).toString()
					.trim());
			printTimesJTF.setText(jtable.getValueAt(selRow, 6).toString()
					.trim());
			priceJTF.setText(jtable.getValueAt(selRow, 7).toString().trim());
		}
	}

	class BookUpdAction implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String ISBN = ISBNJTF.getText().trim();
			String type = (String) categoryJCB.getSelectedItem();
			String bookname = bookNameJTF.getText().trim();
			String author = authorJTF.getText().trim();
			String publish = publishJTF.getText().trim();
			String pubdate = publishDateJTF.getText().trim();
			Integer printtime = Integer
					.parseInt(printTimesJTF.getText().trim());
			Double price = Double.parseDouble(priceJTF.getText().trim());
			int i = BookDao.UpdateBook(ISBN, type, bookname, author, publish,
					pubdate, printtime, price);
			if (i == 1) {
				JOptionPane.showMessageDialog(null, "修改成功");
				Object[][] results = getSSelect(BookDao.selectBook());
				jtable = new JTable(results, booksearch);
				jtable.getTableHeader().setReorderingAllowed(false);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jscrollPane.setViewportView(jtable);
				select_resultJP.add(jscrollPane);

			}
		}

	}

	public static void main(String[] args) {

		new BookSelectandUpdate();
	}

}
