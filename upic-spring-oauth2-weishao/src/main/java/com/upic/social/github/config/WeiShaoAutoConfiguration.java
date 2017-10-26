package com.upic.social.github.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.boot.autoconfigure.social.SocialProperties;
import org.springframework.boot.autoconfigure.social.SocialWebAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.connect.web.GenericConnectionStatusView;

import com.upic.connect.view.UpicConnectionStatusView;
import com.upic.social.github.api.WeiShao;
import com.upic.social.github.connect.WeiShaoConnectionFactory;

@Configuration
@ConditionalOnClass({ SocialConfigurerAdapter.class, OAuth2ConnectionFactory.class })
@AutoConfigureBefore(SocialWebAutoConfiguration.class)
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class WeiShaoAutoConfiguration {
	@Configuration
	@EnableSocial
	@EnableConfigurationProperties(WeiShaoProperties.class)
	@ConditionalOnWebApplication
	@ConditionalOnProperty(prefix = "upic.social.weishao", name = "app-id")
	protected static class GithubConfigurerAdapter extends SocialAutoConfigurerAdapter {

		private final WeiShaoProperties properties;

		protected GithubConfigurerAdapter(WeiShaoProperties properties) {
			this.properties = properties;
		}

		/**
		 * @param repository
		 * @return
		 */
		@Bean
		@Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
		public WeiShao github(ConnectionRepository repository) {
			Connection<WeiShao> connection = repository.findPrimaryConnection(WeiShao.class);
			return connection != null ? connection.getApi() : null;
		}
		
		/* (non-Javadoc)
		 * @see org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter#createConnectionFactory()
		 */
		@Override
		protected ConnectionFactory<?> createConnectionFactory() {
			return new WeiShaoConnectionFactory(getProviderId(), this.properties.getAppId(), this.properties.getAppSecret());
		}

		/**
		 * @return
		 */
		@Bean(name = { "connect/weishaoConnect", "connect/weishaoConnected" })
		@ConditionalOnProperty(prefix = "spring.social", name = "auto-connection-views")
		public UpicConnectionStatusView GithubConnectView() {
			return new UpicConnectionStatusView(getProviderId(), "WeiShao");
		}
		
		/**
		 * @return
		 */
		@Bean(name = { "connect/status"})
		@ConditionalOnProperty(prefix = "spring.social", name = "auto-connection-views")
		public JsonConnectView jsonConnectView() {
			return new JsonConnectView();
		}

		/**
		 * @return
		 */
		protected String getProviderId() {
			String providerId = properties.getProviderId();
			if (StringUtils.isBlank(providerId)) {
				providerId = "weishao";
			}
			return providerId;
		}

	}

}
