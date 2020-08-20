package com.consdata.heimdall.dependnecies

import org.hibernate.search.jpa.Search
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import javax.persistence.EntityManager
import javax.transaction.Transactional


@Component
@Transactional
class DependencySearchService(private val entityManager: EntityManager) {

    @Autowired
    private lateinit var self: DependencySearchService

    @PostConstruct
    fun init() {
        self.initHibernateSearch()
    }

    fun initHibernateSearch() {
        val fullTextEntityManager = Search.getFullTextEntityManager(entityManager)
        fullTextEntityManager.createIndexer().startAndWait()
    }

    fun search(term: String): List<DependencyEntity> {
        val fullTextEntityManager = Search.getFullTextEntityManager(entityManager)
        val queryBuilder = fullTextEntityManager
                .searchFactory
                .buildQueryBuilder()
                .forEntity(DependencyEntity::class.java).get()
        val luceneQuery = queryBuilder
                .simpleQueryString()
                .onFields("scope", "groupId", "artifactId")
                .withAndAsDefaultOperator()
                .matching(term)
                .createQuery()

        val jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, DependencyEntity::class.java)
        return jpaQuery.resultList as List<DependencyEntity>
    }

}
