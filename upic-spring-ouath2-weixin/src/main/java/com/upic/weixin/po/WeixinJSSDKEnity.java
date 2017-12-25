package com.upic.weixin.po;

public class WeixinJSSDKEnity {

	private String appid;
	
	private String timestamp;
	
	private String nonceStr;
	
	private String signature;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public WeixinJSSDKEnity(String appid, String timestamp, String nonceStr, String signature) {
		super();
		this.appid = appid;
		this.timestamp = timestamp;
		this.nonceStr = nonceStr;
		this.signature = signature;
	}


	
}
