package br.com.CastGroup.ValidadorArquivo.service;

import br.com.CastGroup.ValidadorArquivo.domain.File;
import br.com.CastGroup.ValidadorArquivo.enums.PositionFile;
import br.com.CastGroup.ValidadorArquivo.repositories.IFile;
import com.sun.tools.javac.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FileService {

    private IFile iFile;
    private  File file;

    @Autowired
    public FileService(IFile _iFile)  {
        super();
        this.iFile = _iFile;
    }

    public File saveOne(long id, File file) {
        file.setId(id);
        return iFile.save(file);
    }

    public File saveTwo(long id, File file) {
        file.setId(id);
        return iFile.save(file);
    }

    public ResponseEntity<Map<String, String>> getDiff() {
        Map<String, String> res = new HashMap<>();
        List<File> leftBase = (List<File>) iFile.findAll().get(PositionFile.left.ordinal());
        List<File> rightBase = (List<File>) iFile.findAll().get(PositionFile.right.ordinal());
        if (!leftBase.isEmpty() && !rightBase.isEmpty()) {
            String stringBase64Left = new String(Base64.getDecoder().decode(leftBase.get(0).getNameFile()));
            String stringBase64Right = new String(Base64.getDecoder().decode(rightBase.get(0).getNameFile()));
            if (stringBase64Left.length() != stringBase64Right.length()) {
                res.put("Error", "Documentos " + leftBase.get(0).getIdFIle() + " e " + rightBase.get(0).getIdFIle() + " com tamanhos diferentes");
                iFile.deleteAll();
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
                    res.put("Success", "Documentos " + leftBase.get(0).getIdFIle() + " e " + rightBase.get(0).getIdFIle() + " idênticos");
                    iFile.deleteAll();
                } else {
                    res.put("Diff","Documento ID(Left) " + leftBase.get(0).getIdFIle()
                                    + " diferente do Documento ID(Right) " + leftBase.get(0).getIdFIle()
                                    + " na(s) posição(es) " + listDiff.toString());
                    iFile.deleteAll();
                }
            }
        } else if (!leftBase.isEmpty() && rightBase.isEmpty()) {
            res.put("Error", "Nenhum documento right encontrado");
            iFile.deleteAll();
        } else if (leftBase.isEmpty() && !rightBase.isEmpty()) {
            res.put("Error", "Nenhum documento left encontrado");
           iFile.deleteAll();
        } else if (leftBase.isEmpty() && rightBase.isEmpty()) {
            res.put("Error", "Nenhum documento left e right encontrado");
            iFile.deleteAll();
        }
        return new ResponseEntity<Map<String, String>>(res, HttpStatus.OK);
    }

}