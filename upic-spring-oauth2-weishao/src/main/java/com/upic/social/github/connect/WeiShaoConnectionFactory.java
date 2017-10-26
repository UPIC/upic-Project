/**
 * 
 */
package com.upic.social.github.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

import com.upic.social.github.api.WeiShao;

/**
 * @author DTZ
 *
 */
public class WeiShaoConnectionFactory extends OAuth2ConnectionFactory<WeiShao> {
	
	/**
	 * @param appId
	 * @param appSecret
	 */
	public WeiShaoConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new WeiShaoServiceProvider(appId, appSecret), new WeiShaoAdapter());
	}
	
	/**
	 * 由于微信的openId是和accessToken一起返回的，所以在这里直接根据accessToken设置providerUserId即可，不用像QQ那样通过QQAdapter来获取
	 */
	@Override
	protected String extractProviderUserId(AccessGrant accessGrant) {
//		if(accessGrant instanceof GithubAccessGrant) {
//			return ((GithubAccessGrant)accessGrant).getOpenId();
//		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.social.connect.support.OAuth2ConnectionFactory#createConnection(org.springframework.social.oauth2.AccessGrant)
	 */
	public Connection<WeiShao> createConnection(AccessGrant accessGrant) {
		return new OAuth2Connection<WeiShao>(getProviderId(), null, accessGrant.getAccessToken(),
				accessGrant.getRefreshToken(), accessGrant.getExpireTime(), getOAuth2ServiceProvider(), getApiAdapter());		
	}

	/* (non-Javadoc)
	 * @see org.springframework.social.connect.support.OAuth2ConnectionFactory#createConnection(org.springframework.social.connect.ConnectionData)
	 */
	public Connection<WeiShao> createConnection(ConnectionData data) {
		return new OAuth2Connection<WeiShao>(data, getOAuth2ServiceProvider(), getApiAdapter());
	}

	protected ApiAdapter<WeiShao> getApiAdapter() {
		return new WeiShaoAdapter();
	}
	
	private OAuth2ServiceProvider<WeiShao> getOAuth2ServiceProvider() {
		return (OAuth2ServiceProvider<WeiShao>) getServiceProvider();
	}

	
}