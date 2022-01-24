package by.itacademy.javaenterprise.lepnikau.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"by.itacademy.javaenterprise.lepnikau.web"})
public class SpringWebConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver urlBasedViewResolver() {
        return new InternalResourceViewResolver("/WEB-INF/pages/", ".jsp");
    }

}
