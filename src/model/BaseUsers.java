package model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseUsers<M extends BaseUsers<M>> extends Model<M> implements IBean {

	public void setUid(java.lang.Integer uid) {
		set("uid", uid);
	}

	public java.lang.Integer getUid() {
		return get("uid");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}

	public java.lang.String getName() {
		return get("name");
	}

	public void setPassword(java.lang.String password) {
		set("password", password);
	}

	public java.lang.String getPassword() {
		return get("password");
	}

	public void setType(java.lang.String type) {
		set("type", type);
	}

	public java.lang.String getType() {
		return get("type");
	}

	public void setDept(java.lang.String dept) {
		set("dept", dept);
	}

	public java.lang.String getDept() {
		return get("dept");
	}

	public void setAge(java.lang.Integer age) {
		set("age", age);
	}

	public java.lang.Integer getAge() {
		return get("age");
	}

	public void setGender(java.lang.String gender) {
		set("gender", gender);
	}

	public java.lang.String getGender() {
		return get("gender");
	}

	public void setWorkedyears(java.lang.Integer workedyears) {
		set("workedyears", workedyears);
	}

	public java.lang.Integer getWorkedyears() {
		return get("workedyears");
	}

	public void setArrangedrest(java.lang.Integer arrangedrest) {
		set("arrangedrest", arrangedrest);
	}

	public java.lang.Integer getArrangedrest() {
		return get("arrangedrest");
	}

}