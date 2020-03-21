package br.com.CastGroup.ValidadorArquivo;

import br.com.CastGroup.ValidadorArquivo.domain.Arquivo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
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

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileTest {

    @Mock
    protected Arquivo arquivo;

    @Value("${local.server.port}")
    protected int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void carregarArquivoUm() {

    }

    @Test
    public void carregarArquivoDois() {

    }

    @Test
    public void validarArquivosUmeDois() {
        given().get("/v1/diff/").then().statusCode(HttpStatus.SC_OK).body("Error",  containsString("Nenhum documento left e right encontrado"));
    }

    @Test
    public void validarArquivoUm() {
        Arquivo file = new Arquivo();
        file.setNameFile("RGllZ28gQWFzaXMgQ2FydmFsaG8gcGVyZWlyYQ==");
        given().contentType(ContentType.JSON).pathParam("id", 12345).body(file).post("/v1/diff/{id}/right").then().statusCode(HttpStatus.SC_OK);
        given().get("/v1/diff/").then().statusCode(HttpStatus.SC_OK).body("Error", containsString("Nenhum documento left encontrado"));
    }

    @Test
    public void validarArquivoDois() {
        Arquivo file = new Arquivo();
        file.setNameFile("RGllZ28gQWFzaXMgQ2FydmFsaG8gcGVyZWlyYQ==");
        given().contentType(ContentType.JSON).pathParam("id", 12).body(file).post("/v1/diff/{id}/left").then().statusCode(HttpStatus.SC_OK);
        given().get("/v1/diff/").then().statusCode(HttpStatus.SC_OK).body("Error", containsString("Nenhum documento right encontrado"));
    }

    @Test
    public void validarArquivosIguais() {
        Arquivo left = new Arquivo();
        left.setNameFile("RGllZ28gQXNzaXMgQ2FydmFsaG8gUGVyZWlyYQ==");
        left.setId(1L);
        given().contentType(ContentType.JSON).pathParam("id", 12345).body(left).post("/v1/diff/{id}/left").then().statusCode(HttpStatus.SC_OK);

        Arquivo right = new Arquivo();
        left.setId(2L);
        right.setNameFile("RGllZ28gQXNzaXMgQ2FydmFsaG8gUGVyZWlyYQ==");
        given().contentType(ContentType.JSON).pathParam("id", 12345).body(right).post("/v1/diff/{id}/right").then().statusCode(HttpStatus.SC_OK);

        given().get("/v1/diff/").then().statusCode(HttpStatus.SC_OK).body("Success", containsString("Documentos 12345 e 12345 idênticos"));
    }

    @Test
    public void validarDiferente() {
        Arquivo file = new Arquivo();
        file.setNameFile("RGllZ28gQXNzaXMgQ2FydmFsaG8gUGVyZWlyYQ==");
        given().contentType(ContentType.JSON).pathParam("id", 12345).body(file).post("/v1/diff/{id}/left").then().statusCode(HttpStatus.SC_OK);

        file.setNameFile("RGllZ28gQXNzaXMgQ2FydmFsaG8gUGVyZWly");
        given().contentType(ContentType.JSON).pathParam("id", 12345).body(file).post("/v1/diff/{id}/right").then().statusCode(HttpStatus.SC_OK);
        given().get("/v1/diff/").then().statusCode(HttpStatus.SC_OK).body("Error",containsString("Documentos 12345 e 12345 com tamanhos diferentes"));
    }

}
