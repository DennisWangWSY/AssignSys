package controller;

import com.jfinal.core.Controller;

public class TestController extends Controller {
	public void index(){
		renderText("Html,Jsp test");
	}
	public void jsp(){
		render("jspTestPage.jsp");
	}
	public void html(){
		render("htmlTestPage.html");
	}
	public void grid(){
		render("样例表单.html");
	}
}
