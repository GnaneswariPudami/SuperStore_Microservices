package com.superstore.useraccount;

import org.springframework.boot.SpringApplication;

public class TestUseraccountApplication {

	public static void main(String[] args) {
		SpringApplication.from(UseraccountApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
