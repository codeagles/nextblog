
package com.hcn.view;

import com.hcn.db.UserDao;
import com.hcn.model.*;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*; 
import java.awt.event.*;

import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import javax.swing.JButton;


public class Login extends JFrame {
	private static Users user;
	Font f1 = new Font("黑体",Font.BOLD,32);
	JLabel usernameJLabel,passwordJLabel;
	JTextField usernameJTF;
	JPasswordField pwdJPF;
	public static String operator;
	public Login() {
		setSize(260,180);//界面在中间显示的大小
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setLocation(width/2-130,height/2-90);
		setTitle("图书借阅系统登录界面");
		JPanel textJPanel = new JPanel();//提示信息面板
		JPanel loginJPanel = new JPanel();//登录信息面板
		JPanel buttonJPanel = new JPanel();//登录取消按钮面板
		//提示信息面板
		JLabel j11 = new JLabel();
		j11.setFont(f1);
		j11.setText("图书借阅系统");
		textJPanel.add(j11);
		//登录信息面板设计
		loginJPanel.setLayout(new GridLayout(2,2));
		usernameJLabel = new JLabel("用户名：   ");
		//设置文本在整个标签的中间位置显示
		usernameJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameJTF = new JTextField();
		passwordJLabel = new JLabel("密	码：   ");
		passwordJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pwdJPF = new JPasswordField();
		loginJPanel.add(usernameJLabel);
		loginJPanel.add(usernameJTF);
		loginJPanel.add(passwordJLabel);	
		loginJPanel.add(pwdJPF);
		//登录取消按钮面板设计
		JButton confirmButton = new JButton("登录");
		JButton resetButton = new JButton("重置");
		buttonJPanel.add(confirmButton);
		buttonJPanel.add(resetButton);
		//添加3个面板到窗体中，位置分别为BorderLayout布局的北、中、南。
		this.add(textJPanel,BorderLayout.NORTH);
		this.add(loginJPanel,BorderLayout.CENTER);
		this.add(buttonJPanel,BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口时，退出程序	
		confirmButton.addActionListener(new LoginAction());
		resetButton.addActionListener(new ResetAction());
		this.setVisible(true);
		this.setResizable(false);
		
	}
	//用于保存用户登录的用户信息。
	public static void setUser(Users user) {
		Login.user = user;
	}
	public static Users getUser() {
		return user;
	}
	//点击“登录”按钮时间监听器类
	class LoginAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(!"".equals(usernameJTF.getText())&&!"".equals(new String(pwdJPF.getPassword()))){
				user = UserDao.check(usernameJTF.getText(),new String (pwdJPF.getPassword()));
				if(user.getName()!= null){
					operator = usernameJTF.getText();
					try {
						
						Library frame = new Library();
						frame.setVisible(true);
						Login.this.setVisible(false);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(null,"您输入的用户名或密码错误，不能登录");
					usernameJTF.setText("");
					pwdJPF.setText("");
				}
					
			}else{
				JOptionPane.showMessageDialog(null, "请输入用户名和密码");
			}
		}
		
	}
	class ResetAction implements ActionListener{
		public void actionPerformed(final ActionEvent e) {
				usernameJTF.setText("");
				pwdJPF.setText("");
		}
		
	}
	public static void main(String[] args){
		 new Login();
		
	}

}
