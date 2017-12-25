/**
 * 
 */
package com.upic.batch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.upic.common.utils.redis.service.IRedisService;
import com.upic.weixin.utils.JSSDKUtil;

/**
 * @author DTZ
 *
 */
@Component
public class WeixinJSSDKTaskImpl implements WeixinJSSDKTask {
	@Autowired
	private IRedisService redisService;
	
	@Autowired
	private JSSDKUtil jSSDKUtil;
	@Override
	public void doTask() {
		List<String>list=new ArrayList<String>();
//		String string = redisService.get("AccessToken");
		jSSDKUtil.AccessToken=jSSDKUtil.getAccessToken();
		jSSDKUtil.Ticket=jSSDKUtil.getTicket(jSSDKUtil.AccessToken);
		list.add(jSSDKUtil.AccessToken);
		list.add(jSSDKUtil.Ticket);
		boolean set = redisService.setList("nonce", list);
		System.out.println("修改了setTicket："+list.toString()+set);
	}

}
