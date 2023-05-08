package com.bezkoder.spring.security.postgresql.payload.response;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String country;
	private String phoneNumber;
	private String title;
	private Boolean isApproved;
	private List<String> roles;

	public JwtResponse(String accessToken, Long id, String username, String email, String firstName, String lastName , String address, String city, String country, String phoneNumber, String title, Boolean isApproved ,List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.country = country;
		this.phoneNumber = phoneNumber;
		this.title = title;
		this.isApproved = isApproved;
		this.roles = roles;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getApproved() {
		return isApproved;
	}

	public void setApproved(Boolean approved) {
		isApproved = approved;
	}

	public List<String> getRoles() {
		return roles;
	}
}
