package com.aadd.ydc.vo.hibernate;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "NOMBRE_EN_LA_BBDD")
public class ClasePersistencia {

	@Id
	@Column(name = "id_Ejemplo")
	private int ejId;

	@Column(name = "nombre")
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "objeto")
	private Object fkDeAClase;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha")
	private Date fecha;

}
