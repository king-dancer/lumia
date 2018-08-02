package com.spring.apple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppleApplicationTests {

	@Test
	public void contextLoads() {
	}
		public static void main(String[] args) {
	        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	        //加密"0"
	        String encode = bCryptPasswordEncoder.encode("zly123456");
	        System.out.println(encode);
	        //结果：$2a$10$/eEV4X7hXPzYGzOLXfCizu6h7iRisp7I116wPA3P9uRcHAKJyY4TK
	        
	    }

	
}
