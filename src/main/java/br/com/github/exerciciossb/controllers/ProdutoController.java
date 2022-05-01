package br.com.github.exerciciossb.controllers;


import br.com.github.exerciciossb.model.entities.Produto;
import br.com.github.exerciciossb.model.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    // só pode usar em classe gerenciadas, como o Controller aqui que possui o RestController
    // quem instancia é o springboot, há uma inversão de injeção...

    @Autowired  // automaticamente vai colocar um objeto dentro desse produtoRepository
    private ProdutoRepository produtoRepository;

    /**
     * Fazendo dessa forma, voce consegue ter dois métodos em um
     * Dessa forma o método abaixo vai salvar e alterar o produto
     * Vai depender da lógica do négocio se vai precisar colocar tudo em um mesmo método
     * ou dividir em vários métodos
     * @param produto
     * @return
     */
//    @PostMapping
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public @ResponseBody Produto salvarProduto(@Valid Produto produto){ // que seja um produto valido
        // as validaçõe estão em model
        // não precisa setar todos os atributos/
        // o proprio springboot é esperto o suficiente e coloca para voce nas requisições
        produtoRepository.save(produto);
        return produto;
    }


    /**
     * O que significa que com a mesma URL da requestmapping
     * se fizer o post ta chamando o metodo acima, se fizer um get esta chamando o abaixo
     * @return
     */
    @GetMapping
    public Iterable<Produto> obterProdutos(){
       return produtoRepository.findAll(); // retorna todos os produtos da tabela, não é o ideal
    }
    @GetMapping(path = "/nome/{parteNome}")
    public Iterable<Produto> obterProdutosPorNome(@PathVariable String parteNome){
//        return produtoRepository.findByNomeContainingIgnoreCase(parteNome);
        return produtoRepository.searchByNameLike(parteNome);
    }

    @GetMapping(path = "/pagina/{numeroPagina}/{qtdePagina}")
    public Iterable<Produto> obterProdutosPorPagina(
            @PathVariable int numeroPagina,
            @PathVariable int qtdePagina){
        if(qtdePagina >= 10) qtdePagina = 10; // só vai setar no máximo de 10 em 10, validação
        Pageable page = PageRequest.of(numeroPagina,qtdePagina);
        return produtoRepository.findAll(page);
    }

    @GetMapping(path = "/{id}")
    public Optional<Produto> obterProdutoPorId(@PathVariable int id){
        return produtoRepository.findById(id);
    }

    /**
     * Significa que agora toda vez que for feita uma requisição do tipo put
     * ele vai chamar esse método
     * PUT = ALTERAÇÃO TOTAL - TANTO PARA ALTERAÇÃO TOTAL OU UM ATRIBUTO APENAS
     * PATH = ALTERAÇÃO PARCIAL - SOMENTE ATRIBUTOS
     * @param produto
     * @return produto
     */
//    @PutMapping
//    public Produto alterarProduto(@Valid Produto produto){
//        produtoRepository.save(produto);
//        return produto;
//    }

    /**
     * Estudar softdelete para fazer as exclusões
     * @param id
     */
    @DeleteMapping(path = "/{id}")
    public void excluirProduto(@PathVariable int id){
        produtoRepository.deleteById(id);
    }
}
