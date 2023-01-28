package is.hi.hbv501g.hugbunadarverkefni1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * This is the main class. It uses Spring Boot to launch the application and can be used to
 * modify various settings and properties for it. Here the @EnableJpaRepositories
 * annotation tells JPA to implement repository interfaces automatically.
 *
 * @author Ólafur Pálsson, Brynjólfur Steingrímsson
 */

@SpringBootApplication
// @EnableJpaRepositories
public class Hugbunadarverkefni1Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
        return applicationBuilder.sources(Hugbunadarverkefni1Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Hugbunadarverkefni1Application.class, args);
    }
}
