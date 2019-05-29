package com.everis.ejercicio1.configlogg;

import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Data
@EqualsAndHashCode(callSuper = false)
@Component
public class Slf4jMdcFilter extends OncePerRequestFilter{

	Logger log = LoggerFactory.getLogger(this.getClass());
	  private final String responseHeader;
	  private final String mdcTokenKey;
	  private final String mdcClientIpKey;
	  private final String requestHeader;

	  /**
	   * Constructor vacío del filtro.
	   */
	  public Slf4jMdcFilter() {
	    responseHeader = Slf4jMDCFilterConfiguration.DEFAULT_RESPONSE_TOKEN_HEADER;
	    mdcTokenKey = Slf4jMDCFilterConfiguration.DEFAULT_MDC_UUID_TOKEN_KEY;
	    mdcClientIpKey = Slf4jMDCFilterConfiguration.DEFAULT_MDC_CLIENT_IP_KEY;
	    requestHeader = null;
	  }

	  /**
	   * Constructor del filtro.
	   * 
	   * @param responseHeader responseHeader
	   * @param mdcTokenKey    mdcTokenKey
	   * @param mdcClientIpKey mdcClientIpKey
	   * @param requestHeader  requestHeader
	   */
	  public Slf4jMdcFilter(final String responseHeader, final String mdcTokenKey,
	      final String mdcClientIpKey, final String requestHeader) {
	    this.responseHeader = responseHeader;
	    this.mdcTokenKey = mdcTokenKey;
	    this.mdcClientIpKey = mdcClientIpKey;
	    this.requestHeader = requestHeader;
	  }

	  @Override
	  protected void doFilterInternal(final HttpServletRequest request,
	      final HttpServletResponse response, final FilterChain chain)
	      throws java.io.IOException, ServletException {
	    try {
	      final String token;
	      if (!StringUtils.isEmpty(requestHeader)
	          && !StringUtils.isEmpty(request.getHeader(requestHeader))) {
	        token = request.getHeader(requestHeader);
	        log.info("requestHeader no está vacío.");
	      } else {
	        token = UUID.randomUUID().toString().toUpperCase().replace("-", "");
	        log.info("Sí está vacío.");
	      }
	      MDC.put(mdcTokenKey, token);
	      if (!StringUtils.isEmpty(responseHeader)) {
	        response.addHeader(responseHeader, token);
	        log.info("responseHeader no está vacío.");
	      }
	      chain.doFilter(request, response);
	    } finally {
	      MDC.remove(mdcTokenKey);
	    }
	  }

}
