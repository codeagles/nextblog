package com.hcn.db;
import java.sql.*;
import java.util.*;
import com.hcn.model.*;

public class BookTypeDao {
	//查询所有图书类型
	public static List<BookType> selectBookType (){
		List<BookType> list = new ArrayList<BookType>();
		String sql = "select * from booktype";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
			BookType booktype = new BookType();
			booktype.setTypeid(rs.getInt("id"));
			booktype.setTypename(rs.getString("typename"));
			list.add(booktype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//按照图书类型名查询图书类型信息
	public static List<BookType>selectBookTypeByname(String typename){
		List<BookType>list = new ArrayList<BookType>();
		String sql ="select * from booktype where typename like '%"+typename+"%'";
		
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while(rs.next()){
			BookType booktype = new BookType();		
			booktype.setTypeid(rs.getInt("id"));
			booktype.setTypename(rs.getString("typename"));
			
			list.add(booktype);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//添加图书类型信息
	public static int insertBookType(Integer id,String typename){
		int i =0;
		try {
			String sql = "insert into booktype values("+id+",'"+typename+"')";
			i= Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	//修改图书类型信息
	public static int updateBookType(Integer id,String typename){
		int i = 0;
		try {
			String sql = "update booktype set typename ='"+typename+"' where id ="+id+"";
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	//
	//删除图书类别
	public static int deleteBookType(Integer id){
		int i =0;
		try {
			String sql ="delete from booktype where id="+id+"";
			
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
}
