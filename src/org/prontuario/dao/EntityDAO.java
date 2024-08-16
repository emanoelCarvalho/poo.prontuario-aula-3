package org.prontuario.dao;

import java.util.List;

public interface EntityDAO <E>{
	
	void save(E e); 
	E findById(Long id);
	void update(E e);
	void delete(E e);
	List<E> findAll();
	
	
}
