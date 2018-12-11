package cn.e3mall.jedis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.e3mall.common.jedis.JedisClient;

public class JedisClientTest {
	@Test
	public void testJedisClient() throws Exception{
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
		JedisClient jedisClient=applicationContext.getBean(JedisClient.class);
		jedisClient.set("zheng", "jedisClient");
		String string = jedisClient.get("zheng");
		System.out.println(string);
	}
}
