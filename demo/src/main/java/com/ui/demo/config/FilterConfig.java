//package com.ui.demo.config;
//
//import com.ui.demo.filter.CorsFilter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class FilterConfig {
//
//    @Bean
//    public FilterRegistrationBean<CorsFilter> corsFilterRegistration(CorsFilter filter){
//        FilterRegistrationBean<CorsFilter> registration = new FilterRegistrationBean<>(filter);
//        registration.setOrder(0); // highest precedence
//        return registration;
//    }
//}
