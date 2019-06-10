package com.ksquare.sso.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
    @Override
    protected Class<?>[] getRootConfigClasses() {
    	return null;
    }
  
    @Override
    protected Class<?>[] getServletConfigClasses() {
    	return new Class<?>[] { WebDispatcherConfig.class};
    }
  
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
