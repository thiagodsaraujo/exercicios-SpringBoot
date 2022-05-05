package br.com.github.exerciciossb.config;


import br.com.github.exerciciossb.model.entities.Produto;
import br.com.github.exerciciossb.model.entities.User;
import br.com.github.exerciciossb.model.repositories.ProdutoRepository;
import br.com.github.exerciciossb.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner {
    /**
     * Vamos usar pra database seeding, popular o banco de dados com alguns objetos
     * Nesse caso vai ter uma injeção de dependencia, ela tem que ser fraca, desacoplada...
     * Com framework ele tem um mecanismo implicito, automatico...
     */

    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null,"Maria", "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex","Alex Green", "alex@gmail.com", "977777777", "123456");
        userRepository.saveAll(Arrays.asList(u1,u2));
    }

}
