package com.upic.common.utils.redis.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/** 
 *  
 * @author vic 
 * @desc redis service 
 */  
public interface IRedisService {  
      
    public boolean set(String key, String value);  
      
    public String get(String key);  
      
    public boolean expire(String key,long expire);  
      
    public <T> boolean setList(String key ,List<T> list);  
      
    public <T> List<T> getList(String key,Class<T> clz);  
      
    public long lpush(String key,Object obj);  
      
    public long rpush(String key,Object obj);  
      
    public String lpop(String key);  
    
    public void put(String redisKey,String key, Object doamin, long expire);
      
    public Object getObj(String redisKey,String key);
    
    
    
    
    public void del(String key);
    
    public Long init(String key);
    
    public Long increment(String key);
    
    public Long increment(String key, Long addSize);
    
    public Long decrement(String key);
    
    public Long decrement(String key, Long deleteSize);
    
    public boolean putIfAbsent(String key, String hashKey, String value);
    
    public boolean deletByKey(String key);
    
    public boolean deletByKey(Collection<String> keys);
    
    public Set<Object> keys(String key);
    
    public boolean deletByHashKey(String key, String hashKey);
    
    public void set(final byte[] key, final byte[] value, final long liveTime);
    
    public void set(String key, String value, long liveTime);
}  