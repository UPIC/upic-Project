package com.upic.social.github.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;

import com.upic.social.github.api.WeiShao;
import com.upic.social.github.api.WeiShaoProfile;

public class WeiShaoAdapter implements ApiAdapter<WeiShao>{

	@Override
	public boolean test(WeiShao api) {
		return true;
	}

	@Override
	public void setConnectionValues(WeiShao api, ConnectionValues values) {
		WeiShaoProfile profile = api.getWeiShaoProfile();
		values.setProviderUserId(profile.getStudent_number());
		values.setDisplayName(profile.getName());
		values.setImageUrl(profile.getPhoto_live());
	}

	@Override
	public UserProfile fetchUserProfile(WeiShao api) {
		WeiShaoProfile userProfile = (WeiShaoProfile) api.getWeiShaoProfile();
		return new UserProfileBuilder().setName(userProfile.getName()).build();
	}

	@Override
	public void updateStatus(WeiShao api, String message) {
		
	}

}
