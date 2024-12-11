package com.projeto.sistema.controller;

import com.projeto.sistema.modelos.Venda;
import com.projeto.sistema.modelos.ItemVenda;
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
public class VendaControle {

    @Autowired
    private VendaRepositorio vendaRepositorio;
    @Autowired
    private ItemVendaRepositorio itemVendaRepositorio;
    @Autowired
    private ProdutoRepositorio produtoRepositorio;
    @Autowired
    private FuncionarioRepositorio funcionarioRepositorio;
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    //armazenar os valores temporariamente
    private List<ItemVenda> listaItemVenda = new ArrayList<ItemVenda>();
    
    @GetMapping("/cadastroVenda")
    public ModelAndView cadastrar(@ModelAttribute Venda venda, ItemVenda itemVenda) {
        ModelAndView mv = new ModelAndView("administrativo/vendas/cadastro");
        mv.addObject("venda", venda);
        mv.addObject("listaItemVendas", this.listaItemVenda);
        mv.addObject("listaFuncionarios", funcionarioRepositorio.findAll());
        mv.addObject("listaClientes", clienteRepositorio.findAll());
        mv.addObject("listaProdutos", produtoRepositorio.findAll());
        return mv;
    }


    @GetMapping("/listaVendas")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/vendas/lista");
        mv.addObject("listaVendas", vendaRepositorio.findAll());
        return mv;
    }

    @GetMapping("/editarVenda/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Venda> venda = vendaRepositorio.findById(id);

        if (venda.isPresent()) {
            this.listaItemVenda = itemVendaRepositorio.findByVendaId(venda.get().getId());
            return cadastrar(venda.get(), new ItemVenda());
        } else {
            // Trate a situação em que a venda não foi encontrada
            return new ModelAndView("redirect:/paginaDeErro"); // ou qualquer outra lógica
        }
    }



//    @GetMapping("/removerVenda/{id}")
//    public ModelAndView remover(@PathVariable("id") Long id) {
//        Optional<Venda> venda = vendaRepositorio.findById(id);
//        if (venda.isPresent()) {
//            vendaRepositorio.delete(venda.get());
//        }
//        return listar();
//    }

    @PostMapping("/salvarVenda")
    public ModelAndView salvar(String acao, Venda venda, ItemVenda itemVenda, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrar(venda, itemVenda);
        }
        if (acao.equals("itens")) { // Adiciona item à lista

            itemVenda.setValor(itemVenda.getProduto().getPrecoCusto());
            itemVenda.setSubtotal(itemVenda.getProduto().getPrecoVenda()*itemVenda.getQuantidade());

            venda.setValorTotal(venda.getValorTotal() + itemVenda.getValor());
            venda.setQtdTotal(venda.getQtdTotal() + itemVenda.getQuantidade());


            this.listaItemVenda.add(itemVenda);

        } else if (acao.equals("salvar")) {
            vendaRepositorio.saveAndFlush(venda);
            for (ItemVenda it : listaItemVenda) {
                it.setVenda(venda);
//                it.setSubtotal(it.getValor()*it.getQuantidade());//isso é para ter o valor na hora de salvar
                itemVendaRepositorio.saveAndFlush(it); // Salvando o item atual

                Optional<Produto> prod = produtoRepositorio.findById(it.getProduto().getId());
                if (prod.isPresent()) {
                    Produto produto = prod.get();
                    produto.setEstoque(produto.getEstoque() - it.getQuantidade());//ao fazer uma venda estamos diminuindo a quanidade do estoque
                    produto.setPrecoVenda(it.getValor());
//                    produto.setPrecoCusto(it.getValorCusto());
                    produtoRepositorio.saveAndFlush(produto);
                } else {
                    // Lidar com o caso de produto não encontrado, talvez lançar uma exceção
                }
            }
            this.listaItemVenda.clear(); // Limpar a lista de itens em vez de recriar

            return cadastrar(new Venda(), new ItemVenda());
        }

        return cadastrar(venda, new ItemVenda());
    }




    //get and sets list
    public List<ItemVenda> getListaItemVenda() {
        return listaItemVenda;
    }

    public void setListaItemVenda(List<ItemVenda> listaItemVenda) {
        this.listaItemVenda = listaItemVenda;
    }
}
