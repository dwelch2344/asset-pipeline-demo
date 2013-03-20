package co.ntier.example.asset.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import co.ntier.web.pipeline.core.ResourceCompiler;
import co.ntier.web.pipeline.impl.closure.ClosureResourceCompiler;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class WebConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ResourceCompiler rc(){
		return new ClosureResourceCompiler();
	}
	
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    // Add pass-through routes for static resource folders
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    	registry.addResourceHandler("/gen/**").addResourceLocations("/gen/");
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	registry.addViewController("login").setViewName("login");
    }
    
    // Add any request interceptors. Useful place for storing site-specific information (like the site name).
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	registry.addWebRequestInterceptor(new WebRequestInterceptor() {
			
			@Override
			public void preHandle(WebRequest request) throws Exception {
				request.setAttribute("siteName", "Test Site", WebRequest.SCOPE_REQUEST);
			}
			
			@Override
			public void postHandle(WebRequest request, ModelMap model) throws Exception {}
			
			@Override
			public void afterCompletion(WebRequest request, Exception ex) throws Exception {}
		});
    }

    // Add MultipartResolver for file uploads
    /*
    @Bean
    public MultipartResolver multipartResolver(){
            return new CommonsMultipartResolver();
    }
    // */

    // Add Jackson Support for returning JSON
    /*
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
            MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
            ObjectMapper mapper = converter.getObjectMapper();

            SerializationConfig config = mapper.getSerializationConfig();
            config.setSerializationInclusion( Inclusion.NON_NULL );
            mapper.setSerializationConfig( config );

            converters.add(converter);
    }
    // */
}
