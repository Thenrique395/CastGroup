package br.com.CastGroup.ValidadorArquivo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import org.apache.http.HttpStatus;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import br.com.CastGroup.ValidadorArquivo.domain.File;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileTest {

	

	@Mock
	protected File file;

	@Value("${local.server.port}")
	protected int port;

	@Before
	public void setUp() {
		RestAssured.port = port;
	}

	@Test
	public void a() {
		given().get("/v1/diff/").then().statusCode(HttpStatus.SC_OK).body("Error",
				containsString("Nenhum documento left e right encontrado"));
	}

	@Test
	public void b() {
		File file = new File();
		file.setNameFile("RGllZ28gQWFzaXMgQ2FydmFsaG8gcGVyZWlyYQ==");
		given().contentType(ContentType.JSON).pathParam("id", 12345).body(file).post("/v1/diff/{id}/right").then()
				.statusCode(HttpStatus.SC_OK);
		given().get("/v1/diff/").then().statusCode(HttpStatus.SC_OK).body("Error",
				containsString("Nenhum documento left encontrado"));
	}

	@Test
	public void c() {
		File file = new File();
		file.setNameFile("RGllZ28gQWFzaXMgQ2FydmFsaG8gcGVyZWlyYQ==");
		given().contentType(ContentType.JSON).pathParam("id", 12345).body(file).post("/v1/diff/{id}/left").then()
				.statusCode(HttpStatus.SC_OK);
		given().get("/v1/diff/").then().statusCode(HttpStatus.SC_OK).body("Error",
				containsString("Nenhum documento right encontrado"));
	}

	@Test
	public void d() {
		File file = new File();
		file.setNameFile("RGllZ28gQXNzaXMgQ2FydmFsaG8gUGVyZWlyYQ==");
		given().contentType(ContentType.JSON).pathParam("id", 12345).body(file).post("/v1/diff/{id}/left").then()
				.statusCode(HttpStatus.SC_OK);

		file.setNameFile("RGllZ28gQXNzaXMgQ2FydmFsaG8gUGVyZWly");
		given().contentType(ContentType.JSON).pathParam("id", 12345).body(file).post("/v1/diff/{id}/right").then()
				.statusCode(HttpStatus.SC_OK);
		given().get("/v1/diff/").then().statusCode(HttpStatus.SC_OK).body("Error",
				containsString("Documentos 12345 e 12345 com tamanhos diferentes"));
	}

	@Test
	public void e() {
		File file = new File();
		file.setNameFile("RGllZ28gQXNzaXMgQ2FydmFsaG8gUGVyZWlyYQ==");
		given().contentType(ContentType.JSON).pathParam("id", 12345).body(file).post("/v1/diff/{id}/left").then()
				.statusCode(HttpStatus.SC_OK);

		file.setNameFile("RGllZ28gQXNzaXMgQ2FydmFsaG8gUGVyZWlyYQ==");
		given().contentType(ContentType.JSON).pathParam("id", 12345).body(file).post("/v1/diff/{id}/right").then()
				.statusCode(HttpStatus.SC_OK);
		given().get("/v1/diff/").then().statusCode(HttpStatus.SC_OK).body("Success",
				containsString("Documentos 12345 e 12345 idênticos"));
	}
	
}
