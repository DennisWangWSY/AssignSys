package controller;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;

import model.Askforleavesheet;
import model.Overtimesheet;
import model.Users;

public class StaffController extends Controller {
	public void askForLeave(){
		render("askingForLeavePage.html");
	}
	public void askForOvertime(){
		render("askingForOvertimePage.html");
	}
	
	public void checkOvertimeForm(){
		//检查请求加班的时间范围内是否有其他的请求或是否提前加班
		String startDate = getPara("startDate");
		String endDate = getPara("endDate");
		String type = getPara("type");
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = null;
		try {
			date1 = sdf.parse(startDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(now.after(date1)){
			renderText ("beforetoday");
		}else{
			String uid1 = getSessionAttr("uid");
			int uid = Integer.parseInt(uid1);
			String sql1 = "select starttime,endtime from overtimesheet o where o.status<>'denied' and o.starttime>='"+startDate+"' and o.starttime<='"+endDate+"'"+" and o.uid="+uid;
			String sql2 = "select starttime,endtime from overtimesheet o where o.status<>'denied' and o.endtime>='"+startDate+"' and o.endtime<='"+endDate+"'"+" and o.uid="+uid;
			List<Overtimesheet> list1 = Overtimesheet.dao.find(sql1);
			List<Overtimesheet> list2 = Overtimesheet.dao.find(sql2);
			String sql3 = "select starttime,endtime from askforleavesheet a where a.status<>'denied' and a.starttime>='"+startDate+"' and a.starttime<='"+endDate+"'"+" and a.uid="+uid;
			String sql4 = "select starttime,endtime from askforleavesheet a where a.status<>'denied' and a.endtime>='"+startDate+"' and a.endtime<='"+endDate+"'"+" and a.uid="+uid;
			List<Askforleavesheet> list3 = Askforleavesheet.dao.find(sql3);
			List<Askforleavesheet> list4 = Askforleavesheet.dao.find(sql4);
			if(list1.isEmpty()&&list2.isEmpty()&&list3.isEmpty()&&list4.isEmpty()){
				renderText("true");
			}
			else{
				renderText("false");
			}
		}		
	}
	public void checkLeaveForm(){
		String startDate = getPara("startDate");
		String endDate = getPara("endDate");
		String type = getPara("type");
		String uid1 = getSessionAttr("uid");
		int uid = Integer.parseInt(uid1);
		if(!judge2(startDate,endDate,type))
		{
			if(type.equals("sick"))
				renderText("sickwrong");
			else
				renderText("beforetoday");
		}
		else{
			if(!judge(startDate,endDate,uid))
				renderText("cover");
			else
			{
				if(!judge1(startDate,endDate,uid,type))
					renderText("toomuch");
				else
					renderText("true");
			}
		}
		
	}
	private boolean judge(String startDate, String endDate, int uid){
		String sql1 = "select starttime,endtime from overtimesheet o where o.status<>'denied' and o.starttime>='"+startDate+"' and o.starttime<='"+endDate+"'"+" and o.uid="+uid;
		String sql2 = "select starttime,endtime from overtimesheet o where o.status<>'denied' and o.endtime>='"+startDate+"' and o.endtime<='"+endDate+"'"+" and o.uid="+uid;
		List<Overtimesheet> list1 = Overtimesheet.dao.find(sql1);
		List<Overtimesheet> list2 = Overtimesheet.dao.find(sql2);
		String sql3 = "select starttime,endtime from askforleavesheet a where a.status<>'denied' and a.starttime>='"+startDate+"' and a.starttime<='"+endDate+"'"+" and a.uid="+uid;
		String sql4 = "select starttime,endtime from askforleavesheet a where a.status<>'denied' and a.endtime>='"+startDate+"' and a.endtime<='"+endDate+"'"+" and a.uid="+uid;
		List<Askforleavesheet> list3 = Askforleavesheet.dao.find(sql3);
		List<Askforleavesheet> list4 = Askforleavesheet.dao.find(sql4);
		if(list1.isEmpty()&&list2.isEmpty()&&list3.isEmpty()&&list4.isEmpty()){
			return true;
		}
		else{
			return false;
		}
	}
	
	//请假是否符合要求
	private boolean judge1(String startDate, String endDate, int uid, String type){
		java.sql.Connection conn = null;
		java.sql.Statement st = null; //这里取得数据库连接,需自己改动
		ResultSet rs = null;
		String url = "jdbc:mysql://localhost:3306/assignsys";
		String user = "root";
		String password = "950308";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql = "SELECT DATEDIFF(endtime,starttime) as date from askforleavesheet a where a.status<>'denied' and a.type='year' and where year(starttime)=year(now()) and uid="+uid;
		int alreadyDays = 0 ;
		try {
			conn = DriverManager.getConnection(url, user, password);
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				alreadyDays += Integer.parseInt(rs.getString(1)) + 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int days = 0 ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date1 = sdf.parse(startDate);
			Date date2 = sdf.parse(endDate);
			days = (int) ((date2.getTime()-date1.getTime())/(24*60*60*1000)) + 1;
		    //days=date2.getDay()-date1.getDay()+1;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Users user1 = Users.dao.findFirst("select * from users where uid='"+uid+"'");
		
		if(type.equals("year")){
			if(user1.getWorkedyears()<=2){
				if(days+alreadyDays>5){
					return false;
				}
			}
			else if(user1.getWorkedyears()<=5){
				if(days+alreadyDays>10){
					return false;
				}
			}
			else{
				if(days+alreadyDays>15){
					return false;
				}
			}
			return true;
		}
		
		if(type.equals("marriage")){
			if(user1.getGender().equals("male")){
				if(user1.getAge()<25){
					if(days>3){
						return false;
					}
				}
				else{
					if(days>7){
						return false;
					}
				}
			}
			else{
				if(user1.getAge()<23){
					if(days>3){
						return false;
					}
				}
				else{
					if(days>7){
						return false;
					}
				}
			}
			return true;
		}
		
		if(type.equals("birth")){
			if(user1.getGender().equals("male")){
				if(days>30){
					return false;
				}
			}
			else{
				if(days>105){
					return false;
				}
			}
			return true;
		}
		
		if(type.equals("dead")){
			if(days>3){
				return false;
			}
		}
		
		return true;
	}
	
	//请假延后（病假除外）
	private boolean judge2(String startDate, String endDate, String type){
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = null;
		try {
			date1 = sdf.parse(startDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(type.equals("sick")){
			if(date1.after(now)){
				return false;
			}
			return true;
		}
		if(now.after(date1)){
			return false;
		}
		return true;
	}
	
	public void insertOvertimesheet(){
		String startDate = getPara("startDate");
		String endDate = getPara("endDate");
		String cause = getPara("cause");
		String uid1 = getSessionAttr("uid");
		String status = "unproved";
		int uid = Integer.parseInt(uid1);
		Overtimesheet ots = Overtimesheet.dao.findFirst("select max(id) as id from overtimesheet where uid="+uid);
		
		int id;
		if(ots.getId() != null)
			id = ots.getId()+1;
		else
			id = uid*10000+1;
		new Overtimesheet().set("id", id).set("uid", uid).set("cause", cause).set("starttime", startDate).set("endtime", endDate).set("status", status).save();
		redirect("/");
	}
	
	public void insertAskforleavesheet(){
		UploadFile evidence = getFile("evidence","../evidence");
		String startDate = getPara("startDate");
		String endDate = getPara("endDate");
		String type = getPara("type");
		String cause = getPara("cause");
		String uid1 = getSessionAttr("uid");
		int uid = Integer.parseInt(uid1);
		int id;
		Askforleavesheet ots = Askforleavesheet.dao.findFirst("select max(id) as id from askforleavesheet where uid="+uid);
		if(ots.getId() != null)
			id = ots.getId()+1;
		else
			id = uid*10000+1;

		if(type.equals("year")||type.equals("other"))
			new Askforleavesheet().set("id", id).set("uid", uid).set("type", type).set("cause", cause).set("starttime", startDate).set("endtime", endDate).set("status", "unproved").save();
		else
			new Askforleavesheet().set("id", id).set("uid", uid).set("type", type).set("cause", cause).set("starttime", startDate).set("endtime", endDate).set("status", "unproved").set("evidence", evidence.getFileName()).save();
		redirect("/");
	}
	public void personalInfo(){
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
		String sql1 = "SELECT DATEDIFF(endtime,starttime) as date from askforleavesheet a where a.status='proved' and a.type='year' and year(a.starttime)=year(now()) and uid="+uid;
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
		Users user1 = Users.dao.findFirst("select * from users where uid='"+uid+"'");
		int remaindays = 0;
		if(user1.getWorkedyears()<=2){
			remaindays = 5 - alreadyDays;
		}
		else if(user1.getWorkedyears()<=5){
			remaindays = 10 - alreadyDays;
		}
		else{
			remaindays = 15 - alreadyDays;
		}
		
		String sql2 = "SELECT DATEDIFF(endtime,starttime) as date from askforleavesheet a where year(a.starttime)=year(now()) and a.status='proved' and a.type='birth' and uid="+uid;
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
		
		String sql3 = "SELECT DATEDIFF(endtime,starttime) as date from askforleavesheet a where year(a.starttime)=year(now()) and a.status='proved' and a.type='sick' and uid="+uid;
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
		
		String sql4 = "SELECT DATEDIFF(endtime,starttime) as date from askforleavesheet a where year(a.starttime)=year(now()) and a.status='proved' and a.type='marriage' and uid="+uid;
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
		
		String sql5 = "SELECT DATEDIFF(endtime,starttime) as date from askforleavesheet a where year(a.starttime)=year(now()) and a.status='proved' and a.type='dead' and uid="+uid;
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
		
		String sql6 = "SELECT DATEDIFF(endtime,starttime) as date from askforleavesheet a where year(a.starttime)=year(now()) and a.status='proved' and a.type='other' and uid="+uid;
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
		setAttr("rest",remaindays);
		setAttr("overtime",getUOinfor());
		render("personalInfoPage.jsp");
	}
	private int getUOinfor(){
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
		String sql1 = "SELECT DATEDIFF(endtime,starttime) as date from overtimesheet o where year(o.starttime)=year(now()) and o.status='proved' and uid="+uid;
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
		String uid1 = getSessionAttr("uid");
		int uid = Integer.parseInt(uid1);
		String month = getPara("month");
		String type = getPara("type");
		String status = getPara("status");
		if(type.equals("leave"))
		{
			String sql = "select id,starttime, endtime, type, cause, status from askforleavesheet where uid="+uid;
			String leaveType = getPara("leaveType");		
//			System.out.println(uid+" "+month+" "+leaveType+" "+status);
			if(!month.equals("all"))
				sql += " and month(starttime)="+month;
			if(!leaveType.equals("all"))
				sql += " and type ='" +leaveType+"'";
			if(!status.equals("all"))
				sql += " and status='" +status+"'";
//			System.out.println(sql);
			List<Askforleavesheet> afls = Askforleavesheet.dao.find(sql);
//			System.out.println(afls);
			setAttr("list",afls);
			render("leaveSearchResultPage.jsp");
		}else{
			String sql = "select id,starttime,endtime, cause, status from overtimesheet where uid="+uid;	
//			System.out.println(uid+" "+month+" "+leaveType+" "+status);
			if(!month.equals("all"))
				sql += " and month(starttime)="+month;
			if(!status.equals("all"))
				sql += " and status='" +status+"'";
//			System.out.println(sql);
			List<Overtimesheet> afls = Overtimesheet.dao.find(sql);
//			System.out.println(afls);
			setAttr("list",afls);
			render("overtimeSearchResultPage.jsp");
		}
	}


}