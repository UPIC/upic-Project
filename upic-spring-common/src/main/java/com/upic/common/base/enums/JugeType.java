package com.upic.common.base.enums;

import java.io.Serializable;

public class JugeType implements Serializable{
	
	private JygeTypeEnum type;
	private Object data;
	public JygeTypeEnum getType() {
		return type;
	}
	public void setType(JygeTypeEnum type) {
		this.type = type;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public JugeType(JygeTypeEnum type, Object data) {
		super();
		this.type = type;
		this.data = data;
	}
	public JugeType() {
		super();
	}
	
}