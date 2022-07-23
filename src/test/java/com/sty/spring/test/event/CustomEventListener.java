package com.sty.spring.test.event;

import com.sty.spring.context.ApplicationListener;

import java.util.Date;

/**
 * TODO 一句话描述
 *
 * @author tianma
 * @date 2022/7/23
 * @version 1.0.0
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
	
	@Override
	public void onApplicationEvent(CustomEvent event) {
		System.out.println("收到：" + event.getSource() + "消息;时间：" + new Date());
		System.out.println("消息：" + event.getId() + ":" + event.getMessage());
	}
}
