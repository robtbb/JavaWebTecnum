package com.projeto.sistema.repositorios;

import com.projeto.sistema.modelos.ItemEntrada;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemEntradaRepositorio extends JpaRepository<ItemEntrada, Long> {

    @Query("SELECT i FROM ItemEntrada i WHERE i.entrada.id = ?1")
    List<ItemEntrada> findByEntradaId(Long entradaId);
}

