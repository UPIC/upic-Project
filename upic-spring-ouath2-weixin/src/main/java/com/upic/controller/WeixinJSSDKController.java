package com.upic.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upic.common.utils.redis.service.IRedisService;
import com.upic.weixin.po.WeixinJSSDKEnity;
import com.upic.weixin.utils.JSSDKUtil;

/**
 * 获取详情
 * 
 * @author dtz
 *
 */
@RestController
public class WeixinJSSDKController {

	@Autowired
	private IRedisService redisService;

	@Autowired
	private JSSDKUtil jssdkUtils;

	@GetMapping("getJSSDKDetials")
	public WeixinJSSDKEnity getJSSDKDetials(HttpServletRequest request) {
		List<String> list = redisService.getList("nonce", String.class);

		String noncestr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);// 随机字符串
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);// 时间戳

		// 4、获取url
		String url = request.getParameter("url");
		// 5、将参数排序并拼接字符串
		String str = "jsapi_ticket=" + list.get(1) + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url="
				+ url;
		// 6、将字符串进行sha1加密
		String signature = jssdkUtils.SHA1(str);

		return new WeixinJSSDKEnity(jssdkUtils.getAppId(), timestamp, noncestr, signature);
	}
}
