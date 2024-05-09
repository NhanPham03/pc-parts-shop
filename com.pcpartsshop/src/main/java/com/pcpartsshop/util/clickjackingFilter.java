package com.pcpartsshop.util;


import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class clickjackingFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {


    }


    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) resp;
        response.addHeader("X-Frame-Options", "DENY");
        chain.doFilter(req, resp);
    }


    @Override
    public void destroy() {


    }
}
