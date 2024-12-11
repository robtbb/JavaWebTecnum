package com.projeto.sistema.controller;

import com.projeto.sistema.modelos.Cidade;
import com.projeto.sistema.repositorios.CidadeRepositorio;
import com.projeto.sistema.repositorios.EstadoRepositorio;
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
public class CidadeControle {

    @Autowired
    private CidadeRepositorio cidadeRepositorio;
    @Autowired
    private EstadoRepositorio estadoRepositorio; // <- Correto
    
    @GetMapping("/cadastroCidade")
    public ModelAndView cadastrar(@ModelAttribute Cidade cidade) {
        ModelAndView mv = new ModelAndView("administrativo/cidades/cadastro");
        mv.addObject("cidade", cidade);
        mv.addObject("listaEstados", estadoRepositorio.findAll());
        return mv;
    }


    @GetMapping("/listaCidades")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/cidades/lista");
        mv.addObject("listaCidades", cidadeRepositorio.findAll());
        return mv;
    }

    @GetMapping("/editarCidade/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Cidade> cidade = cidadeRepositorio.findById(id);
        if (cidade.isPresent()) {
            return cadastrar(cidade.get());
        } else {
            // Lidar com a situação em que o cidade não é encontrado
            return listar(); // ou redirecionar para uma página de erro
        }
    }

    @GetMapping("/removerCidade/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Cidade> cidade = cidadeRepositorio.findById(id);
        if (cidade.isPresent()) {
            cidadeRepositorio.delete(cidade.get());
        }
        return listar();
    }

    @PostMapping("/salvarCidade")
    public ModelAndView salvar(@ModelAttribute Cidade cidade, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrar(cidade);
        }
        cidadeRepositorio.saveAndFlush(cidade);
        return cadastrar(new Cidade());
    }
}
