/**
 * 
 */
package com.upic.social.github.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;

import com.upic.social.github.api.WeiShao;
import com.upic.social.github.api.impl.WeiShaoTemplate;


/**
 * @author DTZ
 *
 */
public class WeiShaoServiceProvider extends AbstractOAuth2ServiceProvider<WeiShao> {
	
	/**
	 * 微信获取授权码的url
	 */
	private static final String URL_AUTHORIZE = "https://api.weishao.com.cn/oauth/authorize";
	/**
	 * 微信获取accessToken的url
	 */
	private static final String URL_ACCESS_TOKEN = "https://api.weishao.com.cn/oauth/token";

	/**
	 * @param appId
	 * @param appSecret
	 */
	public WeiShaoServiceProvider(String appId, String appSecret) {
		super(getOAuth2Template(appId, appSecret));
	}

	/**
	 * @param appId
	 * @param appSecret
	 * @return
	 */
	private static OAuth2Operations getOAuth2Template(String appId, String appSecret) {
		OAuth2Template oAuth2Template = new OAuth2Template(appId, appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN);
		oAuth2Template.setUseParametersForClientAuthentication(true);
		return oAuth2Template;
	}

	/* (non-Javadoc)
	 * @see org.springframework.social.oauth2.AbstractOAuth2ServiceProvider#getApi(java.lang.String)
	 */
	@Override
	public WeiShao getApi(String accessToken) {
		return new WeiShaoTemplate(accessToken);
	}

}
