package br.com.CastGroup.ValidadorArquivo.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FILE")
public class Arquivo implements Serializable {

    private  static final long serialVersionUID =1L;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long idFIle;

    private String positionFile;

    private String nameFile;


    public  Arquivo(){

    }

    public Arquivo(Long id, Long idFIle, String positionFile, String nameFile) {
        this.id = id;
        this.idFIle = idFIle;
        this.positionFile = positionFile;
        this.nameFile = nameFile;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getIdFIle() {
        return idFIle;
    }

    public void setIdFIle(long idFIle) {
        this.idFIle = idFIle;
    }

    public String getPositionFile() {
        return positionFile;
    }

    public void setPositionFile(String positionFile) {
        this.positionFile = positionFile;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }
}
