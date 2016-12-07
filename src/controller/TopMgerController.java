package controller;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import model.Askforleavesheet;
import model.Overtimesheet;
import model.Users;
import other.MD5;

public class TopMgerController extends Controller {
	public void checkOvertime(){
		//找出员工提交的状态为proving的加班请求，并分页
		String sql = "select o.id as id, o.uid as uid, u.name as name, o.cause as cause"+
				" from overtimesheet o, users u where u.uid=o.uid and status='proving'";
		Page <Record> page = (Page<Record>) Db.paginate(getParaToInt(), 5, sql);
//		System.out.println(page.getList());
		setAttr("list",page);
		render("overtimeListPage.jsp");
	}
	public void checkLeave(){
		//找出员工提交的状态为proving的请假要求，并分页
		String type = getSessionAttr("dept");
		String sql = "select o.id as id, o.uid as uid, u.name as name, o.cause as cause, o.type as type"+
				" from askforleavesheet o, users u where u.uid=o.uid and status='proving'";
		Page <Record> page = (Page<Record>) Db.paginate(getParaToInt(), 5, sql);
		setAttr("list",page);
		render("leaveListPage.jsp");
	}
	public void overtimeDetail(){
		Overtimesheet ots = Overtimesheet.dao.findById(getParaToInt());
		Record rd =Db.findFirst("select u.name from users u, overtimesheet o where o.id="+ots.getId()+" and o.uid=u.uid");
		setAttr("ots",ots);
		setAttr("name",rd.getStr("name"));
		render("overtimeDetailPage.jsp");
	}
	public void leaveDetail(){
		Askforleavesheet afls = Askforleavesheet.dao.findById(getParaToInt());
		Record rd =Db.findFirst("select u.name from users u, askforleavesheet o where o.id="+afls.getId()+" and o.uid=u.uid");
//		System.out.println(afls);
		setAttr("ots",afls);
		setAttr("name",rd.getStr("name"));
		render("leaveDetailPage.jsp");
	}
	public void agreeLeave(){
		String id = getPara("id");
		String choice = getPara("choice");
		String comment = getPara("comment");
		String startDate = getPara("startDate");
		String endDate = getPara("endDate");
		
		java.sql.Connection conn = null;
		java.sql.Statement st = null; //这里取得数据库连接,需自己改动
		boolean rs ;
		String url = "jdbc:mysql://localhost:3306/assignsys";
		String user = "root";
		String password = "950308";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql;
		if(choice.equals("agreed")){
			sql = "Update askforleavesheet set status ='proved',comment2 ='"+comment+"' where id="+id; 
		}
		else{
			sql = "Update askforleavesheet set status ='denied',comment2 ='"+comment+"' where id="+id; 
		}
		try {
			conn = DriverManager.getConnection(url, user, password);
			st = conn.createStatement();
			rs = st.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		redirect("/topMger/checkLeave/1");
	}
	public void agreeOvertime(){
		String id = getPara("id");
		String choice = getPara("choice");
		String comment = getPara("comment");
		String startDate = getPara("startDate");
		String endDate = getPara("endDate");
		
		java.sql.Connection conn = null;
		java.sql.Statement st = null; //这里取得数据库连接,需自己改动
		boolean rs ;
		String url = "jdbc:mysql://localhost:3306/assignsys";
		String user = "root";
		String password = "950308";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql;
		if(choice.equals("agreed")){
			sql = "Update overtimesheet set status ='proved',comment2 ='"+comment+"' where id="+id; 
		}
		else{
			sql = "Update overtimesheet set status ='denied',comment2 ='"+comment+"' where id="+id; 
		}
		try {
			conn = DriverManager.getConnection(url, user, password);
			st = conn.createStatement();
			rs = st.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		redirect("/topMger/checkOvertime/1");
	}
	public void topInfo(){
			java.sql.Connection conn = null;
			java.sql.Statement st = null; //这里取得数据库连接,需自己改动
			ResultSet rs ;
			String url = "jdbc:mysql://localhost:3306/assignsys";
			String user = "root";
			String password = "950308";
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String sql1 = "SELECT DATEDIFF(endtime,starttime) as date from askforleavesheet a where a.status='proved' and a.type='year' and year(a.starttime)=year(now())";
			int alreadyDays = 0 ;
			try {
				conn = DriverManager.getConnection(url, user, password);
				st = conn.createStatement();
				rs = st.executeQuery(sql1);
				while(rs.next()){
					alreadyDays += Integer.parseInt(rs.getString(1)) + 1;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String sql2 = "SELECT DATEDIFF(endtime,starttime) as date from askforleavesheet a where year(a.starttime)=year(now()) and a.status='proved' and a.type='birth'";
			int birthDays = 0 ;
			try {
				conn = DriverManager.getConnection(url, user, password);
				st = conn.createStatement();
				rs = st.executeQuery(sql2);
				while(rs.next()){
					birthDays += Integer.parseInt(rs.getString(1)) + 1;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String sql3 = "SELECT DATEDIFF(endtime,starttime) as date from askforleavesheet a where year(a.starttime)=year(now()) and a.status='proved' and a.type='sick'";
			int sickDays = 0 ;
			try {
				conn = DriverManager.getConnection(url, user, password);
				st = conn.createStatement();
				rs = st.executeQuery(sql3);
				while(rs.next()){
					sickDays += Integer.parseInt(rs.getString(1)) + 1;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String sql4 = "SELECT DATEDIFF(endtime,starttime) as date from askforleavesheet a where year(a.starttime)=year(now()) and a.status='proved' and a.type='marriage'";
			int marriageDays = 0 ;
			try {
				conn = DriverManager.getConnection(url, user, password);
				st = conn.createStatement();
				rs = st.executeQuery(sql4);
				while(rs.next()){
					marriageDays += Integer.parseInt(rs.getString(1)) + 1;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String sql5 = "SELECT DATEDIFF(endtime,starttime) as date from askforleavesheet a where year(a.starttime)=year(now()) and a.status='proved' and a.type='dead' ";
			int deadDays = 0 ;
			try {
				conn = DriverManager.getConnection(url, user, password);
				st = conn.createStatement();
				rs = st.executeQuery(sql5);
				while(rs.next()){
					deadDays += Integer.parseInt(rs.getString(1)) + 1;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String sql6 = "SELECT DATEDIFF(endtime,starttime) as date from askforleavesheet a where year(a.starttime)=year(now()) and a.status='proved' and a.type='other'";
			int otherDays = 0 ;
			try {
				conn = DriverManager.getConnection(url, user, password);
				st = conn.createStatement();
				rs = st.executeQuery(sql6);
				while(rs.next()){
					otherDays += Integer.parseInt(rs.getString(1)) + 1;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		setAttr("total",alreadyDays+sickDays+birthDays+marriageDays+otherDays+deadDays);
		setAttr("year",alreadyDays);
		setAttr("sick",sickDays);
		setAttr("birth",birthDays);
		setAttr("marriage",marriageDays);
		setAttr("other",otherDays);
		setAttr("dead",deadDays);
		setAttr("overtime",getTOinfor());
		render("topInfoPage.jsp");
	}
	private int getTOinfor(){
		String uid1 = getSessionAttr("uid");
		int uid = Integer.parseInt(uid1);
		java.sql.Connection conn = null;
		java.sql.Statement st = null; //这里取得数据库连接,需自己改动
		ResultSet rs ;
		String url = "jdbc:mysql://localhost:3306/assignsys";
		String user = "root";
		String password = "950308";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql1 = "SELECT DATEDIFF(endtime,starttime) as date from overtimesheet o where year(o.starttime)=year(now()) and o.status='proved'";
		int alreadyDays = 0 ;
		try {
			conn = DriverManager.getConnection(url, user, password);
			st = conn.createStatement();
			rs = st.executeQuery(sql1);
			while(rs.next()){
				alreadyDays += Integer.parseInt(rs.getString(1)) + 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alreadyDays;
	}
	public void leaveRules(){
		render("leaveRulesPage.html");
	}
	public void leaveSearchResult(){
		String uid1 = getPara("uid");
		String dept = getPara("dept");
		String month = getPara("month");
		String type = getPara("type");
		String status = getPara("status");
		if(type.equals("leave"))
		{
			String sql = "select a.id,a.uid,u.dept,a.starttime, a.endtime, a.type, a.cause, a.status"+
		" from askforleavesheet a, users u where a.uid=u.uid";
			String leaveType = getPara("leaveType");		
//			System.out.println(uid+" "+month+" "+leaveType+" "+status);
			if(!uid1.equals(""))
				sql += " and a.uid=" +Integer.parseInt(uid1);
			if(!dept.equals("all"))
				sql += " and u.dept='"+dept+"'";
			if(!month.equals("all"))
				sql += " and month(a.starttime)="+month;
			if(!leaveType.equals("all"))
				sql += " and a.type ='" +leaveType+"'";
			if(!status.equals("all"))
				sql += " and a.status='" +status+"'";
//			System.out.println(sql);
			List<Record> afls = Db.find(sql);
//			System.out.println(afls);
			setAttr("list",afls);
			render("leaveSearchResultPage.jsp");
		}else{
			String sql = "select a.id,a.uid,u.dept, a.starttime, a.endtime, a.cause, a.status"+
					" from overtimesheet a, users u where a.uid=u.uid";
//			System.out.println(uid+" "+month+" "+leaveType+" "+status);
			if(!uid1.equals(""))
				sql += " and a.uid=" +Integer.parseInt(uid1);
			if(!dept.equals("all"))
				sql += " and u.dept='"+dept+"'";
			if(!month.equals("all"))
				sql += " and month(a.starttime)="+month;
			if(!status.equals("all"))
				sql += " and a.status='" +status+"'";
//			System.out.println(sql);
			List<Record> afls = Db.find(sql);
//			System.out.println(afls);
			setAttr("list",afls);
			render("overtimeSearchResultPage.jsp");
		}
	}
}
