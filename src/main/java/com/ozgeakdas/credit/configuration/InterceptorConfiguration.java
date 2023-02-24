package com.ozgeakdas.credit.configuration;

import com.ozgeakdas.credit.aop.CreditInterceptor;
import com.ozgeakdas.credit.aop.CustomerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration  implements WebMvcConfigurer {
    private final CustomerInterceptor customerInterceptor;
    private final CreditInterceptor creditInterceptor;

    @Autowired
    public InterceptorConfiguration(CustomerInterceptor customerInterceptor, CreditInterceptor creditInterceptor) {
        this.customerInterceptor = customerInterceptor;
        this.creditInterceptor = creditInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customerInterceptor).addPathPatterns("/api/customer/*");
        registry.addInterceptor(creditInterceptor).addPathPatterns("/api/credit/*");
    }
}
