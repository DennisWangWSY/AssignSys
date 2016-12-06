package controller;
import com.jfinal.core.Controller;

import model.Users;
import other.MD5;

public class IndexController extends Controller {
	public void index(){
		if("yes".equals(getSessionAttr("isLogged")))
			redirect("index/"+getSessionAttr("userType")+"MainPage.html");
		else{
			setSessionAttr("isLogged","no");
			render("loginPage.html");
		}
	}
	public void toMain(){
		if("yes".equals(getSessionAttr("isLogged")))
			redirect("index/"+getSessionAttr("userType")+"MainPage.html");
		else{
			setSessionAttr("isLogged","no");
			render("loginPage.html");
		}
	}
	public void checkLogin(){
		String uid = getPara("uid");
		String password = getPara("password");
    	Users user = Users.dao.findFirst("select * from users where uid='"+uid+"'");
    	if(user==null)
    		render("loginErrorPage.html");
    	else
    		if(user.getPassword().equals(MD5.GetMD5Code(password))){
    			setSessionAttr("isLogged","yes");
    			setSessionAttr("uid",uid);
    			setSessionAttr("userType",user.getType());
    			setSessionAttr("dept",user.getDept());
    			render(user.getType()+"MainPage.html");
    		}
    		else
    			render("loginErrorPage.html");
	}
	public void logOff(){
		setSessionAttr("isLogged","no");
		redirect("/");
	}
}
