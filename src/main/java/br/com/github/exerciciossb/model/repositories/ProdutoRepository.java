package br.com.github.exerciciossb.model.repositories;


import br.com.github.exerciciossb.model.entities.Produto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

// Semelhante ao DAO, porem o repository define a interface de como vai abstrair o acesso a dados
// pode ser por vários tipos, por excell, texto etc
// interface extend outra interface
public interface ProdutoRepository
    extends PagingAndSortingRepository<Produto,Integer>{
    /**
     * A interface implementa o método se ele for colocado de acordo com a convenção
     * tem várias possibilidades de uso
     * @param parteNome
     * @return
     */

    /**
     * findByNomeContaining
     * findByNomeIsContaining
     * findByNomeContains
     *
     * findByNomeStartsWith
     * findByNomeEndsWith
     *
     * findByNomeNotContaining
     * @param parteNome
     * @return
     */
    public Iterable<Produto> findByNomeContainingIgnoreCase(String parteNome);
    @Query("SELECT p from tbl_produto p WHERE p.nome LIKE %:nome%")
    public Iterable<Produto> searchByNameLike(@Param("nome") String nome);
}
