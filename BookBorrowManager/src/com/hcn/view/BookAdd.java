package com.hcn.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.hcn.db.BookDao;
import com.hcn.db.BookTypeDao;

public class BookAdd extends JFrame {
	private JPanel bookAddJP, buttonJP;
	private JButton addJB, resJB, closeJB;
	private JLabel ISBNJL, bookNameJL, categoryJL, authorJL, publishJL,
			printTimesJL, publishDateJL, priceJL;
	private JTextField ISBNJTF, bookNameJTF, publishJTF, printTimesJTF,
			authorJTF, publishDateJTF, priceJTF;
	private JComboBox categoryJCB;

	public BookAdd() {
		setBounds(400, 200, 500, 300);
		setTitle("图书信息添加");

		// 按钮面板设计
		buttonJP = new JPanel();
		addJB = new JButton("添加");
		resJB = new JButton("重置");
		closeJB = new JButton("关闭");
		buttonJP.add(addJB);
		buttonJP.add(resJB);
		buttonJP.add(closeJB);

		// 图书添加面板设计
		bookAddJP = new JPanel();
		bookAddJP.setBorder(new EmptyBorder(5, 10, 5, 10));
		final GridLayout gridLayout = new GridLayout(4, 4);
		gridLayout.setVgap(10);
		gridLayout.setHgap(10);
		bookAddJP.setLayout(gridLayout);
		getContentPane().add(bookAddJP);

		// 创建ISBN标签和ISBN文本框到图书添加面板
		ISBNJL = new JLabel("ISBN:  ");
		ISBNJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(ISBNJL);
		ISBNJTF = new JTextField();
		bookAddJP.add(ISBNJTF);
		// 添加类别标签和下拉框 并添加图书添加面板
		categoryJL = new JLabel("类别:  ");
		categoryJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(categoryJL);
		categoryJCB = new JComboBox();
		List<com.hcn.model.BookType> list = BookTypeDao.selectBookType();
		for (int i = 0; i < list.size(); i++) {
			com.hcn.model.BookType rt = list.get(i);
			categoryJCB.addItem(rt.getTypename());
		}
		bookAddJP.add(categoryJCB);
		// 创建书名标签和文本框并添加到图书添加面板
		bookNameJL = new JLabel("书名：  ");
		bookNameJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(bookNameJL);
		bookNameJTF = new JTextField();
		bookAddJP.add(bookNameJTF);

		// 添加作者标签和文本框 并添加到图书添加面板
		authorJL = new JLabel("作者：  ");
		authorJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(authorJL);
		authorJTF = new JTextField();
		bookAddJP.add(authorJTF);

		// 添加出版社标签和文本框 并添加到图书添加面板
		publishJL = new JLabel("出版社：  ");
		publishJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(publishJL);
		publishJTF = new JTextField();
		bookAddJP.add(publishJTF);

		// 添加出版日期标签和文本框 并添加到 图书添加面板
		publishDateJL = new JLabel("出版日期：  ");
		publishDateJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(publishDateJL);
		publishDateJTF = new JTextField();
		bookAddJP.add(publishDateJTF);

		// 添加印刷次数和文本框 并添加到图书添加面板
		printTimesJL = new JLabel("印刷次数：  ");
		printTimesJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(printTimesJL);
		printTimesJTF = new JTextField();
		bookAddJP.add(printTimesJTF);

		// 添加单价标签和文本框 并添加到图书添加面板中
		priceJL = new JLabel("单价：  ");
		priceJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookAddJP.add(priceJL);
		priceJTF = new JTextField();
		bookAddJP.add(priceJTF);

		this.add(bookAddJP, new BorderLayout().CENTER);
		this.add(buttonJP, new BorderLayout().SOUTH);

		closeJB.addActionListener(new close_bookadd());
		addJB.addActionListener(new AddBtActionListener());
		resJB.addActionListener(new RessetActionListener());
		ISBNJTF.addFocusListener(new ISBNFocusListener());
		this.setVisible(true);
		setResizable(false);
	}

	class close_bookadd implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);

		}
	}

	class AddBtActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (ISBNJTF.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(null, "图书编号不可以为空");
				return;
			}
			if ((ISBNJTF.getText().length() )< 10&& (ISBNJTF.getText().length())>0) {
				JOptionPane.showMessageDialog(null, "图书编号位数不对");
				return;
			}
			if (bookNameJTF.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "图书名不能为空");
				return;
			}
			String isbn = ISBNJTF.getText().trim();
			String type = (String) categoryJCB.getSelectedItem();
			String name = bookNameJTF.getText().trim();
			String author = authorJTF.getText().trim();
			String publish = publishJTF.getText().trim();
			String pubdate = publishDateJTF.getText().trim();
			Integer printtime = Integer
					.parseInt(printTimesJTF.getText().trim());
			Double price = Double.parseDouble(priceJTF.getText().trim());
			int i = BookDao.insertBook(isbn, type, name, author, publish,
					pubdate, printtime, price);
			if (i == 1) {
				JOptionPane.showMessageDialog(null, "添加成功");
				BookAdd.this.setVisible(false);
			}

		}
	}
	class RessetActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			ISBNJTF.setText("");
			bookNameJTF.setText("");
			authorJTF.setText("");
			publishJTF.setText("");
			publishDateJTF.setText("");
			printTimesJTF.setText("");
			priceJTF.setText("");
		}
		
	}
	
	class ISBNFocusListener extends FocusAdapter{
		public void focusLost(FocusEvent e){
			if(!BookDao.selectBookByISNB(ISBNJTF.getText().trim()).isEmpty()){
				JOptionPane.showMessageDialog(null, "添加书号重复！");
			return;
		}
	  }
}
	public static void main(String[] args) {
		new BookAdd();
	}

}
