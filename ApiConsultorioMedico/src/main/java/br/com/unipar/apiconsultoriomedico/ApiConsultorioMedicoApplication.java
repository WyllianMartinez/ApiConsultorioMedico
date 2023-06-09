package br.com.unipar.apiconsultoriomedico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ApiConsultorioMedicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiConsultorioMedicoApplication.class, args);
	}


	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(geraInfoSwsagger())
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.unipar.apiconsultoriomedico"))
				.paths(PathSelectors.any())
				.build();
	}

	public ApiInfo geraInfoSwsagger() {

		return new ApiInfo("Trabalho - Spring Boot",
				"Terceiro Ano de ADS",
				"", null, "Felipe leite"+" Wyllian Martinez", null, null);
	}

}
