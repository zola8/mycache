package com.mz.mycache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mz.mycache.service.TimeService;

@RestController
public class TimeController {

	@Autowired
	private TimeService timeService;

	@RequestMapping("/")
	public String getTime() {
		return timeService.getTime();
	}

}
