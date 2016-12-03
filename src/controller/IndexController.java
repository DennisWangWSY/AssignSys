package controller;
import com.jfinal.core.Controller;

import model.Users;
import other.MD5;

public class IndexController extends Controller {
	public void index(){
		render("loginPage.html");
	}
	public void checkLogin(){
		String uid = getPara("uid");
		String password = getPara("password");
    	Users user = Users.dao.findFirst("select * from users where uid='"+uid+"'");
    	if(user==null)
    		render("loginErrorPage.html");
    	else
    		if(user.getPassword().equals(MD5.GetMD5Code(password))){
    			renderText("登陆成功");
    			setSessionAttr("uid",uid);
    			setSessionAttr("type",user.getType());
    			}
    		else
    			renderText("登录失败");
	}
}
