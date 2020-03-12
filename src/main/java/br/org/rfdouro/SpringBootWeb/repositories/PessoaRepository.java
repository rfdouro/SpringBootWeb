/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.rfdouro.SpringBootWeb.repositories;

import br.org.rfdouro.SpringBootWeb.models.Pessoa;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author romulo.douro
 */
@Repository
public interface PessoaRepository extends PagingAndSortingRepository<Pessoa, Integer> {

 @Override
 public List<Pessoa> findAll();
 
 public List<Pessoa> findAllByOrderByNome();

}
