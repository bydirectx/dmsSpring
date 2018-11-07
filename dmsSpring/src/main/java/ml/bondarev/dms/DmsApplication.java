package ml.bondarev.dms;

import ml.bondarev.dms.config.JpaConfig;
import ml.bondarev.dms.config.MultipartConfig;
import ml.bondarev.dms.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DmsApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DmsApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(new Class<?>[]{MultipartConfig.class, SwaggerConfig.class, JpaConfig.class, DmsApplication.class}, args);
    }
}