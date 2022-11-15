package com.ventas.ventas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ventas.ventas.model.Cliente;
import com.ventas.ventas.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	//Servicio encontrar todos los clientes
	
	@GetMapping
	public ResponseEntity<List<Cliente>> findAll(){
		return ResponseEntity.ok(clienteService.findAll());
	}
	
	//Servicio encontrar un cliente
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable("id") Integer idCliente){
		return clienteService.finfById(idCliente)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	//Servicio crear un cliente
	@PostMapping
	public ResponseEntity<Cliente> create(@RequestBody Cliente cliente) {
		return new ResponseEntity<>(clienteService.create(cliente), HttpStatus.CREATED);
	}
	
	//Servicio actualizar un cliente
	@PutMapping
	public ResponseEntity<Cliente> update(@RequestBody Cliente cliente){
		return clienteService.finfById(cliente.getIdCliente())
				.map( c -> ResponseEntity.ok(clienteService.update(cliente)))
						.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	//Eliminar un cliente
	@DeleteMapping("/{id}")
	public ResponseEntity<Cliente> delete(@PathVariable("id") Integer idCliente){
		return clienteService.finfById(idCliente)
				.map(c -> {
					clienteService.delete(idCliente);
					return ResponseEntity.ok(c);
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	
	
	
}
