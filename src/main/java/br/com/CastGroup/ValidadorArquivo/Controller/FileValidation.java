package br.com.CastGroup.ValidadorArquivo.Controller;

import br.com.CastGroup.ValidadorArquivo.domain.Arquivo;
import br.com.CastGroup.ValidadorArquivo.repositories.IFile;
import br.com.CastGroup.ValidadorArquivo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/v1")
public class FileValidation {

	@Autowired
    private FileService fileService;

    @PostMapping("/diff/{id}/left")
    public Arquivo addFileOne(@PathVariable(value = "id") long id, @RequestBody Arquivo arquivo){
        return fileService.saveOne(id, arquivo);
    }

    @PostMapping("/diff/{id}/right")
    public Arquivo addFileTwo(@PathVariable(value = "id") long id, @RequestBody Arquivo arquivo) {
        return fileService.saveTwo(id, arquivo);
    }

    @GetMapping("/diff")
    public ResponseEntity<Map<String, String>> getDiff() {
        return fileService.getDiff();
    }

}
