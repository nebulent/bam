package com.netflexitysolutions.software.bam.ui;

import java.util.ArrayList;
import java.util.Collection;

import netflexity.schema.software.bam.types._1.UserType;
import netflexity.ws.software.bam.services._1_0.BAMInternal;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

public class BamAuthenticationProvider implements AuthenticationProvider {
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UserType user;
		try {
			netflexity.schema.software.bam.messages._1.Authentication request = new netflexity.schema.software.bam.messages._1.Authentication();
			request.setUserName(authentication.getName());
			request.setPassword((String)authentication.getCredentials());
			
			JaxWsProxyFactoryBean bean = new JaxWsProxyFactoryBean();
			bean.setAddress("http://localhost:9090/ws/BAMInternal");
			bean.setServiceClass(BAMInternal.class);
			BAMInternal bamInternal = (BAMInternal)bean.create();
			
			user = bamInternal.authenticate(request).getUser();
			System.out.println("user: " + ToStringBuilder.reflectionToString(user, ToStringStyle.MULTI_LINE_STYLE));
		} catch (Exception exc) {
			throw new BadCredentialsException("Bad Credentials");
		}
		if(user == null) {
			throw new BadCredentialsException("Bad Credentials");
		}
		Collection<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new GrantedAuthorityImpl("ROLE_USER"));
		return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), roles);
	}

	@Override
	public boolean supports(Class<? extends Object> arg0) {
		return true;
	}
}
