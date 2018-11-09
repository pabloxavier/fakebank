package br.com.fakebank;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
			//addMapping recebe um pattern designando os paths aos quais estarão aplicadas
			//as configurações aqui indicadas.
			.addMapping("/**")
			//allowedMethods define a lista de métodos permitidos que uma solicitação pode enviar
			//Aqui estamos especificando os principais métodos,
			//mas poderíamos usar "*" para liberar todos os métodos
			.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT")
			//allowedHeaders define a lista de headers que uma solicitação pode enviar
			//no caso, estamos passando "*" para permitir o recebimento de todos os headers
			.allowedHeaders("*")
			//magAge indica quantos segundos a resposta de uma request pode ser armazenada pelo Client.
			//3600 segundos = 1 hora (Valor default = 1800 segundos)
			.maxAge(3600);
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		PageableHandlerMethodArgumentResolver pageableResolver = new PageableHandlerMethodArgumentResolver();
		pageableResolver.setFallbackPageable(PageRequest.of(0, 3));
		argumentResolvers.add(pageableResolver);
	}
}
