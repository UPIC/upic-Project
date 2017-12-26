/**
 * 
 */
package com.upic.social.weixin.config;

import java.nio.charset.Charset;
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

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author DTZ
 *
 */
public class WeixinOAuth2Template extends OAuth2Template {

	private String clientId;

	private String clientSecret;

	private String accessTokenUrl;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public WeixinOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
		super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.accessTokenUrl = accessTokenUrl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.social.oauth2.OAuth2Template#exchangeForAccess(java.lang.
	 * String, java.lang.String, org.springframework.util.MultiValueMap)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri,
			MultiValueMap<String, String> parameters) {

		StringBuilder authUrl = new StringBuilder(accessTokenUrl);

		authUrl.append("?appid=" + clientId);
		authUrl.append("&secret=" + clientSecret);
		authUrl.append("&code=" + authorizationCode);
		authUrl.append("&grant_type=authorization_code");
		authUrl.append("&redirect_uri=" + redirectUri);

		logger.info("获取access_token, 请求URL: " + authUrl.toString());

		String response = getRestTemplate().getForObject(authUrl.toString(), String.class);

		logger.info("获取access_token, 响应内容: " + response);

		Map<String, Object> result = null;
		try {
			result = new ObjectMapper().readValue(response, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		WeixinAccessGrant accessToken = new WeixinAccessGrant(MapUtils.getString(result, "access_token"),
				MapUtils.getString(result, "scope"), MapUtils.getString(result, "refresh_token"),
				MapUtils.getLong(result, "expires_in"));

		accessToken.setOpenId(MapUtils.getString(result, "openid"));

		return accessToken;
	}

	/**
	 * 构建获取授权码的请求。也就是引导用户跳转到微信的地址。 snsapi_base snsapi_userinfo snsapi_login(需要其他授权)
	 * 
	 * @exception 1
	 *                首次使用 scope=snsapi_base 进行网页授权
	 * 
	 *                2 拿到 code 后调用接口
	 *                https://api.weixin.qq.com/sns/oauth2/access_token?appid={0}&secret={1}&code={2}&grant_type=authorization_code
	 * 
	 *                3 根据上一步获取的 openid 和 access_token 调用接口
	 *                https://api.weixin.qq.com/sns/userinfo?access_token={0}&openid={1}&lang=zh_CN
	 *                获取用户基本信息
	 * 
	 *                4 获取失败：返回 {"errcode":48001,"errmsg":"api unauthorized, hints:
	 *                [ req_id: 1QoCla0699ns81 ]"}
	 */
	public String buildAuthenticateUrl(OAuth2Parameters parameters) {
		String url = super.buildAuthenticateUrl(parameters);
		url = url + "&appid=" + clientId + "&scope=snsapi_userinfo";
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