/**
 * 
 */
package com.upic.common.support;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhailiang
 *
 */
public class RepositoryImpl<T> extends SimpleJpaRepository<T, Long> {

	public RepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
	}
	
	@Override
	@Transactional
	public <S extends T> S save(S entity) {
		System.out.println("保存了:"+entity.getClass().getSimpleName());
		return super.save(entity);
	}

}
