package com.hcn.db;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import com.hcn.model.*;

public class BookBorrowDao {
	//查询读者借书信息
	public static List<BookBorrow>selectBorrowBookByReaderId(String readerid){
		List<BookBorrow>list = new ArrayList<BookBorrow>();
		String sql ="select *,book.bookname from borrowbook,book where book.ISBN=borrowbook.ISBN and readerid= '"+readerid+"' and returndate is null" ;
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while(rs.next()){
			BookBorrow bb = new BookBorrow();
			bb.setReaderid(rs.getString("readerid"));
			bb.setISBN(rs.getString("ISBN"));
			bb.setBookname(rs.getString("bookname"));
			bb.setFune(rs.getDouble("fine"));
			bb.setBorroedate(rs.getDate("borrowdate"));
			bb.setReturndate(rs.getDate("returndate"));
			list.add(bb);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	
//	public static List<BookBorrow> SelectBorrowbookByreaderidISBN(String readerid,String isbn){
//		List<BookBorrow> list=new ArrayList<BookBorrow>();
//		String sql="select bb.ISBN,bb.borrowdate,b.bookname from borrowbook bb,book b where b.ISBN=bb.ISBN and bb.readerid='"+readerid+"' and bb.ISBN='"+isbn+"'";
//		ResultSet rs=Dao.executeQuery(sql);
//		try {
//			while(rs.next()){
//				BookBorrow b=new BookBorrow();
//				b.setISBN(rs.getString("ISBN"));
//				b.setBorroedate(rs.getDate("borrowdate"));
//				list.add(b);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
	//图书借阅
	public static int borrowBook(String readerid,String ISBN,String borrowdate){
		int i = 0;
		try {
			String sql ="insert into borrowbook(readerid,ISBN,borrowdate)values('"+readerid+"','"+ISBN+"','"+borrowdate+"')";
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	//图书归还
	public static int returnBook(String readerid,String ISBN,Date returndate){
		int i = 0 ;
		try {
			String sql = "update borrowbook set returndate ='"+returndate+"' where readerid = '"+readerid+"'and ISBN = '"+ISBN+"'";
//			String sql1 = "delete from  borrowbook  where readerid = '"+readerid+"'and ISBN = '"+ISBN+"'";
			i = Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
}
