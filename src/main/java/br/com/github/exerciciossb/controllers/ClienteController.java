package br.com.github.exerciciossb.controllers;

import br.com.github.exerciciossb.model.entities.Cliente;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/clientes") // os métodos abaixo percentem a /clientes
public class ClienteController {

    @GetMapping(path = "/qualquer")
    public Cliente obterCliente(){
        return new Cliente(1, "Pedro","123.456.789-00");
    }

    @GetMapping("/{id}") //joga o valor do browser para o id
    public Cliente obterClientePorId1(@PathVariable int id){
        return new Cliente(id, "Maria", "987.654.321-00");
    }

    @GetMapping
    public Cliente obterClientePorId2(
            @RequestParam(name = "id", defaultValue = "1") int id){
        return new Cliente(id, "João", "111.222.333-44");
    }

}
