package br.com.CastGroup.ValidadorArquivo.service;

import br.com.CastGroup.ValidadorArquivo.domain.Arquivo;
import br.com.CastGroup.ValidadorArquivo.enums.PositionFile;
import br.com.CastGroup.ValidadorArquivo.repositories.IFile;
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
//        file.setIdFIle(id);
//        file.setPositionFile(PositionFile.left.name());
        return iFile.save(arquivo);
    }

    public Arquivo saveTwo(long id, Arquivo arquivo) {
        file.setIdFIle(id);
        file.setPositionFile(PositionFile.right.name());
        return iFile.save(arquivo);
    }

    public ResponseEntity<Map<String, String>> getDiff() {
        Map<String, String> res = new HashMap<>();
        List<Arquivo> arquivoList =  iFile.findAll();

        if ( !arquivoList.isEmpty()) {
            String stringBase64Left = new String(Base64.getDecoder().decode(arquivoList.get(0).getNameFile()));
            String stringBase64Right = new String(Base64.getDecoder().decode(arquivoList.get(1).getNameFile()));
            if (stringBase64Left.length() != stringBase64Right.length()) {
                res.put("Error", "Documentos " + arquivoList.get(0).getIdFIle() + " e " + arquivoList.get(1).getIdFIle() + " com tamanhos diferentes");
//                iFile.deleteAll();
            } else {
                char[] stringUm = stringBase64Left.toCharArray();
                char[] stringDois = stringBase64Right.toCharArray();
                List<Integer> listDiff = new ArrayList<Integer>();
                for (int xL = 0; xL < stringUm.length; xL++) {
                    for (int xR = xL; xR < stringDois.length; xR++) {
                        if (stringUm[xL] == (stringDois[xR])) {
                            xR = stringDois.length;
                        } else {
                            listDiff.add(xR + 1);
                            xR = stringDois.length;
                        }
                    }
                }
                if (listDiff.isEmpty()) {
                    res.put("Success", "Documentos " + arquivoList.get(0).getIdFIle() + " e " + arquivoList.get(0).getIdFIle() + " idênticos");
//                iFile.deleteAll();
                } else {
                    res.put("Diff","Documento ID(Left) " + arquivoList.get(0).getIdFIle() + " diferente do Documento ID(Right) " + arquivoList.get(0).getIdFIle() + " na(s) posição(es) " + listDiff.toString());
//                iFile.deleteAll();
                }
            }
        } else if (!arquivoList.isEmpty() && arquivoList.isEmpty()) {
            res.put("Error", "Nenhum documento right encontrado");
//                iFile.deleteAll();
        } else if (arquivoList.isEmpty() && !arquivoList.isEmpty()) {
            res.put("Error", "Nenhum documento left encontrado");
//                iFile.deleteAll();
        } else if (arquivoList.isEmpty() && arquivoList.isEmpty()) {
            res.put("Error", "Nenhum documento left e right encontrado");
//                iFile.deleteAll();
        }
        return new ResponseEntity<Map<String, String>>(res, HttpStatus.OK);
    }

}
