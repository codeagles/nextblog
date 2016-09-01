package com.hcn.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.hcn.db.ReaderDao;
import com.hcn.db.ReaderTypeDao;

public class ReaderAdd extends JFrame {
	private static final long serialVersionUID = 1L;
	// 定义面板
	private JPanel readerAddJP, sexJP, buttonJP;
	// 定义按钮组与下面的按钮，构成性别单选按钮组
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton JRB1, JRB2;
	// 定义标签
	private JLabel IDJL, categoryJL, readerNameJL, sexJL, phoneJL, ageJL,
			deptJL, regJL;
	// 定义文本框
	private JTextField IDJTF, readerNameJTF, phoneJTF, deptJTF, ageJTF, regJTF;
	// 读者类别文本框
	private JComboBox readertypeJCB;
	private JButton addJB, closeJB;

	// 构造方法
	public ReaderAdd() {
		setBounds(400, 200, 500, 200);
		setTitle("读者信息添加");
		buttonJP = new JPanel();// 登录取消按钮

		// 读者信息添加面板设计
		readerAddJP = new JPanel();
		readerAddJP.setBorder(new EmptyBorder(5, 10, 5, 10));
		final GridLayout gridLayout = new GridLayout(4, 4);
		gridLayout.setVgap(10);
		gridLayout.setHgap(10);
		readerAddJP.setLayout(gridLayout);
		getContentPane().add(readerAddJP);

		// 添加读者标号标签和文本号到读者添加面板
		IDJL = new JLabel("编号：  ");
		IDJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(IDJL);
		IDJTF = new JTextField();
		readerAddJP.add(IDJTF);

		// 创建读者姓名标签和文本框添加到读者添加面板
		readerNameJL = new JLabel("姓名：  ");
		readerNameJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerNameJTF = new JTextField();
		readerAddJP.add(readerNameJL);
		readerAddJP.add(readerNameJTF);

		// 添加读者类别标签到读者添加面板
		categoryJL = new JLabel("类别：  ");
		categoryJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(categoryJL);

		// 读者类型下拉列表
		readertypeJCB = new JComboBox();
		List<com.hcn.model.ReaderType> list = ReaderTypeDao.selectReaderType();
		for (int i = 0; i < list.size(); i++) {
			com.hcn.model.ReaderType rt = list.get(i);
			readertypeJCB.addItem(rt.getTypename());
		}
		readerAddJP.add(readertypeJCB);
		sexJL = new JLabel("性别：  ");
		sexJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(sexJL);
//		String[] array = { "教师", "学生" };
//		for (int i = 0; i < array.length; i++) {
//			readertypeJCB.addItem(array[i]);
//		}

		/*
		 * 创建性别面板，用于存放性别按钮。将“男”和“女”按钮放在ButtonGroup中构成
		 * 单选按钮组，即性别只能为男和女。将两个按钮先放到sexJP中，然后，再将sexJP面 板放到readerAddJP面板中。
		 */
		sexJP = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		sexJP.setLayout(flowLayout);
		readerAddJP.add(sexJP);
		JRB1 = new JRadioButton();
		sexJP.add(JRB1);
		buttonGroup.add(JRB1);
		JRB1.setText("男");
		JRB2 = new JRadioButton();
		sexJP.add(JRB2);
		JRB2.setText("女");
		buttonGroup.add(JRB2);
		// 创建读者年龄标签和文本框 并添加到读者添加到面板
		ageJL = new JLabel("年龄：  ");
		ageJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(ageJL);
		ageJTF = new JTextField();
		readerAddJP.add(ageJTF);
		// 创建读者电话标签和文本框并添加到读者添加面板
		phoneJL = new JLabel("电话：  ");
		phoneJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(phoneJL);
		phoneJTF = new JTextField();
		readerAddJP.add(phoneJTF);
		// 创建读者所在部门标签和文本框并添加到读者添加面板
		deptJL = new JLabel("部门：  ");
		deptJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(deptJL);
		deptJTF = new JTextField();
		readerAddJP.add(deptJTF);
		// 创建读者注册日期标签和文本框并添加到读者添加面板。此处，需要默认显示系统当前时间
		// 获取当前系统时间的方法是new java.util.Date(),在通过SimpleDateFormat来指定时间的显示格式
		regJL = new JLabel("注册日期：  ");
		regJL.setHorizontalAlignment(SwingConstants.CENTER);
		readerAddJP.add(regJL);
		regJTF = new JTextField();
		// 时间格式为“yyy-mm-dd”
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String st = format.format(new java.util.Date());
		regJTF.setText(st);
		readerAddJP.add(regJTF);
		/*
		 * Date dt=new Date();
		   SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd");
		   System.out.println(matter1.format(dt));
		 * regJL=new JLabel("注册日期");
		   regJL.setHorizontalAlignment(SwingConstants.CENTER);
		   regtime=new JFormattedTextField(DateFormat.getDateInstance());
		   regtime.setValue(new Date());
		 * */
		// 按钮面板设计
		addJB = new JButton("添加");
		closeJB = new JButton("关闭");
		buttonJP.add(addJB);
		buttonJP.add(closeJB);
		// 添加“读者添加面板”放在窗体中间
		this.add(readerAddJP, BorderLayout.CENTER);
		// 添加“按钮面板”放在窗体南部
		this.add(buttonJP, BorderLayout.SOUTH);

		this.setVisible(true);
		setResizable(false);
		closeJB.addActionListener(new CloseBtListener());
		addJB.addActionListener(new AddBtListener(JRB1));
	}

	class CloseBtListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	}

	class AddBtListener implements ActionListener {
		private final JRadioButton button1;

		AddBtListener(JRadioButton button1) {
			this.button1 = button1;
		}

		public void actionPerformed(final ActionEvent e) {
			if (IDJTF.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "读者编号不可以为空");
				return;
			}
			if (IDJTF.getText().length() != 10) {
				JOptionPane.showMessageDialog(null, "读者编号位数不对");
				return;
			}
			if (readerNameJTF.getText().length() >= 4) {
				JOptionPane.showMessageDialog(null, "读者姓名不能为空");
				return;
			}
			String ID = IDJTF.getText().trim();
			// 获取选择的读者类型
			String readertype = (String)readertypeJCB.getSelectedItem();
			// String.valueOf(readertypeJCB.getSelectedIndex()) ;
			String name = readerNameJTF.getText().trim();
			String age = ageJTF.getText().trim();
			// 获取读者的性别
			String sex = "女";
			if (button1.isSelected()) {
				sex = "男";
			}
			String phone = phoneJTF.getText().trim();
			String dept = deptJTF.getText().trim();
			String regdate = regJTF.getText().trim();
			int i = ReaderDao.insertReader(ID, readertype, name, age, sex,
					phone, dept, regdate);
			System.out.println("readertype:"+readertype);
			if (i == 1) {
				JOptionPane.showMessageDialog(null, "添加成功");
				ReaderAdd.this.setVisible(false);
			}
		}

	}

	public static void main(String[] args) {
		new ReaderAdd();

	}

}
