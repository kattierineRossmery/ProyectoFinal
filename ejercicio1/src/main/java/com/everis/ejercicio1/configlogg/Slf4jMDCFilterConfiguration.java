package com.everis.ejercicio1.configlogg;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "configlogg.slf4jfilter")
public class Slf4jMDCFilterConfiguration {

	public static final String DEFAULT_RESPONSE_TOKEN_HEADER = "Response_Token";
    public static final String DEFAULT_MDC_UUID_TOKEN_KEY = "Slf4jMdcFilter.UUID";
	public static final String DEFAULT_MDC_CLIENT_IP_KEY = "Slf4jMdcFilter.ClientIP";

    private String responseHeader = DEFAULT_RESPONSE_TOKEN_HEADER;
    private String mdcTokenKey = DEFAULT_MDC_UUID_TOKEN_KEY;
    private String mdcClientIpKey = DEFAULT_MDC_CLIENT_IP_KEY;
    private String requestHeader = null;
  
	/*
	 * @Bean public FilterRegistrationBean servletRegistrationBean() { final
	 * FilterRegistrationBean registrationBean = new FilterRegistrationBean(); final
	 * Slf4jMDCFilter log4jMDCFilterFilter = new Slf4jMDCFilter(responseHeader,
	 * mdcTokenKey, requestHeader);
	 * registrationBean.setFilter(log4jMDCFilterFilter);
	 * registrationBean.setOrder(2); return registrationBean; }
	 */
	
}
