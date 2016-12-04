package controller;

import com.jfinal.core.Controller;

public class StaffController extends Controller {
	public void askForLeave(){
		render("askingForLeavePage.html");
	}
	public void askForOvertime(){
		render("askingForOvertimePage.html");
	}
}