package com.utility;

import com.github.javafaker.Faker;

public class RandomData {
	Faker fakeData=new Faker();
	public String provideFirstName() {
		return fakeData.name().firstName();
	}
	
	public String provideLastName() {
		return fakeData.name().lastName();
	}
	public String provideZipCode() {
		return fakeData.address().zipCode();
	}
}
