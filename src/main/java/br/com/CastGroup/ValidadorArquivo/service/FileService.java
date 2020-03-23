package br.com.CastGroup.ValidadorArquivo.service;

import br.com.CastGroup.ValidadorArquivo.domain.Arquivo;
import br.com.CastGroup.ValidadorArquivo.enums.PositionFile;
import br.com.CastGroup.ValidadorArquivo.repositories.IFile;
import org.hibernate.annotations.SQLDeleteAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FileService {

    private IFile iFile;
    private Arquivo file = new Arquivo();

    @Autowired
    public FileService(IFile _iFile)  {
        super();
        this.iFile = _iFile;
    }

    public FileService()  {
        super();
    }

    public Arquivo saveOne(long id, Arquivo arquivo) {
        file.setId(id);
        return iFile.save(arquivo);
    }

    public Arquivo saveTwo(long id, Arquivo arquivo) {
        return iFile.save(arquivo);
    }

    public ResponseEntity<Map<String, String>> compararArquivos() {
        Map<String, String> resposta = new HashMap<>();
        List<Arquivo> arquivos =  iFile.findAll();

        if ( !arquivos.isEmpty()) {
            String arquivoleft = new String(Base64.getDecoder().decode(arquivos.get(0).getNameFile()));
            String arquivoright = new String(Base64.getDecoder().decode(arquivos.get(1).getNameFile()));
            if (arquivoleft.length() != arquivoright.length()) {
                resposta.put("Success", "Documentos " + arquivos.get(0).getIdFIle() + " e " + arquivos.get(1).getIdFIle() + " com tamanhos diferentes");
            } else {
                char[] stringUm = arquivoleft.toCharArray();
                char[] stringDois = arquivoright.toCharArray();
                List<Integer> comparador = new ArrayList<Integer>();
                for (int x = 0; x < stringUm.length; x++) {
                    for (int y = x; y < stringDois.length; y++) {
                        if (stringUm[x] == (stringDois[y])) {
                            y = stringDois.length;
                        } else {
                            comparador.add(y + 1);
                            y = stringDois.length;
                        }
                    }
                }
                if (comparador.isEmpty()) {
                    resposta.put("Success", "Arquivo " + arquivos.get(0).getIdFIle() + " e " + arquivos.get(1).getIdFIle() + " idênticos");
                } else {
                    resposta.put("Diff", "Arquivo chave: (Left) " + arquivos.get(0).getIdFIle() + " diferente do Documento  chave:(Right) " + arquivos.get(1).getIdFIle() + " na(s) posição(es) " + comparador.toString());
                }
            }
        }
        return new ResponseEntity<Map<String, String>>(resposta, HttpStatus.OK);
    }



}
