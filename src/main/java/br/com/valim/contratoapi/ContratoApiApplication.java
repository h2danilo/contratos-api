package br.com.valim.contratoapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Contratos API", version = "v1", description = "Api do sistema Contratos"))
@RequiredArgsConstructor
public class ContratoApiApplication  extends SpringBootServletInitializer implements CommandLineRunner {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ContratoApiApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ContratoApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO document why this method is empty
	}
}
