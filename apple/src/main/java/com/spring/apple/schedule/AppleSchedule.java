package com.spring.apple.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.spring.apple.dto.SysUser;
import com.spring.apple.repository.SysUserRepository;

@Component
public class AppleSchedule {
//	@Autowired
//	SysUserRepository suser;
//	private Logger logger = LoggerFactory.getLogger(AppleSchedule.class);
//	private int i=4;
//	@Scheduled(fixedRate=5000)
//	public void showTime(){
//		Date date = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat();
//		logger.info("当前时间：{}",sdf.format(date));
//		SysUser su = new SysUser();
//		su.setId(i);
//		su.setPassword("$2a$10$uIdRVS46wBM.MhCB1wVVjuTTb/XBHVgQdojEBGbDUZLy2AytqpYUO");
//		su.setUsername("用户"+i);
//		suser.save(su);
//		i++;
//	}
}
