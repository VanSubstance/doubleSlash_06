package server06.miniproject.model;

public class Funding {
	private String fund_id;
	private String fund_inst;
	private String inst_icon;
	private String inst_des;
	private String fund_img;
	private int tar_point;
	private int acu_point;
	
	public Funding(String fund_id, String fund_inst, String inst_icon, String inst_des, String fund_img, int tar_point,
			int acu_point) {
		super();
		this.fund_id = fund_id;
		this.fund_inst = fund_inst;
		this.inst_icon = inst_icon;
		this.inst_des = inst_des;
		this.fund_img = fund_img;
		this.tar_point = tar_point;
		this.acu_point = acu_point;
	}

	public Funding() {
		super();
	}

	public String getFund_id() {
		return fund_id;
	}

	public void setFund_id(String fund_id) {
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

	public String getFund_img() {
		return fund_img;
	}

	public void setFund_img(String fund_img) {
		this.fund_img = fund_img;
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