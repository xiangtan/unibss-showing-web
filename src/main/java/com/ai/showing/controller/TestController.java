package com.ai.showing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController
{
	@RequestMapping("/test")
	public String test()
	{
		return "test/test";
	}
	@RequestMapping("")
	public String index()
	{
		return "index";
	}
}
