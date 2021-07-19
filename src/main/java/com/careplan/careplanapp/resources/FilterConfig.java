/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.careplan.careplanapp.resources;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author Tiago
 */
public class FilterConfig {
    
private FilterConfig filterConfig;

public void init(FilterConfig filterConfig) {
    this.filterConfig = filterConfig;
}

public void destroy() {
    this.filterConfig = null;
}

public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws UnsupportedEncodingException, IOException, ServletException {
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8");
    chain.doFilter(request, response);
}
}
