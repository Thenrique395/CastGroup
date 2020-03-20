package br.com.CastGroup.ValidadorArquivo.domain;

import br.com.CastGroup.ValidadorArquivo.enums.PositionFile;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FILE")
public class File implements Serializable {

    private  static final long serialVersionUID =1L;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long idFIle;

    private PositionFile positionFile;

    private String nameFile;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdFIle() {
        return idFIle;
    }

    public void setIdFIle(long idFIle) {
        this.idFIle = idFIle;
    }

    public PositionFile getPositionFile() {
        return positionFile;
    }

    public void setPositionFile(PositionFile positionFile) {
        this.positionFile = positionFile;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }
}
