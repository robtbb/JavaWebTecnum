//package com.projeto.sistema.modelos;
//
//import jakarta.persistence.*;
//
//import java.util.Date;
//
//@Entity
//public class Venda {
//    private Long Id;
//    private String Obs;
//    private Double ValorTotal;
//    private Double QtdTotal;
//    private Date DataVenda;
//
//    @ManyToOne
//    @JoinColumn(name = "funcionario_id")
//    private  Funcionario Funcionario;
//    @ManyToOne
//    @JoinColumn(name = "cliente_id")
//    private Cliente Cliente;
//    @jakarta.persistence.Id
//    private Long id;
//
//    public Long getId() {
//        return Id;
//    }
//
//    public void setId(Long id) {
//        Id = id;
//    }
//
//    public String getObs() {
//        return Obs;
//    }
//
//    public void setObs(String obs) {
//        Obs = obs;
//    }
//
//    public Double getValorTotal() {
//        return ValorTotal;
//    }
//
//    public void setValorTotal(Double valorTotal) {
//        ValorTotal = valorTotal;
//    }
//
//    public Double getQtdTotal() {
//        return QtdTotal;
//    }
//
//    public void setQtdTotal(Double qtdTotal) {
//        QtdTotal = qtdTotal;
//    }
//
//    public Date getDataVenda() {
//        return DataVenda;
//    }
//
//    public void setDataVenda(Date dataVenda) {
//        DataVenda = dataVenda;
//    }
//
//    public Funcionario getFuncionario() {
//        return Funcionario;
//    }
//
//    public void setFuncionario(Funcionario funcionario) {
//        Funcionario = funcionario;
//    }
//
//    public Cliente getCliente() {
//        return Cliente;
//    }
//
//    public void setCliente(Cliente cliente) {
//        Cliente = cliente;
//    }
//
//}
