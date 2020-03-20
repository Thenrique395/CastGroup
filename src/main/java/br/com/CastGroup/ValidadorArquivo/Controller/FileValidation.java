package br.com.CastGroup.ValidadorArquivo.Controller;

import br.com.CastGroup.ValidadorArquivo.domain.File;
import br.com.CastGroup.ValidadorArquivo.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/v1")
public class FileValidation {

 private FileService fileService;

    @PostMapping("/diff")
    public String teste() {
        return "Entrou";
    }

    @PostMapping("/diff/{id}/left")
    public File addFileOne(@PathVariable(value = "id") long id, @RequestBody File file){
        return fileService.saveOne(id, file);
    }

    @PostMapping("/diff/{id}/right")
    public File addFileTwo(@PathVariable(value = "id") long id, @RequestBody File file) {
        return fileService.saveTwo(id, file);
    }

    @GetMapping("/diff")
    public ResponseEntity<Map<String, String>> getDiff() {
        return fileService.getDiff();
    }

}
