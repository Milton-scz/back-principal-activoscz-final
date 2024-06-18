package com.milton.activoscz.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.milton.activoscz.Model.Activo;

public interface ActivoRepository  extends MongoRepository<Activo, String>{
	Activo findBy_id(String activoId);
}