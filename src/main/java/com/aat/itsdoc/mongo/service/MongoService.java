package com.aat.itsdoc.mongo.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public interface MongoService {
	Object save(Object obj);

	void delete(Object obj);

	<T> void deleteById(Serializable id, Class<T> clazz);

	<T> int update(Query query, Update update, Class<T> clazz);

	<T> T findOne(Class<T> clazz, Query query);

	<T> T findById(Class<T> clazz, Object id);

	<T> List<T> findAll(Class<T> clazz);

	<T> List<T> find(Class<T> clazz, Query query);

	<T> List<T> findList(Class<T> clazz, Query query, int currentPage, int pageSize);

	<T> long findCount(Class<T> clazz, Query query);
}
