package controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;

import model.Askforleavesheet;
import model.Overtimesheet;

public class StaffController extends Controller {
	public void askForLeave(){
		render("askingForLeavePage.html");
	}
	public void askForOvertime(){
		render("askingForOvertimePage.html");
	}
	public void insertIntoAskForLeaveSheet(){
		getFile("evidence","../evidence");
		System.out.println("asdf");
	}
	
	public void checkOvertimeForm(){
		//检查请求加班的时间范围内是否有其他的请求
		renderText("true");
		String startDate = getPara("startDate");
		String endDate = getPara("endDate");
		String uid1 = getSessionAttr("uid");
		int uid = Integer.parseInt(uid1);
		String sql1 = "select starttime,endtime from overtimesheet o where o.starttime>='"+startDate+"' and o.starttime<='"+endDate+"'"+" and o.uid="+uid;
		String sql2 = "select starttime,endtime from overtimesheet o where o.endtime>='"+startDate+"' and o.endtime<='"+endDate+"'"+" and o.uid="+uid;
		List<Overtimesheet> list1 = Overtimesheet.dao.find(sql1);
		List<Overtimesheet> list2 = Overtimesheet.dao.find(sql2);
		String sql3 = "select starttime,endtime from askforleavesheet a where a.starttime>='"+startDate+"' and a.starttime<='"+endDate+"'"+" and a.uid="+uid;
		String sql4 = "select starttime,endtime from askforleavesheet a where a.endtime>='"+startDate+"' and a.endtime<='"+endDate+"'"+" and a.uid="+uid;
		List<Askforleavesheet> list3 = Askforleavesheet.dao.find(sql3);
		List<Askforleavesheet> list4 = Askforleavesheet.dao.find(sql4);
		if(list1.isEmpty()&&list2.isEmpty()&&list3.isEmpty()&&list4.isEmpty()){
			renderText("true");
		}
		else{
			renderText("false");
		}
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
		
//		List<Record> model =Db.find("select o.id as nid from overtimesheet o, users u where o.uid=u.uid and u.name='Tony'");
//		System.out.println(model);
		new Overtimesheet().set("id", id).set("uid", uid).set("cause", cause).set("starttime", startDate).set("endtime", endDate).set("status", status).save();
	}
	
	public void insertAskforleavesheet(){
		String evidence = "haha";
		String startDate = getPara("startDate");
		String endDate = getPara("endDate");
		String type = getPara("type");
		String cause = getPara("cause");
		String uid1 = getSessionAttr("uid");
		int uid = Integer.parseInt(uid1);
		//id2 = id2 + 1 ;
		//new Askforleavesheet().set("id", id2).set("uid", uid).set("type", type).set("cause", cause).set("starttime", startDate).set("endtime", endDate).set("status", "normal").set("evidence", "haha").save();
	}


}