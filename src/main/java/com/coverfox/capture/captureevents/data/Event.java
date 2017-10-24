package com.coverfox.capture.captureevents.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("events")
public class Event implements Serializable{
	
	private static final long serialVersionUID = 8039391841327409862L;
	@PrimaryKey
	private UUID id;
	private String type;
	private String product;
	private String page;
	private String url;
	private Integer mobile;
	private String email;
	private String name;
	private String kind;
	private String referer;
	private Map<String, String> data;
	
	public Event() {
		id = UUID.randomUUID();
		this.data = new HashMap<String,  String>();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getMobile() {
		return mobile;
	}

	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getData(String key) {
		return this.data.get(key);
	}

	public String addData(String key, String value) {
		return this.data.put(key, value);
	}
	
	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public UUID getId() {
		return id;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}
	
}
