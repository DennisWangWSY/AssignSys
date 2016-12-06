package config;

import java.util.logging.Logger;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;

import controller.DeptMgerController;
import controller.IndexController;
import controller.OgzerController;
import controller.StaffController;
import controller.TestController;
import controller.TopMgerController;
import model._MappingKit;

public class AssignConfig extends JFinalConfig {
	private final Logger logger = Logger.getLogger("");
	@Override
	public void configConstant(Constants me) {
		// TODO Auto-generated method stub
		me.setDevMode(true);
		me.setViewType(ViewType.JSP);
	}

	@Override
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("basePath"));	
	}

	@Override
	public void configInterceptor(Interceptors arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configPlugin(Plugins me) {
		// TODO Auto-generated method stub
		C3p0Plugin c3p0Plugin = new C3p0Plugin("jdbc:mysql://127.0.0.1/assignsys?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull"
				,"root","950308");
		me.add(c3p0Plugin);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
		_MappingKit.mapping(arp);
	}

	@Override
	public void configRoute(Routes me) {
		// TODO Auto-generated method stub
		me.add("ogzer",OgzerController.class,"/ogzer");
		me.add("/topMger",TopMgerController.class,"/topMger");
		me.add("/deptMger",DeptMgerController.class,"/deptMger");
		me.add("/staff",StaffController.class,"/staff");
		me.add("/test",TestController.class,"/test");
		me.add("/",IndexController.class,"/index");
		
	}

}
