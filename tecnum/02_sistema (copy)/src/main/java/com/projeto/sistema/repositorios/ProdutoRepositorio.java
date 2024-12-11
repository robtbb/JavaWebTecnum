package com.projeto.sistema.repositorios;

import com.projeto.sistema.modelos.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {


}
