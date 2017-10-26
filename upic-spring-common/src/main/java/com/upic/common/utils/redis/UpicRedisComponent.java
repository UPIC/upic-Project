package com.upic.common.utils.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * @author DTZ
 */
@Component
public class UpicRedisComponent {
	private static final long INIT = 0;
	private static final long INCREMENT = 1;
	private static final long DECREMENT = -1;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	public void set(String key, String value) {
		ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();
		if (!this.stringRedisTemplate.hasKey(key)) {
			ops.set(key, value);
			System.out.println("set key success");
		} else {
			// 存在则打印之前的value值
			System.out.println("this key = " + ops.get(key));
		}
	}

	public String get(String key) {
		return this.stringRedisTemplate.opsForValue().get(key);
	}

	public void del(String key) {
		this.stringRedisTemplate.delete(key);
	}

	/**
	 * 初始化
	 * 
	 * @param key
	 * @return
	 */
	public Long init(String key) {
		try {
			if (get(key) != null) {
				throw new Exception();
			}
			return stringRedisTemplate.opsForValue().increment(key, INIT);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return -1L;
		}
	}

	/**
	 * 添加数值
	 * 
	 * @param key
	 * @return
	 */
	public Long increment(String key) {
		return increment(key, INCREMENT);
	}

	public Long increment(String key, Long addSize) {
		try {
			if (get(key) == null) {
				throw new Exception();
			}
			return stringRedisTemplate.opsForValue().increment(key, addSize);
		} catch (Exception e) {
			return -1L;
		}
	}

	public Long decrement(String key) {
		return decrement(key, DECREMENT);
	}

	public Long decrement(String key, Long deleteSize) {
		return stringRedisTemplate.opsForValue().increment(key, deleteSize);
	}
	
	public boolean putIfAbsent(String key,String hashKey,String value) {
		return stringRedisTemplate.opsForHash().putIfAbsent(key,hashKey,value);
	}
	
}
