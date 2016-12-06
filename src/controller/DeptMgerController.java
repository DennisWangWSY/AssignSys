package controller;
import java.util.Iterator;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import model.Askforleavesheet;
import model.Overtimesheet;
import model.Users;
import other.MD5;

public class DeptMgerController extends Controller {
	public void checkOvertime(){
		//找出该部门员工提交的状态为unproved的加班请求，并分页
		String type = getSessionAttr("dept");
		String sql = "select o.id as id, o.uid as uid, u.name as name, o.cause as cause"+
				" from overtimesheet o, users u where u.uid=o.uid and status='unproved' and u.dept='"+type+"'";
		Page <Record> page = (Page<Record>) Db.paginate(getParaToInt(), 5, sql);
		setAttr("list",page);
		render("overtimeListPage.jsp");
	}
	public void checkLeave(){
		//找出该部门员工提交的状态为unproved的请假要求，并分页
		String type = getSessionAttr("dept");
		String sql = "select o.id as id, o.uid as uid, u.name as name, o.cause as cause, o.type as type"+
				" from askforleavesheet o, users u where u.uid=o.uid and status='unproved' and u.dept='"+type+"'";
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
		System.out.println(afls);
		setAttr("ots",afls);
		setAttr("name",rd.getStr("name"));
		render("leaveDetailPage.jsp");
	}
}
