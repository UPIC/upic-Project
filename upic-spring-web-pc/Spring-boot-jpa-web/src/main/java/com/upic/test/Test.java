package com.upic.test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class Test {

public static void main(String[] args) {
	configureFor("127.0.0.1", 8080);
//		stubFor(any(urlPathEqualTo("/everything"))
//			  .withHeader("Accept", containing("xml"))
//			  .withCookie("session", matching(".*12345.*"))
//			  .withQueryParam("search_term", equalTo("WireMock"))
//			  .withBasicAuth("jeff@example.com", "jeffteenjefftyjeff")
//			  .withRequestBody(equalToXml("<search-results />"))
//			  .withRequestBody(matchingXPath("//search-results"))
//			  .willReturn(aResponse()));
	stubFor(get(urlPathEqualTo("/test")).willReturn(okJson("{name:dongshuai}")));
}
}
