package com.aadd.ydc.vo.json;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class EjemploJson {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fecha;

}
