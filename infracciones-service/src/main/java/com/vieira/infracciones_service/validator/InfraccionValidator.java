package com.vieira.infracciones_service.validator;


import com.vieira.infracciones_service.entity.Infraccion;
import com.vieira.infracciones_service.exception.ValidateServiceException;

public class InfraccionValidator {
	public static void save(Infraccion obj) {
		if (obj.getDni()==null || obj.getDni().trim().isEmpty()) {
			throw new ValidateServiceException("El campo DNI es obligatorio");
		}
	}
}
