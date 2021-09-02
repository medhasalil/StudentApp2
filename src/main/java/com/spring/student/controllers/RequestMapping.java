package com.spring.student.controllers;

import org.springframework.web.bind.annotation.RequestMethod;

public @interface RequestMapping {

	String value();

	RequestMethod method();

	String produces();

	String consumes();

}
