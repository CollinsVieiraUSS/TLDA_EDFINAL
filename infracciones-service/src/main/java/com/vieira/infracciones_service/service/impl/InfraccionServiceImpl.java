package com.vieira.infracciones_service.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vieira.infracciones_service.entity.Infraccion;
import com.vieira.infracciones_service.exception.GeneralServiceException;
import com.vieira.infracciones_service.exception.NoDataFoundException;
import com.vieira.infracciones_service.exception.ValidateServiceException;
import com.vieira.infracciones_service.repository.InfraccionRepository;
import com.vieira.infracciones_service.service.InfraccionService;
import com.vieira.infracciones_service.validator.InfraccionValidator;

@Service
public class InfraccionServiceImpl implements InfraccionService {

	@Autowired
	private InfraccionRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Infraccion> findAll(Pageable page) {
		try {
			List<Infraccion> data = repository.findAll(page).toList();
			return data;

		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public List<Infraccion> findByDni(String dni, Pageable page) {
		try {
			List<Infraccion> data = repository.findByDni(dni,page);
			return data;
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

	@Override
	@Transactional
	public Infraccion findById(int id) {
		try {
			Infraccion data = repository.findById(id)
					.orElseThrow(() -> new NoDataFoundException("No existe un registro con ese ID"));
			return data;

		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public Infraccion save(Infraccion obj) {
		try {
			InfraccionValidator.save(obj);
			if(obj.getId()==0) {
				Infraccion data = repository.save(obj);
				return data;
			}
			else {
				Infraccion data = findById(obj.getId());
				data.setDni(obj.getDni());
				data.setFecha(obj.getFecha());
				data.setTipoInfraccion(obj.getTipoInfraccion());
				data.setUbicacion(obj.getUbicacion());
				data.setDescripcion(obj.getDescripcion());
				data.setMontoMulta(obj.getMontoMulta());
				data.setEstado(obj.getEstado());							
				return repository.save(data);				
			}
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public boolean delete(int id) {
		try {
			Infraccion data = findById(id);
			repository.delete(data);
			return true;
		} catch (ValidateServiceException | NoDataFoundException e) {
			throw e;
		} catch (Exception e) {
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	
}
