package com.upic.connect.view;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.UserProfile;
import org.springframework.web.servlet.view.AbstractView;

public class UpicConnectionStatusView extends AbstractView {
	
	private final String providerId;
	
	private final String providerDisplayName;

	public UpicConnectionStatusView(String providerId, String providerDisplayName) {
		this.providerId = providerId;
		this.providerDisplayName = providerDisplayName;
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		generateConnectionViewHtml(getProfileIfConnected(model),response);
	}

	private void generateConnectionViewHtml(UserProfile profile, HttpServletResponse response) throws IOException {
		StringBuilder builder = new StringBuilder();
		if (profile == null) {
			builder.append("对不起，绑定失败，请重试！");
			response.setHeader("Content-Type", "text/html");
			response.getWriter().write(builder.toString());
		} else {
			response.sendRedirect("/?username="+profile.getUsername());
		}
	}

	private UserProfile getProfileIfConnected(Map<String, Object> model) {
		@SuppressWarnings("unchecked")
		List<Connection<?>> connections = (List<Connection<?>>) model.get("connections");
		if (connections != null) {
			for (Connection<?> connection : connections) {
				if (connection.getKey().getProviderId().equals(providerId)) {
					return connection.fetchUserProfile();
				}
			}
		}
		return null;
	}
	
}