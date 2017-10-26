package com.upic.social.github.api;

public class WeiShaoProfile {

	private String name;
	
	private String sex;
	
	private String photo_live;
	
	private String mood_words;
	
	private String student_number;
	
	private String nick_name;
	
	private String organization_id;
	
	private String organization;
	
	private String identity;
	
	private String domain;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhoto_live() {
		return photo_live;
	}

	public void setPhoto_live(String photo_live) {
		this.photo_live = photo_live;
	}

	public String getMood_words() {
		return mood_words;
	}

	public void setMood_words(String mood_words) {
		this.mood_words = mood_words;
	}

	public String getStudent_number() {
		return student_number;
	}

	public void setStudent_number(String student_number) {
		this.student_number = student_number;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getOrganization_id() {
		return organization_id;
	}

	public void setOrganization_id(String organization_id) {
		this.organization_id = organization_id;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public WeiShaoProfile(String name, String sex, String photo_live, String mood_words, String student_number,
			String nick_name, String organization_id, String organization, String identity, String domain) {
		super();
		this.name = name;
		this.sex = sex;
		this.photo_live = photo_live;
		this.mood_words = mood_words;
		this.student_number = student_number;
		this.nick_name = nick_name;
		this.organization_id = organization_id;
		this.organization = organization;
		this.identity = identity;
		this.domain = domain;
	}

	public WeiShaoProfile() {
		super();
	}
}
