package controller;

import org.eclipse.jetty.util.security.Credential.MD5;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import model.Askforleavesheet;
import model.Overtimesheet;
import model.Users;

public class OgzerController extends Controller {
	public void userList(){
		String sql = "select * from users";
		Page <Users> page = (Page<Users>) Users.dao.paginate(getParaToInt(), 5, sql);
//		System.out.println(page.getList());
		setAttr("list",page);
		render("userListPage.jsp");
	}
	public void userDetail(){
		Users user = Users.dao.findById(getParaToInt());
//		System.out.println(user);
		setAttr("user",user);
		render("userDetailPage.jsp");
	}
	public void addUser(){
		render("addUserPage.html");
	}
	public void insertUser(){
		String name = getPara("name");
		String password = getPara("password");
		String age1 = getPara("age");
		int age = Integer.parseInt(age1);
		String workedyears1 = getPara("workedyears");
		int workedyears = Integer.parseInt(workedyears1);
		String gender = getPara("gender");
		String type = getPara("type");
		String dept = getPara("dept");
		int uid;
		Users user = Users.dao.findFirst("select max(uid) as uid from users");
		if(user.getUid() != null)
			uid = user.getUid()+1;
		else
		{
			uid = 1001;
		}
		new Users().set("uid",uid).set("name",name).set("password",other.MD5.GetMD5Code(password))
		.set("age",age).set("workedyears",workedyears).set("gender",gender)
		.set("type",type).set("dept",dept).save();
		redirect("/");
		//		System.out.println(name+" "+password+" "+age+" "+workedyears+" "+gender+" "+type+" "+dept+" "+uid);
	}
	public void updateUser(){
		String password = getPara("password");
		String age1 = getPara("age");
		int age = Integer.parseInt(age1);
		String workedyears1 = getPara("workedyears");
		int workedyears = Integer.parseInt(workedyears1);
		String uid1 = getPara("uid");
		int uid = Integer.parseInt(uid1);
		String type = getPara("type");
		String dept = getPara("dept");
//		System.out.println(uid+" "+password+" "+age+" "+workedyears+" "+type+" "+dept);
		if(!password.equals(""))
		{
			Users.dao.findById(uid).set("password",other.MD5.GetMD5Code(password)).set("type", type)
			.set("dept", dept).set("age", age)
			.set("workedyears", workedyears).update();
		}else{
			Users.dao.findById(uid).set("type", type).set("dept", dept)
			.set("age", age).set("workedyears", workedyears).update();
		}
			redirect("/ogzer/userList/1");
	}
	
}
