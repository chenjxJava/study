package com.chenjx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class CountServiceImpl {
	@Autowired
	private JedisPool jedisPool;

	public void set(String key, String value) {
		Jedis resource = jedisPool.getResource();
		resource.set(key, value);
	}

	public String get(String key) {
		Jedis resource = jedisPool.getResource();
		return resource.get(key);
	}
}
