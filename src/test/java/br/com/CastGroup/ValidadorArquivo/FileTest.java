package br.com.CastGroup.ValidadorArquivo;


import br.com.CastGroup.ValidadorArquivo.Controller.FileValidation;
import br.com.CastGroup.ValidadorArquivo.domain.Arquivo;
import br.com.CastGroup.ValidadorArquivo.enums.PositionFile;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;

//@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FileTest {


//    @Mock
//    protected Arquivo arquivo;
    private FileValidation fileValidation;

    @Autowired
    public  FileTest(FileValidation fileValidation)
    {
        super();
        this.fileValidation = fileValidation;
    }

    @BeforeEach
    public  void configuracao(@Value("$(local.server.port))") Integer port){
        RestAssured.port = port;
    }

    @Test
    @DisplayName(" Valida se existe os dois arquivos no banco H2")
    public void validationFileOneAndTwo() {
        Arquivo arquivo = new Arquivo(1L,1l, PositionFile.right.toString(),"TWV1IG5vbWXDqSBIZW5yaXF1ZQ==");

        given()
               .contentType(ContentType.JSON)
                .pathParam("id",1L)
                .body(arquivo)
                .post("/v1/diff/{id}/right")
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    //   .get("/v1/diff/").then().statusCode(HttpStatus.SC_OK).body("sucess",);
    }

//
//
//    @Test
//    @BeforeEach
//    @DisplayName(" Cadastrar o primeiro arquivo no H2")
//    public void loadFileOne() {
//        Arquivo file = new Arquivo();
//        file.setNameFile("TWV1IG5vbWXDqSBIZW5yaXF1ZQ==");
//        given().contentType(ContentType.JSON).pathParam("id", 12345).body(file).post("/v1/diff/{id}/right").then().statusCode(HttpStatus.SC_OK);
//
//    }
//
//    @Test
//    @BeforeEach
//    @DisplayName(" Cadastrar o segundo arquivo no H2")
//
//    public void loadFileTwo() {
//        Arquivo file = new Arquivo();
//        file.setNameFile("RXUgdGUgYW1vIGpvYW5uYQ==");
//        given().contentType(ContentType.JSON).pathParam("id", 12).body(file).post("/v1/diff/{id}/left").then().statusCode(HttpStatus.SC_OK);
//    }
//
//    @Test
//    @BeforeAll
//    @DisplayName("Verifica se existe algum arquivo cadastrado na base H2 para fazer a comparação")
//    public void validationFile() {
//        loadFileOne();
//        loadFileTwo();
//        given().get("/v1/diff/").then().statusCode(HttpStatus.SC_OK);
//        //.body("sucess",  containsString("Nenhum documento left e right encontrado"));
//    }
//
//
//    @Test
//    @BeforeAll
//    @DisplayName(" Cadastrar os dois arquivos ao mesmo tempo arquivo no H2")
//    public void validationFiles() {
//        Arquivo left = new Arquivo();
//        left.setNameFile("TWV1IG5vbWXDqSBIZW5yaXF1ZQ==");
//        given().contentType(ContentType.JSON).pathParam("id", 1).body(left).post("/v1/diff/{id}/left").then().statusCode(HttpStatus.SC_OK);
//
//        Arquivo right = new Arquivo();
//        right.setNameFile("TWV1IG5vbWXDqSBIZW5yaXF1ZQ==");
//        given().contentType(ContentType.JSON).pathParam("id", 2).body(right).post("/v1/diff/{id}/right").then().statusCode(HttpStatus.SC_OK);
//
//        //  given().get("/v1/diff/").then().statusCode(HttpStatus.SC_OK).body("Success", containsString("Documentos 1 e 2 idênticos"));
//    }
//
//
//    //----------------------------------------------------------------------------------------------------------
//
//    @Test
//    public void validationFilesEqual() {
//        Arquivo left = new Arquivo();
//        left.setNameFile("TWV1IG5vbWXDqSBIZW5yaXF1ZQ==");
//        given().contentType(ContentType.JSON).pathParam("id", 1).body(left).post("/v1/diff/{id}/left").then().statusCode(HttpStatus.SC_OK);
//
//        Arquivo right = new Arquivo();
//        right.setNameFile("TWV1IG5vbWXDqSBIZW5yaXF1ZQ==");
//        given().contentType(ContentType.JSON).pathParam("id", 2).body(right).post("/v1/diff/{id}/right").then().statusCode(HttpStatus.SC_OK);
//
//          given().get("/v1/diff/").then().statusCode(HttpStatus.SC_OK).body("Success", containsString("Documentos 1 e 2 idênticos"));
//    }
//
//    @Test
//    public void validationFIleDiferent() {
//        Arquivo left = new Arquivo();
//        left.setNameFile("TWV1IG5vbWXDqSBIZW5yaXF1ZQ==");
//        given().contentType(ContentType.JSON).pathParam("id", 1).body(left).post("/v1/diff/{id}/left").then().statusCode(HttpStatus.SC_OK);
//
//        Arquivo right = new Arquivo();
//
//        right.setNameFile("RXUgdGUgYW1vIGpvYW5uYQ==");
//        given().contentType(ContentType.JSON).pathParam("id", 2).body(right).post("/v1/diff/{id}/right").then().statusCode(HttpStatus.SC_OK);
//        given().get("/v1/diff/").then().statusCode(HttpStatus.SC_OK).body("Error",containsString("Documentos 1 e 2 com tamanhos diferentes"));
//    }
//

    

}
