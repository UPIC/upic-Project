package com.upic.common.utils.redis.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author vic
 * @desc resdis service
 * 
 */
@Service
public class RedisServiceImpl implements IRedisService {
	private static final long INIT = 0;
	private static final long INCREMENT = 1;
	private static final long DECREMENT = -1;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
//	@Autowired
//	private RedisTemplate<String, String> redisTemplate;
	@Resource
	protected HashOperations<String, String, Object> hashOperations;

	@Override
	public boolean set(final String key, final String value) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				connection.set(serializer.serialize(key), serializer.serialize(value));
				return true;
			}
		});
		return result;
	}

	public String get(final String key) {
		String result = redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] value = connection.get(serializer.serialize(key));
				return serializer.deserialize(value);
			}
		});
		return result;
	}

	@Override
	public boolean expire(final String key, long expire) {
		return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
	}

	@Override
	public <T> boolean setList(String key, List<T> list) {
		String value = JSONArray.toJSONString(list);
		return set(key, value);
	}

	@Override
	public <T> List<T> getList(String key, Class<T> clz) {
		String json = get(key);
		if (json != null) {
			List<T> list = JSONArray.parseArray(json, clz);
			return list;
		}
		return null;
	}

	@Override
	public long lpush(final String key, Object obj) {

		final String value = JSONObject.toJSONString(obj);
		long result = redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				long count = connection.lPush(serializer.serialize(key), serializer.serialize(value));
				return count;
			}
		});
		return result;
	}

	@Override
	public long rpush(final String key, Object obj) {
		final String value = JSONObject.toJSONString(obj);
		long result = redisTemplate.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				long count = connection.rPush(serializer.serialize(key), serializer.serialize(value));
				return count;
			}
		});
		return result;
	}

	@Override
	public String lpop(final String key) {
		String result = redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
				byte[] res = connection.lPop(serializer.serialize(key));
				return serializer.deserialize(res);
			}
		});
		return result;
	}

	@Override
	public void put(String redisKey, String key, Object doamin, long expire) {
		hashOperations.put(redisKey, key, doamin);
		if (expire != -1) {
			redisTemplate.expire(key, expire, TimeUnit.SECONDS);
		}
	}

	@Override
	public Object getObj(String redisKey, String key) {
		return hashOperations.get(redisKey, key);
	}

	public void del(String key) {
		this.redisTemplate.delete(key);
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
			return redisTemplate.opsForValue().increment(key, INIT);
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
			return redisTemplate.opsForValue().increment(key, addSize);
		} catch (Exception e) {
			return -1L;
		}
	}

	public Long decrement(String key) {
		return decrement(key, DECREMENT);
	}

	public Long decrement(String key, Long deleteSize) {
		return redisTemplate.opsForValue().increment(key, deleteSize);
	}

	public boolean putIfAbsent(String key, String hashKey, String value) {
		return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
	}

	public boolean deletByKey(String key) {
		try {
			redisTemplate.delete(key);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean deletByKey(Collection<String> keys) {
		try {
			redisTemplate.delete(keys);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public Set<Object> keys(String key) {
		return redisTemplate.opsForHash().keys(key);
	}

	public boolean deletByHashKey(String key, String hashKey) {
		try {
			redisTemplate.opsForHash().delete(key, hashKey);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

//	public void set(String key, String value, long expir) {
//		ValueOperations<String, Object> ops = this.redisTemplate.opsForValue();
//		if (!this.redisTemplate.hasKey(key)) {
//			ops.set(key, value, expir);
////			System.out.println("set key success");
//		} else {
//			// 存在则打印之前的value值
////			System.out.println("this key = " + ops.get(key));
//		}
//	}
	 /**
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(final byte[] key, final byte[] value, final long liveTime) {
        redisTemplate.execute(new RedisCallback() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                if (liveTime > 0) {
                    connection.expire(key, liveTime);
                }
                return 1L;
            }
        });
    }

    /**
     * @param key
     * @param value
     * @param liveTime
     */
    public void set(String key, String value, long liveTime) {
        this.set(key.getBytes(), value.getBytes(), liveTime);
    }
}