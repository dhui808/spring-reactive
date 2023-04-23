package com.example.trueclientipdemo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MyController {

	@Autowired
	private HttpServletRequest request;

	@RequestMapping("/getClientIp")
	public String getClientIp(@RequestParam("name") String name) {
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
		}

		String clientIp = request.getHeader("True-Client-IP");

		if (clientIp == null || clientIp.isEmpty()) {
			clientIp = request.getRemoteAddr();
		}
		String s = name + "," + clientIp;
		log.info(s);
		return s;
	}
}