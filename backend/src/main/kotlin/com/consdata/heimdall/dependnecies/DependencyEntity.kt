package com.consdata.heimdall.dependnecies

import org.hibernate.search.annotations.Field
import org.hibernate.search.annotations.Indexed
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
@Indexed
data class DependencyEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
        @Field
        var scope: String,
        @Field
        var groupId: String,
        @Field
        var artifactId: String
)
