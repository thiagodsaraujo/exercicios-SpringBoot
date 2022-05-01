package br.com.github.exerciciossb.controllers;


import org.springframework.web.bind.annotation.*;

@RestController
public class PrimeiroController {

    @GetMapping// quer mapear uma requisição para esse método
    //localhost:8090/ola
    public String paginaInicial(){
        return "PAGINA INICIAL";
    }

    @GetMapping(path = "/ola")// quer mapear uma requisição para esse método
    //localhost:8090/ola
    public String ola(){
        return "Olá Spring Boot!";
    }

//    @PostMapping(path = "/saudacao") // quer mapear uma requisição para esse método
//    // usando postMapping voce mapea uma URL para dois métodos http diferentes
//    public String saudacao(){
//        return "Olá Spring Boot! (Post)";
//    }


}
