package com.projeto.sistema.controller;

import com.projeto.sistema.modelos.Funcionario;
import com.projeto.sistema.repositorios.FuncionarioRepositorio;
import com.projeto.sistema.repositorios.CidadeRepositorio;
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
public class FuncionarioControle {

    @Autowired
    private FuncionarioRepositorio funcionarioRepositorio;

    @Autowired
    private CidadeRepositorio cidadeRepositorio;

    @GetMapping("/cadastroFuncionario")
    public ModelAndView cadastrarFuncionario(@ModelAttribute Funcionario funcionario) {
        ModelAndView mv = new ModelAndView("administrativo/funcionarios/cadastro");
        mv.addObject("funcionario", funcionario);
        mv.addObject("listaCidades", cidadeRepositorio.findAll());
        return mv;
    }

    @GetMapping("/listaFuncionarios")
    public ModelAndView listarFuncionarios() {
        ModelAndView mv = new ModelAndView("administrativo/funcionarios/lista");
        mv.addObject("listaFuncionarios", funcionarioRepositorio.findAll());
        return mv;
    }

    @GetMapping("/editarFuncionario/{id}")
    public ModelAndView editarFuncionario(@PathVariable("id") Long id) {
        Optional<Funcionario> funcionario = funcionarioRepositorio.findById(id);
        if (funcionario.isPresent()) {
            return cadastrarFuncionario(funcionario.get());
        } else {
            ModelAndView mv = listarFuncionarios();
            mv.addObject("mensagemErro", "Funcionário não encontrado");
            return mv;
        }
    }

    @GetMapping("/removerFuncionario/{id}")
    public ModelAndView removerFuncionario(@PathVariable("id") Long id) {
        Optional<Funcionario> funcionario = funcionarioRepositorio.findById(id);
        if (funcionario.isPresent()) {
            funcionarioRepositorio.delete(funcionario.get());
        }
        return listarFuncionarios();
    }

    @PostMapping("/salvarFuncionario")
    public ModelAndView salvarFuncionario(@ModelAttribute Funcionario funcionario, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrarFuncionario(funcionario);
        }
        funcionarioRepositorio.save(funcionario); // Use apenas save
        return listarFuncionarios(); // Redireciona para a lista após salvar
    }
}

