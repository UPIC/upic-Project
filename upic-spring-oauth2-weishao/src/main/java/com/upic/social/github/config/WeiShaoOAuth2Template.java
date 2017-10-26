/**
 * 
 */
package com.upic.social.github.config;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author DTZ
 *
 */
public class WeiShaoOAuth2Template extends OAuth2Template {
	
	private String clientId;
	
	private String clientSecret;

	private String accessTokenUrl;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	public WeiShaoOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
		super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.accessTokenUrl = accessTokenUrl;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.social.oauth2.OAuth2Template#exchangeForAccess(java.lang.String, java.lang.String, org.springframework.util.MultiValueMap)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri,
			MultiValueMap<String, String> parameters) {
		
		StringBuilder authUrl = new StringBuilder(accessTokenUrl);
		
		
		Map<String,Object> data=new HashMap<String,Object>();
		data.put("app_key", clientId);
		data.put("grant_type", "client_credentials");
		data.put("app_secret", clientSecret);
		data.put("scope", "base_api");
		JSONObject jsonObj=new JSONObject(data);
		logger.info("获取access_token, 请求URL: "+authUrl.toString());
		
//		String response = getRestTemplate().getForObject(authUrl.toString(), String.class);
		String response = getRestTemplate().postForObject(authUrl.toString(), jsonObj, String.class);
		
		logger.info("获取access_token, 响应内容: "+response);
		
		Map<String, Object> result = null;
		try {
			result = new ObjectMapper().readValue(response, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		AccessGrant accessToken = new AccessGrant(
				MapUtils.getString(result, "access_token"), 
				MapUtils.getString(result, "scope"), 
				MapUtils.getString(result, "refresh_token"), 
				MapUtils.getLong(result, "expires_in"));
		return accessToken;
	}
	
	/**
	 * 构建获取授权码的请求。也就是引导用户跳转到微信的地址。
	 * snsapi_base
	 * snsapi_userinfo
	 * snsapi_login(需要其他授权)
	 */
	public String buildAuthenticateUrl(OAuth2Parameters parameters) {
		String url = super.buildAuthenticateUrl(parameters);
		url = url + "&client_id="+clientId+"&scope=snsapi_base";
		return url;
	}
	
	public String buildAuthorizeUrl(OAuth2Parameters parameters) {
		return buildAuthenticateUrl(parameters);
	}
	
	/**
	 * 微信返回的contentType是html/text，添加相应的HttpMessageConverter来处理。
	 */
	protected RestTemplate createRestTemplate() {
		RestTemplate restTemplate = super.createRestTemplate();
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return restTemplate;
	}

}