package com.upic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.upic.common.utils.redis.UpicRedisComponent;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UpicSpringParent1Application.class)
public class UpicSpringParent1ApplicationTests {
	@Autowired
    private UpicRedisComponent redisComponent;
	  @Test
	    public void testInit() {
//	    	System.out.println("result:"+redisComponent.init("1001"));
	    	redisComponent.set("dtz", "superman");
	    }

}
