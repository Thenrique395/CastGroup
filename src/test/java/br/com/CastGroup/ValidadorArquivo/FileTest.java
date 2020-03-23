package br.com.CastGroup.ValidadorArquivo;


import br.com.CastGroup.ValidadorArquivo.Controller.FileValidation;
import br.com.CastGroup.ValidadorArquivo.domain.Arquivo;
import br.com.CastGroup.ValidadorArquivo.enums.PositionFile;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(JUnitPlatform.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FileTest {


    private  String valorOne ="TWV1IG5vbWXDqSBIZW5yaXF1ZQ==";
    private  String valorTwo ="TWV1IG5vbWXDqSBIZW5yaXF1ZQ==";
    private  String valortree ="Sm9hbm5hIGV1IHRlIGFtbyE=";
    private  String valorfour ="TWVnIGUgUGFuZG9yYQ==";



    @Mock
    protected FileValidation controller;

    @Value("${local.server.port}")
    protected int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

        @Test
    @DisplayName("")

    public void a() {
        Arquivo arquivo = new Arquivo(1l, PositionFile.right.toString(),valorOne);

        given()
               .contentType(ContentType.JSON)
                .pathParam("id",1L)
                .body(arquivo)
                .post("/v1/diff/{id}/left")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

        @Test
    @DisplayName(" ")
    public void b() {
        Arquivo arquivo = new Arquivo(2l, PositionFile.right.toString(),valorTwo);

        given()
               .contentType(ContentType.JSON)
                .pathParam("id",2L)
                .body(arquivo)
                .post("/v1/diff/{id}/right")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

        @Test
    @DisplayName("Verifica se os arquivos são igual")
    public void c() {
            given().get("/v1/diff/").then().statusCode(HttpStatus.SC_OK).body("Success", containsString("Arquivo 1 e 2 idênticos"));
    }

    @Test
    @DisplayName("Verifica se os arquivos são diferente")
    public void d() {
        Arquivo arquivo = new Arquivo(1l, PositionFile.right.toString(),valortree);
        given()
                .contentType(ContentType.JSON)
                .pathParam("id",1L)
                .body(arquivo)
                .post("/v1/diff/{id}/left")
                .then()
                .statusCode(HttpStatus.SC_OK);

        Arquivo arquivo1 = new Arquivo(2l, PositionFile.right.toString(),valorfour);
        given()
                .contentType(ContentType.JSON)
                .pathParam("id",2L)
                .body(arquivo1)
                .post("/v1/diff/{id}/right")
                .then()
                .statusCode(HttpStatus.SC_OK);

        given().get("/v1/diff/").then().statusCode(HttpStatus.SC_OK).body("Success", containsString("Documentos 1 e 2 com tamanhos diferentes"));
    }


}
