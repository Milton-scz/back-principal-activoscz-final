package com.milton.activoscz.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.milton.activoscz.Model.Categoria;

public interface CategoriaRepository  extends MongoRepository<Categoria, String>{
	Categoria findBy_id(String _id);
}