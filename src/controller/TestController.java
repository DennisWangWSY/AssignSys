package controller;

import com.jfinal.core.Controller;

public class TestController extends Controller {
	public void index(){
		render("testPage.jsp");
	}
}
