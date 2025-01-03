package com.vieira.infracciones_service.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "infraccion")
@EntityListeners(AuditingEntityListener.class)
public class Infraccion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="dni", nullable = false, length=8)
	private String dni;
	@Column(name="fecha",nullable = false, length=64)
	private Date fecha;
	@Column(name="tipo_infraccion",nullable = false, length=100)
	private String tipoInfraccion;
	@Column(name="ubicacion",nullable = false, length=200)
	private String ubicacion;
	@Column(name="descripcion",nullable = false, length=250)
	private String descripcion;
	@Column(name="monto_multa",nullable = false, length=20)
	private Double montoMulta;
	@Column(name="estado",nullable = false, length=20)
	private String estado;
	
	
	
	@Column(name="created_at",nullable = false,updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	@Column(name="updated_at",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;
}
