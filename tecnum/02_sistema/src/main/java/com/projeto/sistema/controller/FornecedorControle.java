package com.projeto.sistema.controller;

import com.projeto.sistema.modelos.Fornecedor;
import com.projeto.sistema.repositorios.CidadeRepositorio;
import com.projeto.sistema.repositorios.FornecedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class FornecedorControle {

    @Autowired
    private FornecedorRepositorio fornecedorRepositorio;

    @Autowired
    private CidadeRepositorio cidadeRepositorio;

    @GetMapping("/cadastroFornecedor")
    public ModelAndView cadastrarFornecedor(@ModelAttribute Fornecedor fornecedor) {
        ModelAndView mv = new ModelAndView("administrativo/fornecedores/cadastro");
        mv.addObject("fornecedor", fornecedor);
        mv.addObject("listaCidades", cidadeRepositorio.findAll());
        return mv;
    }

    @GetMapping("/listaFornecedores")
    public ModelAndView listarFornecedores() {
        ModelAndView mv = new ModelAndView("administrativo/fornecedores/lista");
        mv.addObject("listaFornecedores", fornecedorRepositorio.findAll());
        return mv;
    }

    @GetMapping("/editarFornecedor/{id}")
    public ModelAndView editarFornecedor(@PathVariable("id") Long id) {
        Optional<Fornecedor> fornecedor = fornecedorRepositorio.findById(id);
        if (fornecedor.isPresent()) {
            return cadastrarFornecedor(fornecedor.get());
        } else {
            ModelAndView mv = listarFornecedores();
            mv.addObject("mensagemErro", "Funcionário não encontrado");
            return mv;
        }
    }

    @GetMapping("/removerFornecedor/{id}")
    public ModelAndView removerFornecedor(@PathVariable("id") Long id) {
        Optional<Fornecedor> fornecedor = fornecedorRepositorio.findById(id);
        if (fornecedor.isPresent()) {
            fornecedorRepositorio.delete(fornecedor.get());
        }
        return listarFornecedores();
    }

    @PostMapping("/salvarFornecedor")
    public ModelAndView salvarFornecedor(@ModelAttribute Fornecedor fornecedor, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrarFornecedor(fornecedor);
        }
        fornecedorRepositorio.save(fornecedor); // Use apenas save
        return listarFornecedores(); // Redireciona para a lista após salvar
    }
}

