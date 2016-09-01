package com.hcn.db;

import java.sql.*;
import java.util.*;
import com.hcn.model.*;

public class BookDao {
	// 查询所有图书
	public static List<Book> selectBook() {
		List<Book> list = new ArrayList<Book>();
		String sql = "select ISBN,typeid,bookname,author,publish,publishdate,printtime,unitprice,typename from book join booktype on book.typeid = booktype.id";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Book book = new Book();
				book.setISBN(rs.getString("ISBN"));
				book.setTypeid(rs.getInt("typeid"));
				book.setBookname(rs.getString("bookname"));
				book.setAuthor(rs.getString("author"));
				book.setPublish(rs.getString("publish"));
				book.setPublishdate(rs.getDate("publishdate"));
				book.setPrinttime(rs.getInt("printtime"));
				book.setUnitprice(rs.getDouble("unitprice"));
				book.setTypename(rs.getString("typename"));
				list.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}

	// 查询指定编号的图书信息
	public static List<Book> selectBookByISNB(String ISBN) {
		List<Book> list = new ArrayList<Book>();
		String sql = "select ISBN,typeid,bookname,author,publish,publishdate,printtime,unitprice,typename from book join booktype on book.typeid = booktype.id and ISBN = '"
				+ ISBN + "'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Book book = new Book();
				book.setISBN(rs.getString("ISBN"));
				book.setTypeid(rs.getInt("typeid"));
				book.setBookname(rs.getString("bookname"));
				book.setAuthor(rs.getString("author"));
				book.setPublish(rs.getString("publish"));
				book.setPublishdate(rs.getDate("publishdate"));
				book.setPrinttime(rs.getInt("printtime"));
				book.setUnitprice(rs.getDouble("unitprice"));
				book.setTypename(rs.getString("typename"));
				list.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}

	// 查询指定名称的图书信息
	public static List<Book> selectBookByBookName(String bookname) {
		List<Book> list = new ArrayList<Book>();
		String sql = "select ISBN,typeid,bookname,author,publish,publishdate,printtime,unitprice,typename from book join booktype on book.typeid = booktype.id and bookname like '%"
			+ bookname + "%'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Book book = new Book();
				book.setISBN(rs.getString("ISBN"));
				book.setTypeid(rs.getInt("typeid"));
				book.setBookname(rs.getString("bookname"));
				book.setAuthor(rs.getString("author"));
				book.setPublish(rs.getString("publish"));
				book.setPublishdate(rs.getDate("publishdate"));
				book.setPrinttime(rs.getInt("printtime"));
				book.setUnitprice(rs.getDouble("unitprice"));
				book.setTypename(rs.getString("typename"));
				list.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}

	// 查询指定作者的图书信息
	public static List<Book> selectBookByAuthor(String author) {
		List<Book> list = new ArrayList<Book>();
		String sql = "select ISBN,typeid,bookname,author,publish,publishdate,printtime,unitprice,typename from book join booktype on book.typeid = booktype.id and author like '%"
				+ author + "%'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Book book = new Book();
				book.setISBN(rs.getString("ISBN"));
				book.setTypeid(rs.getInt("typeid"));
				book.setBookname(rs.getString("bookname"));
				book.setAuthor(rs.getString("author"));
				book.setPublish(rs.getString("publish"));
				book.setPublishdate(rs.getDate("publishdate"));
				book.setPrinttime(rs.getInt("printtime"));
				book.setUnitprice(rs.getDouble("unitprice"));
				book.setTypename(rs.getString("typename"));
				list.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;

	}

	// 查询指定类别的图书信息
	public static List<Book> selectBookByType(String type) {
		List<Book> list = new ArrayList<Book>();
		String sql = "select ISBN,typeid,bookname,author,publish,publishdate,printtime,unitprice,typename from book join booktype on book.typeid = booktype.id and typename like'%"+type+"%'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Book book = new Book();
				book.setISBN(rs.getString("ISBN"));
				book.setTypeid(rs.getInt("typeid"));
				book.setBookname(rs.getString("bookname"));
				book.setAuthor(rs.getString("author"));
				book.setPublish(rs.getString("publish"));
				book.setPublishdate(rs.getDate("publishdate"));
				book.setPrinttime(rs.getInt("printtime"));
				book.setUnitprice(rs.getDouble("unitprice"));
				book.setTypename(rs.getString("typename"));
				list.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}

	// 查询指定出版社的图书信息
	public static List<Book> selectBookByPublish(String publish) {
		List<Book> list = new ArrayList<Book>();
		String sql = "select ISBN,typeid,bookname,author,publish,publishdate,printtime,unitprice,typename from book join booktype on book.typeid = booktype.id and publish like '%"
				+ publish + "%'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Book book = new Book();
				book.setISBN(rs.getString("ISBN"));
				book.setTypeid(rs.getInt("typeid"));
				book.setBookname(rs.getString("bookname"));
				book.setAuthor(rs.getString("author"));
				book.setPublish(rs.getString("publish"));
				book.setPublishdate(rs.getDate("publishdate"));
				book.setPrinttime(rs.getInt("printtime"));
				book.setUnitprice(rs.getDouble("unitprice"));
				book.setTypename(rs.getString("typename"));
				list.add(book);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}

	// 图书添加
	public static int insertBook(String ISBN, String typename, String bookname,
			String author, String publish, String publishdate,
			Integer printtime, Double price) {
		int id = 0, i = 0;
		try {
			String sql1 = "select * from booktype where typename = '"
					+ typename + "'";
			ResultSet rs = Dao.executeQuery(sql1);
			try {
				while (rs.next()) {
					id = rs.getInt("id");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			String sql = "insert into Book(ISBN,typeid,bookname,author,publish,publishdate,printtime,unitprice)values('"
					+ ISBN
					+ "','"
					+ id
					+ "','"
					+ bookname
					+ "','"
					+ author
					+ "','"
					+ publish
					+ "','"
					+ publishdate
					+ "','"
					+ printtime
					+ "','" + price + "')";
			i = Dao.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}

	// 图书修改
	public static int UpdateBook(String ISBN, String typename, String bookname,
			String author, String publish, String publishdate,
			Integer printtime, Double unitprice) {
		int typeid = 0, i = 0;
		try {
			String sql1 = "select * from booktype where typename = '"
					+ typename + "'";
			ResultSet rs = Dao.executeQuery(sql1);
			try {
				while (rs.next()) {
					typeid = (rs.getInt("id"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			String sql = "update book set ISBN='" + ISBN + "',typeid='"
					+ typeid + "',bookname='" + bookname + "',author='"
					+ author + "',publish='" + publish + "',publishdate='"
					+ publishdate + "',printtime='" + printtime
					+ "',unitprice=" + unitprice + "" + "where ISBN='" + ISBN
					+ "'";
			i = Dao.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}

	public static void main(String[] args) {
		BookDao.selectBook();
	}
}
