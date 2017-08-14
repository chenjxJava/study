package com.chenjx.redis;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisDemo {
	private Jedis jedis;// 非切片额客户端连接
	private JedisPool jedisPool;// 非切片连接池
	private ShardedJedis shardedJedis;// 切片额客户端连接
	private ShardedJedisPool shardedJedisPool;// 切片连接池

	public RedisDemo() {
		initialPool();
		initialShardedPool();
		shardedJedis = shardedJedisPool.getResource();
		jedis = jedisPool.getResource();

	}

	/**
	 * 初始化非切片池
	 */
	private void initialPool() {
		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(20);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(1000l);
		config.setTestOnBorrow(false);

		jedisPool = new JedisPool(config, "116.196.70.225", 6379);
	}

	/**
	 * 初始化切片池
	 */
	private void initialShardedPool() {
		// 池基本配置
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(20);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(1000l);
		config.setTestOnBorrow(false);
		// slave链接
		List shards = new ArrayList();
		shards.add(new JedisShardInfo("116.196.70.225", 6379, "master"));

		// 构造池
		shardedJedisPool = new ShardedJedisPool(config, shards);
	}

	private void StringOperate() {
		System.out.println("======================String_1==========================");
		// 清空数据
		System.out.println("清空库中所有数据：" + jedis.flushDB());

		System.out.println("=============增=============");
		jedis.set("key001", "value001");
		jedis.set("key002", "value002");
		jedis.set("key003", "value003");
		System.out.println("已新增的3个键值对如下：");
		System.out.println(jedis.get("key001"));
		System.out.println(jedis.get("key002"));
		System.out.println(jedis.get("key003"));

		System.out.println("=============删=============");
		System.out.println("删除key003键值对：" + jedis.del("key003"));
		System.out.println("获取key003键对应的值：" + jedis.get("key003"));

		System.out.println("=============改=============");
		// 1、直接覆盖原来的数据
		System.out.println("直接覆盖key001原来的数据：" + jedis.set("key001", "value001-update"));
		System.out.println("获取key001对应的新值：" + jedis.get("key001"));
		// 2、直接覆盖原来的数据
		System.out.println("在key002原来值后面追加：" + jedis.append("key002", "+appendString"));
		System.out.println("获取key002对应的新值" + jedis.get("key002"));

		System.out.println("=============增，删，查（多个）=============");
		/**
		 * mset,mget同时新增，修改，查询多个键值对 等价于： jedis.set("name","ssss"); jedis.set("jarorwar","xxxx");
		 */
		System.out.println("一次性新增key201,key202,key203,key204及其对应值："
			+ jedis.mset("key201", "value201", "key202", "value202", "key203", "value203", "key204", "value204"));
		System.out.println("一次性获取key201,key202,key203,key204各自对应的值："
			+ jedis.mget("key201", "key202", "key203", "key204"));
		System.out.println("一次性删除key201,key202：" + jedis.del(new String[]{"key201", "key202"}));
		System.out.println("一次性获取key201,key202,key203,key204各自对应的值："
			+ jedis.mget("key201", "key202", "key203", "key204"));
		System.out.println();

		// jedis具备的功能shardedJedis中也可直接使用，下面测试一些前面没用过的方法
		System.out.println("======================String_2==========================");
		// 清空数据
		System.out.println("清空库中所有数据：" + jedis.flushDB());

		System.out.println("=============新增键值对时防止覆盖原先值=============");
		System.out.println("原先key301不存在时，新增key301：" + shardedJedis.setnx("key301", "value301"));
		System.out.println("原先key302不存在时，新增key302：" + shardedJedis.setnx("key302", "value302"));
		System.out.println("当key302存在时，尝试新增key302：" + shardedJedis.setnx("key302", "value302_new"));
		System.out.println("获取key301对应的值：" + shardedJedis.get("key301"));
		System.out.println("获取key302对应的值：" + shardedJedis.get("key302"));

		System.out.println("=============超过有效期键值对被删除=============");
		// 设置key的有效期，并存储数据
		System.out.println("新增key303，并指定过期时间为2秒" + shardedJedis.setex("key303", 2, "key303-2second"));
		System.out.println("获取key303对应的值：" + shardedJedis.get("key303"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		System.out.println("3秒之后，获取key303对应的值：" + shardedJedis.get("key303"));

		System.out.println("=============获取原值，更新为新值一步完成=============");
		System.out.println("key302原值：" + shardedJedis.getSet("key302", "value302-after-getset"));
		System.out.println("key302新值：" + shardedJedis.get("key302"));

		System.out.println("=============获取子串=============");
		System.out.println("获取key302对应值中的子串：" + shardedJedis.getrange("key302", 5, 7));
	}


	public static void main(String[] args) {
		new RedisDemo().StringOperate();
	}
}
