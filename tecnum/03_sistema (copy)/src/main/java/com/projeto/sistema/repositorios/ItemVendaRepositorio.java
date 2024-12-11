package com.projeto.sistema.repositorios;

import com.projeto.sistema.modelos.ItemVenda;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemVendaRepositorio extends JpaRepository<ItemVenda, Long> {

    @Query("SELECT i FROM ItemVenda i WHERE i.venda.id = ?1")
    List<ItemVenda> findByVendaId(Long vendaId);
}

