package co.com.neoris.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * autor: Gilmar Arley Gonzalez 
 * El codigo que se presenta acontinuación fue creado como solución a una prueba tecnica de desarrollo
 * para aplicar al cargo de desarrollador java.
 * 
 * 
 * Nivel de dificultad puntuado 1 a 10 para DEV semisenior: 8.0.
 * Tiempo de desarrollo estimado : 28 horas habiles.
 * Tiempo de desarrollo ejecutado: 36 horas habiles.
 * 
 * 
 * Mejoras pendientes por parte del desarrollador:
 * Alojar la base de datos en un servidor remoto como PhpMyAdmin
 * Implementar la persistencia de entidades usando Herencia
 * Implementar pruebas unitarias.
 * 
 * 
 * */

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
