package com.aat.itsdoc.mongo.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class MongoDao<T, ID extends Serializable> {
	@Autowired
	private MongoTemplate mongoTemplate;

	public void save(Object obj) {
		mongoTemplate.save(obj);
	}

	public void delete(Object obj) {
		mongoTemplate.remove(obj);
	}

	public void deleteById(Serializable id, Class<T> clazz) {
		mongoTemplate.remove(getIdQuery(id), clazz);
	}

	public <S> int update(Query query, Update update, Class<T> clazz) {
		return mongoTemplate.updateFirst(query, update, clazz).getN();
	}

	public <S> T findOne(Class<T> clazz, Query query) {
		return mongoTemplate.findOne(query, clazz);
	}

	public <S> T findById(Class<T> clazz, Object id) {
		return mongoTemplate.findById(id, clazz);
	}

	public <S> List<T> findAll(Class<T> clazz) {
		return mongoTemplate.findAll(clazz);
	}

	public <S> List<T> find(Class<T> clazz, Query query) {
		return mongoTemplate.find(query, clazz);
	}

	public <S> List<T> findList(Class<T> clazz, Query query, int currentPage, int pageSize) {
		// 计算起始位置
		int startIndex = ((currentPage - 1) < 0 ? 0 : (currentPage - 1)) * pageSize;
		query.skip(startIndex);
		query.limit(pageSize);
		return mongoTemplate.find(query, clazz);
	}

	public <S> long findCount(Class<T> clazz, Query query) {
		return mongoTemplate.count(query, clazz);
	}

	protected Query getIdQuery(Serializable id) {
		return Query.query(getIdCriteria(id));
	}

	protected Criteria getIdCriteria(Serializable id) {
		return Criteria.where("id").is(id);
	}

}
