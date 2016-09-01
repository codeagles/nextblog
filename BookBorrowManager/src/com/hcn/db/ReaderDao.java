package com.hcn.db;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import com.hcn.model.*;

public class ReaderDao {
	// 查询所有读者信息
	public static List<Reader> selectReader() {
		List<Reader> list = new ArrayList<Reader>();
		String sql = "select readerid,type,name,age,sex,phone,dept,regdate,typename,maxborrownum,limit from reader join readertype on reader.type = readertype.id";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Reader reader = new Reader();
				reader.setReaderid(rs.getInt("readerid"));
				reader.setType(rs.getInt("type"));
				reader.setName(rs.getString("name"));
				reader.setAge(rs.getInt("age"));
				reader.setSex(rs.getString("sex"));
				reader.setPhone(rs.getString("phone"));
				reader.setDept(rs.getString("dept"));
				reader.setRegdate(rs.getDate("regdate"));
				reader.setTypename(rs.getString("typename"));
				reader.setMaxborrownum(rs.getInt("maxborrownum"));
				reader.setLimit(rs.getInt("limit"));
				list.add(reader);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}

	// 查询指定编号读者信息
	public static List<Reader> selectReaderById(String id) {
		List<Reader> list = new ArrayList<Reader>();
		String sql = "select readerid,type,name,age,sex,phone,dept,regdate,typename,maxborrownum,limit from reader join readertype on reader.type = readertype.id and readerid='"
				+ id + "'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Reader reader = new Reader();
				reader.setReaderid(rs.getInt("readerid"));
				reader.setType(rs.getInt("type"));
				reader.setName(rs.getString("name"));
				reader.setAge(rs.getInt("age"));
				reader.setSex(rs.getString("sex"));
				reader.setPhone(rs.getString("phone"));
				reader.setDept(rs.getString("dept"));
				reader.setRegdate(rs.getDate("regdate"));
				reader.setTypename(rs.getString("typename"));
				reader.setMaxborrownum(rs.getInt("maxborrownum"));
				reader.setLimit(rs.getInt("limit"));
				list.add(reader);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}

	// 查询指定姓名的读者信息
	public static List<Reader> selectReaderByName(String name) {
		List<Reader> list = new ArrayList<Reader>();

		String sql = "select readerid,type,name,age,sex,phone,dept,regdate,typename,maxborrownum,limit from reader join readertype on reader.type = readertype.id and name like'%"
				+ name + " %'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Reader reader = new Reader();
				reader.setReaderid(rs.getInt("readerid"));
				reader.setType(rs.getInt("type"));
				reader.setName(rs.getString("name"));
				reader.setAge(rs.getInt("age"));
				reader.setSex(rs.getString("sex"));
				reader.setPhone(rs.getString("phone"));
				reader.setDept(rs.getString("dept"));
				reader.setRegdate(rs.getDate("regdate"));
				reader.setTypename(rs.getString("typename"));
				reader.setMaxborrownum(rs.getInt("maxborrownum"));
				reader.setLimit(rs.getInt("limit"));
				list.add(reader);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}

	// 按照指定部门查找
	public static List<Reader> selectReaderByDept(String dept) {
		List<Reader> list = new ArrayList<Reader>();

		String sql = "select readerid,type,name,age,sex,phone,dept,regdate,typename,maxborrownum,limit from reader join readertype on reader.type = readertype.id and dept like'%"
				+ dept + "%'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Reader reader = new Reader();
				reader.setReaderid(rs.getInt("readerid"));
				reader.setType(rs.getInt("type"));
				reader.setName(rs.getString("name"));
				reader.setAge(rs.getInt("age"));
				reader.setSex(rs.getString("sex"));
				reader.setPhone(rs.getString("phone"));
				reader.setDept(rs.getString("dept"));
				reader.setRegdate(rs.getDate("regdate"));
				reader.setTypename(rs.getString("typename"));
				reader.setMaxborrownum(rs.getInt("maxborrownum"));
				reader.setLimit(rs.getInt("limit"));
				list.add(reader);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}

	// 按照类别查找
	public static List<Reader> selectReaderByType(String type) {
		List<Reader> list = new ArrayList<Reader>();

		String sql = "select readerid,type,name,age,sex,phone,dept,regdate,typename,maxborrownum,limit from reader join readertype on reader.type = readertype.id and typename like'%"+type+"%'";
		ResultSet rs = Dao.executeQuery(sql);
		try {
			while (rs.next()) {
				Reader reader = new Reader();
				reader.setReaderid(rs.getInt("readerid"));
				reader.setType(rs.getInt("type"));
				reader.setName(rs.getString("name"));
				reader.setAge(rs.getInt("age"));
				reader.setSex(rs.getString("sex"));
				reader.setPhone(rs.getString("phone"));
				reader.setDept(rs.getString("dept"));
				reader.setRegdate(rs.getDate("regdate"));
				reader.setTypename(rs.getString("typename"));
				reader.setMaxborrownum(rs.getInt("maxborrownum"));
				reader.setLimit(rs.getInt("limit"));
				list.add(reader);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return list;
	}
	//读者插入
	public static int insertReader(String readerid, String typename,String name,String age,String sex,String phone,String dept,String reg){
		int typeid = 0,i =0;
		try{
			String sql1 = "select * from readertype where typename = '"+ typename+"'";
			ResultSet rs = Dao.executeQuery(sql1);
			try{
				while (rs.next()){
					typeid = rs.getInt("id");
					
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
		String sql = "insert into reader(readerid,type,name,age,sex,phone,dept,regdate)values('"+readerid+"','"+typeid+"','"+name+"','"+age+"','"+sex+"','"+phone+"','"+dept+"','"+reg+"')";
		
		i = Dao.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
	//读者修改
	public static int UpdateReader(String id, String typename,String name,Integer age,String sex,String phone,String dept,String reg){
		int typeid = 0,i=0;
		try {
			String sql1 = "select * from readertype where typename = '"+ typename +"'";
			ResultSet rs = Dao.executeQuery(sql1);
			try {
				while(rs.next()){
					typeid = rs.getInt("id");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			String sql = "update reader set readerid ='"+id+"',type='"+typeid+"',name = '"+name+"',age='"+age+"',sex='"+sex+"',phone='"+phone+"',dept='"+dept+"',regdate='"+reg+"'"+"where readerid ='"+id+"'";
			i =Dao.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
		return i;
	}
}
