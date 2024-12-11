package com.projeto.sistema.modelos;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "cidade")
public class Cidade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nome;
    private String cep;

    @ManyToOne
    private Estado estado;
    //essa anotação indica para o banco de dados que vamos ter
    //uma associação de *um para muitos* logo um estado vai ter
    //varias cidades


    public int getId() {return id;    }
    public void setId(int id) {this.id = id;    }

    public String getNome() {return nome;    }
    public void setNome(String nome) {this.nome = nome;    }

    public String getCep() {return cep;   }
    public void setCep(String cep) {this.cep = cep;    }

    public Estado getEstado() {return estado;   }
    public void setEstado(Estado estado) {this.estado = estado;    }

    public static long getSerialVersionUID(){
        return serialVersionUID;
    }
}
