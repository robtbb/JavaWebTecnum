package com.projeto.sistema.controller;

import com.projeto.sistema.modelos.Estado;
import com.projeto.sistema.repositorios.EstadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class EstadoControle {

    @Autowired
    private EstadoRepositorio estadoRepositorio;

    @GetMapping("/cadastroEstado")
    public ModelAndView cadastrar(@ModelAttribute Estado estado) {
        ModelAndView mv = new ModelAndView("administrativo/estados/cadastro");
        mv.addObject("estado", estado);
        return mv;
    }

    @GetMapping("/listaEstados")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/estados/lista");
        mv.addObject("listaEstados", estadoRepositorio.findAll());
        return mv;
    }

    @GetMapping("/editarEstado/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Estado> estado = estadoRepositorio.findById(id);
        if (estado.isPresent()) {
            return cadastrar(estado.get());
        } else {
            // Lidar com a situação em que o estado não é encontrado
            return listar(); // ou redirecionar para uma página de erro
        }
    }

    @GetMapping("/removerEstado/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Estado> estado = estadoRepositorio.findById(id);
        if (estado.isPresent()) {
            estadoRepositorio.delete(estado.get());
        }
        return listar();
    }

    @PostMapping("/salvarEstado")
    public ModelAndView salvar(@ModelAttribute Estado estado, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrar(estado);
        }
        estadoRepositorio.saveAndFlush(estado);
        return cadastrar(new Estado());
    }
}
