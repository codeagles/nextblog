package com.hcn.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.JFrame;

import com.hcn.db.ReaderTypeDao;
import com.hcn.model.*;
import com.hcn.db.*;

public class ReaderSelectandUpdate extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel selectJP, select_conditionJP, select_resultJP, sexJP,
			updateJP, buttonJP;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton JRB1, JRB2;
	private JLabel IDJL, typeJL, readerNameJL, sexJL, phoneJL, deptJL, regJL,
			ageJL;
	private JTextField select_conditionJTF, IDJTF, ageJTF, readerNameJTF,
			deptJTF, regJTF, phoneJTF;
	private JComboBox conditionJCB, readertypeJCB;
	private JScrollPane jscrollPane;
	// 定义表格，用于存放查询出来的读者信息
	private JTable jtable;
	private JButton selectJB, updateJB, closeJB;
	// 存放读者信息表格的表头数据
	private String[] readersearch = { "编号", "类型", "姓名", "年龄", "性别", "电话", "系部",
			"注册日期" };

	private Object[][] getSSelect(List<Reader>list){
		Object[][] results = new Object[list.size()][readersearch.length];
		for(int i= 0;i<list.size();i++){
			Reader reader = list.get(i);
			results[i][0] = reader.getReaderid();
			results[i][1] = reader.getTypename();
			results[i][2] = reader.getName();
			results[i][3] = reader.getAge();			
			results[i][4] = reader.getSex();	
			results[i][5] = reader.getPhone();
			results[i][6] = reader.getDept();
			results[i][7] = reader.getRegdate();
		}
		return results;
		
	}

	public ReaderSelectandUpdate() {
		setBounds(400, 100, 500, 500);
		setTitle("读者信息查询与修改");
		// 读者信息查询面板设计
		selectJP = new JPanel();
		selectJP.setLayout(new BorderLayout());
		// 查询条件面板
		// 查询条件下拉列表框
		select_conditionJP = new JPanel();
		conditionJCB = new JComboBox();
		String[] array = { "读者编号", "姓名", "类型", "系部" };
		for (int i = 0; i < array.length; i++) {
			conditionJCB.addItem(array[i]);
		}
		select_conditionJP.add(conditionJCB);
		// 查询条件文本框
		select_conditionJTF = new JTextField();
		select_conditionJTF.setColumns(20);
		select_conditionJP.add(select_conditionJTF);
		// 查询按钮
		selectJB = new JButton("查询");
		// selectJB.addActionListener(new SelectAction());
		// selectJB.setText("查询");
		select_conditionJP.add(selectJB);
		selectJP.add(select_conditionJP, BorderLayout.NORTH);
		// 查询结果面板
		select_resultJP = new JPanel();
		jscrollPane = new JScrollPane();
		jscrollPane.setPreferredSize(new Dimension(400, 200));
		Object[][] results = getSSelect(ReaderDao.selectReader());
		jtable = new JTable(results,readersearch);
		jtable.getTableHeader().setReorderingAllowed(false);
		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jscrollPane.setViewportView(jtable);////将参数jtable作为JScorrllPane的视图进行显示。
		select_resultJP.add(jscrollPane);
		selectJP.add(select_resultJP, BorderLayout.CENTER);
		// 读者信息修改面板
		updateJP = new JPanel();
		updateJP.setBorder(new EmptyBorder(5, 10, 5, 10));
		GridLayout gridLayout = new GridLayout(4, 4);
		gridLayout.setVgap(10);
		gridLayout.setHgap(10);
		updateJP.setLayout(gridLayout);
		// 创建标号标签和文本框并添加到updateJP中
		IDJL = new JLabel("编号：  ");
		IDJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(IDJL);
		IDJTF = new JTextField();
		updateJP.add(IDJTF);
		// 创建姓名标签和文本框并添加到updateJP中
		readerNameJL = new JLabel("姓名：  ");
		readerNameJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(readerNameJL);
		readerNameJTF = new JTextField();
		updateJP.add(readerNameJTF);
		// 创建类别标签和下拉列表框并添加到updateJP中
		typeJL = new JLabel("类别：  ");
		typeJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(typeJL);
		// 读者类别下拉列表
		readertypeJCB = new JComboBox();
		List<ReaderType>list = ReaderTypeDao.selectReaderType();
		for(int i=0;i<list.size();i++)
		{
			ReaderType rt = list.get(i);
			readertypeJCB.addItem(rt.getTypename());
			
		}
		updateJP.add(readertypeJCB);
		// 创建性别标签和按钮组面板
		sexJL = new JLabel("性别： ");
		sexJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(sexJL);
		sexJP = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		sexJP.setLayout(flowLayout);
		updateJP.add(sexJP);
		JRB1 = new JRadioButton();
		JRB1.setText("男");
		sexJP.add(JRB1);
		JRB1.setSelected(true);
		buttonGroup.add(JRB1);
		JRB2 = new JRadioButton();
		JRB2.setText("女");
		sexJP.add(JRB2);
		buttonGroup.add(JRB2);
	
		// 创建年龄标签和文本框并添加到updateJP中
		ageJL = new JLabel("年龄：  ");
		ageJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(ageJL);
		ageJTF = new JTextField();
		updateJP.add(ageJTF);
		// 创建电话标签和文本框并添加到updateJP中
		phoneJL = new JLabel("电话：  ");
		phoneJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(phoneJL);
		phoneJTF = new JTextField();
		updateJP.add(phoneJTF);
		// 创建所在部门标签和文本框并添加到updateJP中
		deptJL = new JLabel("所在部门：  ");
		deptJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(deptJL);
		deptJTF = new JTextField();
		updateJP.add(deptJTF);
		// 创建注册日期标签和文本框并添加到updateJP中
		regJL = new JLabel("注册日期：  ");
		regJL.setHorizontalAlignment(SwingConstants.CENTER);
		updateJP.add(regJL);
		regJTF = new JTextField();
		updateJP.add(regJTF);
		// 按钮面板设计
		buttonJP = new JPanel();
		updateJB = new JButton("修改");
		closeJB = new JButton("关闭");
		buttonJP.add(updateJB);
		buttonJP.add(closeJB);
		// 添加面板到创体中
		// 添加读者信息查询面板到窗体的北部
		this.add(buttonJP, BorderLayout.SOUTH);
		this.add(updateJP, BorderLayout.CENTER);
		this.add(selectJP, BorderLayout.NORTH);
		this.setVisible(true);
		setResizable(true);
		closeJB.addActionListener(new CloseActionListener());
		selectJB.addActionListener(new SelectActionListener());
		updateJB.addActionListener(new ReaderUpdActionListener() );
	}
	class JTableListener extends MouseAdapter{
		public void mouseClicked(final MouseEvent e){
			int selRow = jtable.getSelectedRow();
			IDJTF.setText(jtable.getValueAt(selRow, 0).toString().trim());
////////////////////////////////////////////////////////////////////////////////
			readertypeJCB.setSelectedItem(jtable.getValueAt(selRow, 1).toString().trim());
			
			readerNameJTF.setText(jtable.getValueAt(selRow, 2).toString().trim());
			ageJTF.setText(jtable.getValueAt(selRow, 3).toString().trim());
			if(jtable.getValueAt(selRow, 4).toString().trim().equals("男")){
				JRB1.setSelected(true);
			}else{
				JRB2.setSelected(true);
			}
			phoneJTF.setText(jtable.getValueAt(selRow, 5).toString().trim());
			deptJTF.setText(jtable.getValueAt(selRow, 6).toString().trim());
			regJTF.setText(jtable.getValueAt(selRow, 7).toString().trim());
		}
	}
	
	//按条件查询读者
	class SelectActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String condition = (String)conditionJCB.getSelectedItem();
			if(condition.equals("读者编号")){
				Object[][] results =getSSelect(ReaderDao.selectReaderById(select_conditionJTF.getText().trim()));
				jtable = new JTable(results,readersearch);
				jscrollPane.setViewportView(jtable);
				jtable.getTableHeader().setReorderingAllowed(false);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtable.addMouseListener(new JTableListener());
			}else if(condition.equals("姓名")){
				Object[][] results = getSSelect(ReaderDao.selectReaderByName(select_conditionJTF.getText().trim()));
				jtable = new JTable(results,readersearch);
				jscrollPane.setViewportView(jtable);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtable.addMouseListener(new JTableListener());
				
			}else if(condition.equals("类型")){
				Object[][] results = getSSelect(ReaderDao.selectReaderByType(select_conditionJTF.getText().trim()));
				jtable = new JTable(results,readersearch);
				jscrollPane.setViewportView(jtable);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtable.addMouseListener(new JTableListener());
			}else if(condition.equals("系部")){
				Object[][] results = getSSelect(ReaderDao.selectReaderByDept(select_conditionJTF.getText().trim()));
				jtable = new JTable(results,readersearch);
				jscrollPane.setViewportView(jtable);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtable.addMouseListener(new JTableListener());
			}
			
			
		}
		
	}

	class ReaderUpdActionListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			String id = IDJTF.getText().trim();
			String type = (String)readertypeJCB.getSelectedItem();
			String name = readerNameJTF.getText().trim();
			Integer age = Integer.parseInt(ageJTF.getText().trim());
			String sex = "男";
			if(JRB1.isShowing()){
				sex="女";
			}
			String phone = phoneJTF.getText().trim();
			String dept = deptJTF.getText().trim();
			String regdate = regJTF.getText().trim();
			int i = ReaderDao.UpdateReader(id, type,name, age, sex,  phone, dept, regdate);
			if(i==1){
				JOptionPane.showMessageDialog(null, "修改成功");
				Object[][] resutls = getSSelect(ReaderDao.selectReader());
				jtable=new JTable(resutls,readersearch);
				jtable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				jtable.addMouseListener(new JTableListener());
				jscrollPane.setViewportView(jtable);
			}
			
		}
	}
	//关闭按钮
	class CloseActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			setVisible(false);
		}
		
	}
	public static void main(String[] args) {
		new ReaderSelectandUpdate();

	}

}
