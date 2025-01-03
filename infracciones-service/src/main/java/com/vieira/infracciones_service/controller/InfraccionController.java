package com.vieira.infracciones_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vieira.infracciones_service.entity.Infraccion;
import com.vieira.infracciones_service.service.InfraccionService;

@RestController
@RequestMapping("/v1/infracciones")
public class InfraccionController {

	@Autowired
	private InfraccionService service;

	@GetMapping
	public ResponseEntity<List<Infraccion>> getInfracciones(
			@RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<Infraccion> data = service.findAll(page);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Infraccion> create(@RequestBody Infraccion data) {
		Infraccion dat = service.save(data);
		return new ResponseEntity<>(dat, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Infraccion> getById(@PathVariable("id") int id){
		Infraccion dato = service.findById(id);
		return new ResponseEntity<>(dato,HttpStatus.OK);
	}
	
	@GetMapping("/usuario/{dni}")
	public ResponseEntity<?> getByDni(
			@PathVariable("dni") String dni,
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<Infraccion> data = service.findByDni(dni, page);
        return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Infraccion> update(@PathVariable("id") int id, @RequestBody Infraccion reg) {
		Infraccion dat = service.save(reg);
		return new ResponseEntity<>(dat, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		service.delete(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}

