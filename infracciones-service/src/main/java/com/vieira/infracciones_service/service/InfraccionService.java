package com.vieira.infracciones_service.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.vieira.infracciones_service.entity.Infraccion;

public interface InfraccionService {
	public List<Infraccion> findAll(Pageable page);
	public List<Infraccion> findByDni(String dni, Pageable page);
	public Infraccion findById(int id);
	public Infraccion save(Infraccion obj);
	public boolean delete(int id);
}
