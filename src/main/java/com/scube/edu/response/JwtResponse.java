package com.scube.edu.response;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	

	private String userid;
	private String email;
	private String roles;

	public JwtResponse(String accessToken, Long id, String userid, String username, String email) {
		this.token = accessToken;
		this.id = id;
		this.userid = userid;
		this.username = username;
		this.email = email;
		
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
}
