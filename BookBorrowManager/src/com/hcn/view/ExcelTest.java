package com.hcn.view;


import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hcn.db.Dao;
import com.hcn.model.Reader;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCell;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Excel导入导出
 * 
 * @author 侯楚男
 * @version 1.0 2015/1/12 17:52PM
 */
public class ExcelTest {

    /**
     * 导出(导出到磁盘)
     */
    
    public void exportExcel() {
        WritableWorkbook book = null;
        try {
            // 打开文件
            book = Workbook.createWorkbook(new File("C:/Users/Hou/Desktop/测试.xls"));
            // 生成名为"日结账"的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("日结账", 0);
            // 指定单元格位置是第一列第一行(0, 0)以及单元格内容为张三
            Label label = new Label(0, 0, "张三");
            // 将定义好的单元格添加到工作表中
            sheet.addCell(label);
            // 保存数字的单元格必须使用Number的完整包路径
            jxl.write.Number number = new jxl.write.Number(1, 0, 30);
            sheet.addCell(number);
            // 写入数据并关闭文件
            book.write();
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(book!=null){
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            }
        }
    }
    
    /**
     * 对象数据写入到Excel
     */
  private Label str = new Label(0, 0, "asd");
    public void writeExcel() {
        WritableWorkbook book = null;
        try {
            // 打开文件
            book = Workbook.createWorkbook(new File("C:/Users/Hou/Desktop/stu.xls"));
            // 生成名为"日结账"的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("日结账", 0);
            sheet.addCell(str);
            List<Reader> stuList=queryStudentList();
            if(stuList!=null && !stuList.isEmpty()){
                for(int i=1; i<stuList.size(); i++){
                    sheet.addCell(new Label(0, i, stuList.get(i).getName()));
                    sheet.addCell(new Number(1, i, stuList.get(i).getAge()));
                }
            }
            
            // 写入数据并关闭文件
            book.write();
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(book!=null){
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            }
        }
    
    }
    private List<Reader> queryStudentList(){
        List<Reader> stuList=new ArrayList<Reader>();
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
				stuList.add(reader);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dao.close();
	
        return stuList;
    }
    public static void main(String[] args){
    	ExcelTest t = new ExcelTest();
    	t.exportExcel();
    }
}