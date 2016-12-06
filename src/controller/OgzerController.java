package controller;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

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
}
