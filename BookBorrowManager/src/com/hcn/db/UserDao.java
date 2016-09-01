package com.hcn.db;
import java.util.*;
import com.hcn.model.*;
import java.sql.*;

public class UserDao {
	//用户校验
	public static Users check(String name, String password){
			Users users=new Users();
			String sql="select * from users where name='"+name+"' and password='"+password+"'";
			ResultSet rs=Dao.executeQuery(sql);
			try {
				while(rs.next()){
					users.setId(rs.getInt("id"));
					users.setName(rs.getString("name"));
					users.setPassword(rs.getString("password"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Dao.close();
			return users;
		
		
	}
	//查询所有用户
	public static List<Users>selectUser(){
		List<Users> list = new ArrayList<Users>();
		String sql ="select * from users";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while(rs.next()){
				Users users = new Users();
				users.setId(rs.getInt("id"));
				users.setName(rs.getString("name"));
				users.setPassword(rs.getString("password"));
				list.add(users);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//添加用户
	public static int insertUser(String name,String pwd){
		int i = 0;
		try {
			String sql ="insert into users(name,password)values('"+name+"','"+pwd+"')";
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
		
	}
	//判断原始密码是否正确
	public static String SelectOldPwd(String name){
		String sql="select password from users where name='"+name+"'";
		ResultSet rs=Dao.executeQuery(sql);
		String oldpwd="";
		try {
			while(rs.next()){
				oldpwd=rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return oldpwd;
	}
	//修改密码
	public static int ChangePwd(String password,String name){
		String sql="update users set password='"+password+"' where name='"+name+"'";
		int i=Dao.executeUpdate(sql);
		return i;
	}
	//删除用户信息
	public static int deleteUser(Integer id){
		int i = 0;
		try {
			String sql = "delete from users where id = "+id+"";
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
}
