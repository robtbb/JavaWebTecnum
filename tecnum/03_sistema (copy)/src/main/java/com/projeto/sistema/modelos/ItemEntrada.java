package com.projeto.sistema.modelos;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "itemEntrada")
public class ItemEntrada implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double quantidade;
    private Double valor;
    private Double valorCusto;

    @ManyToOne
    @JoinColumn(name = "entrada_id")
    private Entrada entrada;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    //getters and setters
    public Long getId() {return id;    }
    public void setId(Long id) {this.id = id;    }

    public Double getQuantidade() {return quantidade;    }
    public void setQuantidade(Double quantidade) {this.quantidade = quantidade;    }

    public Double getValor() {return valor;  }
    public void setValor(Double valor) {this.valor = valor;    }

    public Double getValorCusto() {return valorCusto;    }
    public void setValorCusto(Double valorCusto) {this.valorCusto = valorCusto;    }

    public Entrada getEntrada() {return entrada;    }
    public void setEntrada(Entrada entrada) {this.entrada = entrada;    }

    public Produto getProduto() {return produto;    }
    public void setProduto(Produto produto) {this.produto = produto;    }
}
