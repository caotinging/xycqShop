package caotinging.utils;

import java.util.ResourceBundle;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolUtils {
	private static JedisPool jedisPool;
	
	static {
		ResourceBundle bundle = ResourceBundle.getBundle("jedis-config");
		
		int maxIdle = Integer.parseInt(bundle.getString("maxIdle"));
		int maxTotal = Integer.parseInt(bundle.getString("maxTotal"));
		int minIdle = Integer.parseInt(bundle.getString("minIdle"));
		String host = bundle.getString("host");
		int port = Integer.parseInt(bundle.getString("port"));
		
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(maxIdle);
		poolConfig.setMaxTotal(maxTotal);
		poolConfig.setMinIdle(minIdle);
		
		jedisPool = new JedisPool(poolConfig, host, port);
	}
	
	public static Jedis getJedis() {
		return jedisPool.getResource();
	}
	
}
