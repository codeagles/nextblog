package com.hcn.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
public static void main(String[] args) {
	//插入
//	String sql="insert into booktype values ('10','计算机')";
//	int i=Dao.executeUpdate(sql);
//	if(i!=0){
//		System.out.println("success");
//	}else {
//		System.out.println("fail");
//	}
	//查询
	String sql="select * from booktype";
	ResultSet rs=Dao.executeQuery(sql);
	try {
		while(rs.next()){
			System.out.println("id为："+rs.getInt(1)+"  图书类型："+rs.getString(2));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
}
