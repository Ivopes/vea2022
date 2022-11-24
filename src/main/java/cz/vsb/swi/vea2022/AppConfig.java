package cz.vsb.swi.vea2022;

import cz.vsb.swi.vea2022.utilities.converter.ProductConverter;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class AppConfig implements WebMvcConfigurer {

//	@Autowired
//	private LogingInterceptor logingInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(logingInterceptor);

	}
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new ProductConverter());
	}
}
