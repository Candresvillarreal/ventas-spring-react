package com.ventas.ventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ventas.ventas.model.Cliente;

//Repositorio correspondiente al cliente

public interface IClienteRepository extends JpaRepository<Cliente, Integer>{

}
