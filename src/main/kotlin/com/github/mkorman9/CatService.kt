package com.github.mkorman9

import com.fasterxml.uuid.Generators
import jakarta.enterprise.context.ApplicationScoped
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.Entity
import org.ktorm.entity.add
import org.ktorm.entity.filter
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList
import java.time.Instant
import java.util.UUID

@ApplicationScoped
class CatService(
    private val db: Database
) {
    fun addGroup(name: String): CatGroup {
        val group = Entity.create<CatGroup>().apply {
            this.id = ID_GENERATOR.generate()
            this.name = name
            this.createdAt = Instant.now()
        }
        db.sequenceOf(CatGroupTable).add(group)
        return group
    }

    fun addCat(name: String, group: CatGroup) {
        db.sequenceOf(CatTable).add(
            Entity.create<Cat>().apply {
                this.id = ID_GENERATOR.generate()
                this.name = name
                this.createdAt = Instant.now()
                this.group = group
            }
        )
    }

    fun findCatsInGroup(groupId: UUID): List<Cat> {
        return db.sequenceOf(CatTable)
            .filter { it.groupId eq groupId }
            .toList()
    }

    companion object {
        private val ID_GENERATOR = Generators.timeBasedEpochGenerator()
    }
}
