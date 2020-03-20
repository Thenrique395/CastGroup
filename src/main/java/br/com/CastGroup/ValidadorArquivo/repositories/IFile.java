package br.com.CastGroup.ValidadorArquivo.repositories;

import br.com.CastGroup.ValidadorArquivo.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFile extends JpaRepository<File,Long> {

     File find(long id);
}
