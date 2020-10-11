package server06.miniproject.model;

public class Funding {
	private int fund_id;
	private String fund_inst;
	private String inst_icon;
	private String inst_des;
	private int tar_point;
	private int acu_point;
	
	public Funding(int fund_id, String fund_inst, String inst_icon, String inst_des, int tar_point,int acu_point) {
		super();
		this.fund_id = fund_id;
		this.fund_inst = fund_inst;
		this.inst_icon = inst_icon;
		this.inst_des = inst_des;
		this.tar_point = tar_point;
		this.acu_point = acu_point;
	}

	public Funding() {
		super();
	}

	public int getFund_id() {
		return fund_id;
	}

	public void setFund_id(int fund_id) {
		this.fund_id = fund_id;
	}

	public String getFund_inst() {
		return fund_inst;
	}

	public void setFund_inst(String fund_inst) {
		this.fund_inst = fund_inst;
	}

	public String getInst_icon() {
		return inst_icon;
	}

	public void setInst_icon(String inst_icon) {
		this.inst_icon = inst_icon;
	}

	public String getInst_des() {
		return inst_des;
	}

	public void setInst_des(String inst_des) {
		this.inst_des = inst_des;
	}

	public int getTar_point() {
		return tar_point;
	}

	public void setTar_point(int tar_point) {
		this.tar_point = tar_point;
	}

	public int getAcu_point() {
		return acu_point;
	}

	public void setAcu_point(int acu_point) {
		this.acu_point = acu_point;
	}
	
}