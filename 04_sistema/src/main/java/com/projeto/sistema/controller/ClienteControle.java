package com.projeto.sistema.controller;

import com.projeto.sistema.modelos.Cliente;
import com.projeto.sistema.repositorios.CidadeRepositorio;
import com.projeto.sistema.repositorios.ClienteRepositorio;
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
public class ClienteControle {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private CidadeRepositorio cidadeRepositorio;

    @GetMapping("/cadastroCliente")
    public ModelAndView cadastrarCliente(@ModelAttribute Cliente cliente) {
        ModelAndView mv = new ModelAndView("administrativo/clientes/cadastro");
        mv.addObject("cliente", cliente);
        mv.addObject("listaCidades", cidadeRepositorio.findAll());
        return mv;
    }

    @GetMapping("/listaClientes")
    public ModelAndView listarClientes() {
        ModelAndView mv = new ModelAndView("administrativo/clientes/lista");
        mv.addObject("listaClientes", clienteRepositorio.findAll());
        return mv;
    }

    @GetMapping("/editarCliente/{id}")
    public ModelAndView editarCliente(@PathVariable("id") Long id) {
        Optional<Cliente> cliente = clienteRepositorio.findById(id);
        if (cliente.isPresent()) {
            return cadastrarCliente(cliente.get());
        } else {
            ModelAndView mv = listarClientes();
            mv.addObject("mensagemErro", "Funcionário não encontrado");
            return mv;
        }
    }

    @GetMapping("/removerCliente/{id}")
    public ModelAndView removerCliente(@PathVariable("id") Long id) {
        Optional<Cliente> cliente = clienteRepositorio.findById(id);
        if (cliente.isPresent()) {
            clienteRepositorio.delete(cliente.get());
        }
        return listarClientes();
    }

    @PostMapping("/salvarCliente")
    public ModelAndView salvarCliente(@ModelAttribute Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrarCliente(cliente);
        }
        clienteRepositorio.save(cliente); // Use apenas save
        return listarClientes(); // Redireciona para a lista após salvar
    }
}

