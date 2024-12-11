package com.projeto.sistema.controller;

import com.projeto.sistema.modelos.Entrada;
import com.projeto.sistema.modelos.ItemEntrada;
import com.projeto.sistema.modelos.Produto;
import com.projeto.sistema.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class EntradaControle {

    @Autowired
    private EntradaRepositorio entradaRepositorio;
    @Autowired
    private ItemEntradaRepositorio itemEntradaRepositorio;
    @Autowired
    private ProdutoRepositorio produtoRepositorio;
    @Autowired
    private FuncionarioRepositorio funcionarioRepositorio;
    @Autowired
    private FornecedorRepositorio fornecedorRepositorio;

    //armazenar os valores temporariamente
    private List<ItemEntrada> listaItemEntrada = new ArrayList<ItemEntrada>();
    
    @GetMapping("/cadastroEntrada")
    public ModelAndView cadastrar(@ModelAttribute Entrada entrada, ItemEntrada itemEntrada) {
        ModelAndView mv = new ModelAndView("administrativo/entradas/cadastro");
        mv.addObject("entrada", entrada);
        mv.addObject("listaItemEntradas", this.listaItemEntrada);
        mv.addObject("listaFuncionarios", funcionarioRepositorio.findAll());
        mv.addObject("listaFornecedores", fornecedorRepositorio.findAll());
        mv.addObject("listaProdutos", produtoRepositorio.findAll());
        return mv;
    }


    @GetMapping("/listaEntradas")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/entradas/lista");
        mv.addObject("listaEntradas", entradaRepositorio.findAll());
        return mv;
    }

    @GetMapping("/editarEntrada/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Entrada> entrada = entradaRepositorio.findById(id);
            this.listaItemEntrada = itemEntradaRepositorio.findByEntradaId(entrada.get().getId());
            return cadastrar(entrada.get(), new ItemEntrada());

    }



//    @GetMapping("/removerEntrada/{id}")
//    public ModelAndView remover(@PathVariable("id") Long id) {
//        Optional<Entrada> entrada = entradaRepositorio.findById(id);
//        if (entrada.isPresent()) {
//            entradaRepositorio.delete(entrada.get());
//        }
//        return listar();
//    }

    @PostMapping("/salvarEntrada")
    public ModelAndView salvar(String acao, Entrada entrada, ItemEntrada itemEntrada, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrar(entrada, itemEntrada);
        }
        if (acao.equals("itens")) {
            this.listaItemEntrada.add(itemEntrada); // Adiciona item à lista
            entrada.setValorTotal(entrada.getValorTotal() + itemEntrada.getValor());
            entrada.setQtdTotal(entrada.getQtdTotal() + itemEntrada.getQuantidade());

        } else if (acao.equals("salvar")) {
            entradaRepositorio.saveAndFlush(entrada);
            for (ItemEntrada it : listaItemEntrada) {
                it.setEntrada(entrada);
                itemEntradaRepositorio.saveAndFlush(it); // Salvando o item atual

                Optional<Produto> prod = produtoRepositorio.findById(it.getProduto().getId());
                if (prod.isPresent()) {
                    Produto produto = prod.get();
                    produto.setEstoque(produto.getEstoque() + it.getQuantidade());
                    produto.setPrecoVenda(it.getValor());
                    produto.setPrecoCusto(it.getValorCusto());
                    produtoRepositorio.saveAndFlush(produto);
                } else {
                    // Lidar com o caso de produto não encontrado, talvez lançar uma exceção
                }
            }
            this.listaItemEntrada.clear(); // Limpar a lista de itens em vez de recriar

            return cadastrar(new Entrada(), new ItemEntrada());
        }

        return cadastrar(entrada, new ItemEntrada());
    }




    //get and sets list
    public List<ItemEntrada> getListaItemEntrada() {
        return listaItemEntrada;
    }

    public void setListaItemEntrada(List<ItemEntrada> listaItemEntrada) {
        this.listaItemEntrada = listaItemEntrada;
    }
}
