package com.example.reactive.webclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class ServiceInvoker implements CommandLineRunner {

	@Autowired
    private MyService service;
	
	@Override
	public void run(String... args) throws Exception {

		boolean blocking = false;
		int count = 100;
		String blockingText = "Blocking chat";
		
		if (args.length > 0) {
			blocking = "nb".equals(args[0])? false : true;
		}
		
		if (args.length > 1) {
			count = Integer.valueOf(args[1]);
		}
		
		long start = System.currentTimeMillis();
		
		if (blocking) {
			for (int i = 0; i < count; i++) {
				service.getChatBlocking();
				System.out.println("Blocking: " + i);
			}
		} else {
			blockingText = "Non-blocking Chat";
			
			for (int i = 0; i < count; i++) {
				service.getChatNonBlocking();
				System.out.println("Non-Blocking: " + i);
			}
		}

		long end = System.currentTimeMillis();
		
		System.out.println(blockingText + " takes " + (end - start) / 1000 + " seconds.");
	}

}
