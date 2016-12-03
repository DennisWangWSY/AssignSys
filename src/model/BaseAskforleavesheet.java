package model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseAskforleavesheet<M extends BaseAskforleavesheet<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setUid(java.lang.Integer uid) {
		set("uid", uid);
	}

	public java.lang.Integer getUid() {
		return get("uid");
	}

	public void setType(java.lang.String type) {
		set("type", type);
	}

	public java.lang.String getType() {
		return get("type");
	}

	public void setCause(java.lang.String cause) {
		set("cause", cause);
	}

	public java.lang.String getCause() {
		return get("cause");
	}

	public void setStarttime(java.util.Date starttime) {
		set("starttime", starttime);
	}

	public java.util.Date getStarttime() {
		return get("starttime");
	}

	public void setEndtime(java.util.Date endtime) {
		set("endtime", endtime);
	}

	public java.util.Date getEndtime() {
		return get("endtime");
	}

	public void setStatus(java.lang.String status) {
		set("status", status);
	}

	public java.lang.String getStatus() {
		return get("status");
	}

	public void setEvidence(java.lang.String evidence) {
		set("evidence", evidence);
	}

	public java.lang.String getEvidence() {
		return get("evidence");
	}

}