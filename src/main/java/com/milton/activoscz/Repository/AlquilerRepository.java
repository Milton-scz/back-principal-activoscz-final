package com.milton.activoscz.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.milton.activoscz.Model.Alquiler;

public interface AlquilerRepository  extends MongoRepository<Alquiler, Long>{
	Alquiler findByAlquilerId(Long alquilerId);
}