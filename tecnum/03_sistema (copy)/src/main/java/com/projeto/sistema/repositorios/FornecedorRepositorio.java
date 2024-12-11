package com.projeto.sistema.repositorios;

import com.projeto.sistema.modelos.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepositorio extends JpaRepository<Fornecedor, Long> {


}

/*
    O QUE É JPA?

    JPA(Java Persistence API) é uma especificação da liguagem Java para mapeato objeto-relacional
    (ORM-Object-RelationalMapping). O JPA define um confjunto de regras e interfaces que
    permitem que objetos java sejam armazenasdos em bancos de dados relacionais sem que vocé tenha
    que escrever SQL diretamente.

    Objetivo principal: Facilitar o desenvolvimento de aplicações java que interagem com banco
    de dados ao permitir que desenvolvedores manipulesm dados como objetos java em vez de lidar
    diretamente com SQL.

    Sem implemtação própria: JPA é apenas uma especficação, não possui implementação. Para que funcione
    é necessário utilizar uma biblioteca que implemente o JPA, como Hibernate, EclipseLink...


    O QUE É HIBERNATE?

    É uma implemetanção de JPA, e ao mesmo tempo uma framework ROM completa. O hibernate
    implementa todas as interfaces e regras definidas pela JPA e, além disso, oferece
    varios recursos adicinais que facilitma o mapeadmente e a manipulação de dados
    em um vanco de dados relacional.

    Objetivo: Fornecer uma solução de mapeamento objeto-relacional,
    permitindo que desenvolvedores Java travalhem com banco de dados usando objetos
    e evitando a implementação direta por mysql

    Exemplo básico de uso de JPA com Hibernate:
java
Copy code
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Double preco;

    // getters e setters
}
Aqui temos a anotação @Entity, que vem do JPA, e o Hibernate seria responsável por mapear essa classe Produto para uma tabela no banco de dados.

 */