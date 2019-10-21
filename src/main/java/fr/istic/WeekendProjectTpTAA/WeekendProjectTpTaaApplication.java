package fr.istic.WeekendProjectTpTAA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableSwagger2
/*@ComponentScan({"fr.istic.WeekendProjectTpTAA.model"})
@EntityScan({"fr.istic.WeekendProjectTpTAA"})
@EnableJpaRepositories({"fr.istic.WeekendProjectTpTAA.repository"})*/
public class WeekendProjectTpTaaApplication {

	/*@Override protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(WeekendProjectTpTaaApplication.class);
	}*/

	public static void main(String[] args) {
		SpringApplication.run(WeekendProjectTpTaaApplication.class, args);
	}

}
