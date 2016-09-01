package com.hcn.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.hcn.db.BookBorrowDao;
import com.hcn.db.BookDao;
import com.hcn.db.ReaderDao;
import com.hcn.model.Book;
import com.hcn.model.BookBorrow;
import com.hcn.model.Reader;

public class ReturnInfo extends JFrame {
	private JPanel selectJP, borrowJP, buttonJP, select_conditionJP,
			select_resultJP;
	private JLabel IDJL, readerNameJL, readercategroyJL, ISBNJL, categroyJL,
			bookNameJL, authorJL, publishJL, publishDateJL, printTimeJL,
			priceJL, dateJL, workerJL, overDateJL, fineJL;
	private JTextField IDJTF, readerNameJTF, readercategroyJTF, ISBNJTF,
			categroyJTF, bookNameJTF, authorJTF, publishJTF, publishDateJTF,
			printTimeJTF, priceJTF, dateJTF, workerJTF, overDateJTF, fineJTF;
	private JScrollPane jscrollPane;
	private JTable tab;
	private JButton borrowJB, closeJB;
	private String[] resch = { "图书编号", "图书姓名", "借书日期" };

	private Object[][] getSSelect(List<BookBorrow> list) {
		Object[][] results = new Object[list.size()][resch.length];
		for (int i = 0; i < list.size(); i++) {
			BookBorrow borrowbook = list.get(i);
			results[i][0] = borrowbook.getISBN();
			results[i][1] = borrowbook.getBookname();
			results[i][2] = borrowbook.getBorroedate();
		}
		return results;
	}

	public ReturnInfo() {
		setBounds(300, 100, 700, 500);
		setTitle("图书归还");
		// 创建主要的三个面板
		selectJP = new JPanel();
		selectJP.setLayout(new BorderLayout());
		buttonJP = new JPanel();
		borrowJP = new JPanel();
		borrowJP.setLayout(new GridLayout(6, 4));
		// 查询条件面板设计
		select_conditionJP = new JPanel();
		// select_conditionJP.setLayout(new FlowLayout());
		select_conditionJP.setLayout(new GridLayout(1, 6));
		// 条件的组建放入条件面板中
		IDJL = new JLabel("读者编号：  ");
		IDJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerNameJL = new JLabel("读者姓名：  ");
		readerNameJL.setHorizontalAlignment(SwingConstants.CENTER);
		readercategroyJL = new JLabel("读者类型：  ");
		readercategroyJL.setHorizontalAlignment(SwingConstants.CENTER);
		IDJTF = new JTextField();
		readerNameJTF = new JTextField();
		readercategroyJTF = new JTextField();
		select_conditionJP.add(IDJL);
		select_conditionJP.add(IDJTF);
		select_conditionJP.add(readerNameJL);
		select_conditionJP.add(readerNameJTF);
		select_conditionJP.add(readercategroyJL);
		select_conditionJP.add(readercategroyJTF);
		selectJP.add(select_conditionJP, BorderLayout.NORTH);
		// 结果面板设计
		select_resultJP = new JPanel();
		jscrollPane = new JScrollPane();
		jscrollPane.setPreferredSize(new Dimension(550, 250));
		Object[][] results = getSSelect(BookBorrowDao
				.selectBorrowBookByReaderId(IDJTF.getText().trim()));
		tab = new JTable(results, resch);
		// tab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jscrollPane.setViewportView(tab);
		select_resultJP.add(jscrollPane);
		selectJP.add(select_resultJP, BorderLayout.CENTER);
		// 图书借阅面板设计
		ISBNJL = new JLabel("ISBN：  ");
		ISBNJL.setHorizontalAlignment(SwingConstants.CENTER);
		categroyJL = new JLabel("类别：  ");
		categroyJL.setHorizontalAlignment(SwingConstants.CENTER);
		bookNameJL = new JLabel("书名：  ");
		bookNameJL.setHorizontalAlignment(SwingConstants.CENTER);
		authorJL = new JLabel("作者：  ");
		authorJL.setHorizontalAlignment(SwingConstants.CENTER);
		publishJL = new JLabel("出版社:  ");
		publishJL.setHorizontalAlignment(SwingConstants.CENTER);
		publishDateJL = new JLabel("出版日期：  ");
		publishDateJL.setHorizontalAlignment(SwingConstants.CENTER);
		printTimeJL = new JLabel("印刷次数:  ");
		printTimeJL.setHorizontalAlignment(SwingConstants.CENTER);
		priceJL = new JLabel("单价：  ");
		priceJL.setHorizontalAlignment(SwingConstants.CENTER);
		dateJL = new JLabel("当期日期：  ");
		dateJL.setHorizontalAlignment(SwingConstants.CENTER);
		overDateJL = new JLabel("超期天数：  ");
		overDateJL.setHorizontalAlignment(SwingConstants.CENTER);
		fineJL = new JLabel("罚金：  ");
		fineJL.setHorizontalAlignment(SwingConstants.CENTER);
		workerJL = new JLabel("操作人员：  ");
		workerJL.setHorizontalAlignment(SwingConstants.CENTER);
		ISBNJTF = new JTextField();
		categroyJTF = new JTextField();
		bookNameJTF = new JTextField();
		authorJTF = new JTextField();
		publishJTF = new JTextField();
		publishDateJTF = new JTextField();
		printTimeJTF = new JTextField();
		priceJTF = new JTextField();
		dateJTF = new JTextField();
		overDateJTF = new JTextField();
		fineJTF = new JTextField();
		workerJTF = new JTextField();

		workerJTF.setEditable(false);
		// 设置时间 获取系统时间
		java.util.Calendar c = java.util.Calendar.getInstance();
		SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String st = format.format(c.getTime());
		dateJTF.setText(st);
		borrowJP.add(ISBNJL);
		borrowJP.add(ISBNJTF);
		borrowJP.add(categroyJL);
		borrowJP.add(categroyJTF);
		borrowJP.add(bookNameJL);
		borrowJP.add(bookNameJTF);
		borrowJP.add(authorJL);
		borrowJP.add(authorJTF);
		borrowJP.add(publishJL);
		borrowJP.add(publishJTF);
		borrowJP.add(publishDateJL);
		borrowJP.add(publishDateJTF);
		borrowJP.add(printTimeJL);
		borrowJP.add(printTimeJTF);
		borrowJP.add(priceJL);
		borrowJP.add(priceJTF);
		borrowJP.add(dateJL);
		borrowJP.add(dateJTF);
		borrowJP.add(overDateJL);
		borrowJP.add(overDateJTF);
		borrowJP.add(fineJL);
		borrowJP.add(fineJTF);
		borrowJP.add(workerJL);
		borrowJP.add(workerJTF);
		// 设计按钮面板
		borrowJB = new JButton("归还");
		closeJB = new JButton("关闭");
		buttonJP.add(borrowJB);
		buttonJP.add(closeJB);
		// 将三大板块 放入 窗体中
		this.add(selectJP, new BorderLayout().NORTH);
		this.add(borrowJP, new BorderLayout().CENTER);
		this.add(buttonJP, new BorderLayout().SOUTH);
		this.setVisible(true);
		setResizable(false);
		// 注册
		borrowJB.addActionListener(new ReturnInfoAction());
		IDJTF.addKeyListener(new SelectReaderAction());
		ISBNJTF.addKeyListener(new SelectByISBN());
		closeJB.addActionListener(new Return_closeListner());
		workerJTF.setText(Login.operator);
	}

	// 关闭界面监听器实现
	public class Return_closeListner implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}

	// 实现学生输入指定学号 回车 的响应事件
	class SelectReaderAction extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			List<Reader> reader_list = ReaderDao.selectReaderById(IDJTF
					.getText().trim());
			// for (int i = 0; i < list.size(); i++) {
			// Book book = list.get(i);
			if (reader_list.size() != 0) {
				for (Reader r : reader_list) {
					readerNameJTF.setText(r.getName());
					readercategroyJTF.setText(r.getTypename());
				}
			} else {
				readerNameJTF.setText("");
				readercategroyJTF.setText("");
			}
			// 更新查询信息
			Object[][] bookdata = getSSelect(BookBorrowDao
					.selectBorrowBookByReaderId(IDJTF.getText().toString()
							.trim()));
			tab = new JTable(bookdata, resch);
			// tab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			// 设置列的宽度
			tab.getColumnModel().getColumn(0).setPreferredWidth(133);
			tab.getColumnModel().getColumn(1).setPreferredWidth(133);
			tab.getColumnModel().getColumn(2).setPreferredWidth(133);
			jscrollPane.setViewportView(tab);
		}
	}

	// 归还图书 ，判断是否成功 实现 归还按钮的功能
	class ReturnInfoAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String returndate = dateJTF.getText().toString().trim();
			Date re = Date.valueOf(returndate);
			String readerid = IDJTF.getText().trim();
			String isbn = ISBNJTF.getText().trim();
			int i = BookBorrowDao.returnBook(readerid, isbn, re);
			if (i != 0) {
				JOptionPane.showMessageDialog(null, "归还成功！");
				Object[][] results = getSSelect(BookBorrowDao
						.selectBorrowBookByReaderId(IDJTF.getText().trim()));
				tab = new JTable(results, resch);
				tab.getTableHeader().setReorderingAllowed(false);

				jscrollPane.setViewportView(tab);
				select_resultJP.add(jscrollPane);
			}
		}
	}

	// 实现输入ISBN号 出现图书各种信息
	class SelectByISBN extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			List<Book> list = BookDao
					.selectBookByISNB(ISBNJTF.getText().trim());
			if (list.size() != 0) {
				for (Book book : list) {
					categroyJTF.setText(book.getTypename());
					bookNameJTF.setText(book.getBookname().trim());
					authorJTF.setText(book.getAuthor().trim());
					publishJTF.setText(book.getPublish().trim());
					publishDateJTF.setText(book.getPublishdate().toString()
							.trim());
					printTimeJTF.setText(String.valueOf(book.getPrinttime()));
					priceJTF.setText(book.getUnitprice().toString().trim());
				}
			} else {
				categroyJTF.setText("");
				bookNameJTF.setText("");
				authorJTF.setText("");
				publishJTF.setText("");
				publishDateJTF.setText("");
				printTimeJTF.setText("");
				priceJTF.setText("");
			}
			int selRow = tab.getSelectedRow();
			java.sql.Date borrowday, returnday;
			borrowday = java.sql.Date.valueOf(tab.getValueAt(selRow, 2)
					.toString().trim());
			returnday = java.sql.Date.valueOf(dateJTF.getText().trim());
			Long m_intervalday = returnday.getTime() - borrowday.getTime();// 计算所得为微秒数
			Long borrowtime = m_intervalday / 1000 / 60 / 60 / 24 - 30;// 计算所得的天数
			List<Reader> list1 = ReaderDao.selectReaderById(IDJTF.getText()
					.trim());
			// for()
			int limit;
			for (int i = 0; i < list1.size(); i++) {
				Reader reader = list1.get(i);
				limit = reader.getLimit();

				if (borrowtime > limit) {
					overDateJTF.setText(String.valueOf(borrowtime));
					Double zfk = Double.valueOf(borrowtime - 30) * Fine.fune;//Login.username
					fineJTF.setText(String.valueOf(zfk));
				} else {
					overDateJTF.setText("没有超过规定天数");
					fineJTF.setText("0");
				}
			}
			// 对表格添加鼠标适配器 以实现 鼠标点击 自动获取图书各种信息查询并显示出来

			/*
			 * class TableListener extends MouseAdapter {
			 * 
			 * public void mouseClicked(final MouseEvent e) {
			 * 
			 * int selRow = table.getSelectedRow();
			 * ISBNJTF.setText(table.getValueAt(selRow, 0).toString().trim());
			 * System.out.println(ISBNJTF.getText()); List<Book>
			 * list=BookDao.selectBookByISBN(ISBNJTF.getText().trim()); for (int
			 * i = 0; i < list.size(); i++) { Book book = list.get(i);
			 * //获取图书类型名称
			 * 
			 * booktypeJTF.setText(book.getTypename());
			 * booknameJTF.setText(book.getBookname());
			 * authorJTF.setText(book.getAuthor());
			 * publishJTF.setText(book.getPublish()); DateFormat format = new
			 * SimpleDateFormat("yyyy-MM-dd"); String
			 * date=format.format(book.getPublishdate());
			 * publishdateJTF.setText(date); String
			 * time=String.valueOf(book.getPrinttime());
			 * printtimeJTF.setText(time); String
			 * unitprice=String.valueOf(book.getUnitprice());
			 * unitpriceJTF.setText(unitprice); } //设置超期天数值 java.sql.Date
			 * borrowday,returnday; borrowday
			 * =java.sql.Date.valueOf(table.getValueAt(selRow,
			 * 2).toString().trim());
			 * returnday=java.sql.Date.valueOf(returndate.getText().trim());
			 * Long m_intervalday = returnday.getTime() -
			 * borrowday.getTime();//计算所得为微秒数 Long borrowtime = m_intervalday /
			 * 1000 / 60 / 60 / 24;//计算所得的天数 System.out.println(borrowtime);
			 * List<Reader>
			 * list1=ReaderDao.selectReaderById(readeridJTF.getText().trim());
			 * //for() int limit; for (int i = 0; i < list1.size(); i++) {
			 * Reader reader = list1.get(i); limit=reader.getLimit();
			 * 
			 * if(borrowtime>limit){
			 * overlimitJTF.setText(String.valueOf(borrowtime)); Double
			 * zfk=Double.valueOf(borrowtime)*FineSet.fine;
			 * fineJTF.setText(String.valueOf(zfk)); } else{
			 * overlimitJTF.setText("没有超过规定天数"); fineJTF.setText("0"); } } }
			 * 
			 * }
			 */
			// =======================================================================================
			// Calendar
			// nowDate=Calendar.getInstance(),oldDate=Calendar.getInstance();
			// nowDate.setTime(new Date());//设置为当前系统时间
			// oldDate.set(1990, 5, 19);//设置为1990年（6）月29日
			// long timeNow=nowDate.getTimeInMillis();
			// long timeOld=oldDate.getTimeInMillis();
			// long 相隔天数=(timeNow-timeOld)/(1000*60*60*24);//化为天
			// System.out.println("相隔"+相隔天数+"天");
			// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			// long to = df.parse("2008-4-25").getTime();
			// long from = df.parse("2008-1-20").getTime();
			// System.out.println((to - from) / (1000 * 60 * 60 * 24))
			// if (list.size() != 0) {
			// int selRow = tab.getSelectedRow();
			// String borrowdate = (tab.getValueAt(selRow, 2).toString()
			// .trim());
			// String nowdate = dateJTF.getText();
			// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			// long borrow = 0;
			// long now = 0;
			// try {
			// borrow = df.parse(borrowdate).getTime();
			// } catch (ParseException e1) {
			//
			// e1.printStackTrace();
			// }
			// try {
			// now = df.parse(nowdate).getTime();
			// } catch (ParseException e1) {
			// e1.printStackTrace();
			// }
			// BookBorrow bookborrow = new BookBorrow();
			// long c = ((now - borrow) / (1000 * 60 * 60 * 24));
			// overDateJTF.setText(String.valueOf(c));
			// // double m =
			// // ((now-borrow)/(1000*60*60*24))*bookborrow.getFune();
			// // fineJTF.setText(String.valueOf(m));
			// System.out.println(bookborrow.getFune());
			// if (((now - borrow) / (1000 * 60 * 60 * 24)) > 30) {
			// double money = (c - 30) * bookborrow.getFune();
			// System.out.println(money);
			// fineJTF.setText(String.valueOf(money));
			// }
			//
			// } else{
			// overDateJTF.setText("");
			// fineJTF.setText("");
			// }
			// ============================================================================================
		}
	}

	public static void main(String[] args) {
		new ReturnInfo();

	}

}
/*
 * java.util.Calendar c=java.util.Calendar.getInstance();
 * java.text.SimpleDateFormat f=new java.text.SimpleDateFormat("yyyy年MM月dd日");
 * System.out.println(f.format(c.getTime()));
 */
