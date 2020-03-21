package br.com.CastGroup.ValidadorArquivo.repositories;

import br.com.CastGroup.ValidadorArquivo.domain.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFile extends JpaRepository<Arquivo,Long> {

//     Arquivo find(Long id);
}
