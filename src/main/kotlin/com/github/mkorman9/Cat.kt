package com.github.mkorman9

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.timestamp
import org.ktorm.schema.uuid
import org.ktorm.schema.varchar
import java.time.Instant
import java.util.UUID

interface CatGroup : Entity<CatGroup> {
    var id: UUID
    var name: String
    var createdAt: Instant
}

object CatGroupTable : Table<CatGroup>("cat_groups") {
    val id = uuid("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val createdAt = timestamp("created_at").bindTo { it.createdAt }
}

interface Cat : Entity<Cat> {
    var id: UUID
    var name: String
    var createdAt: Instant
    var group: CatGroup?
}

object CatTable : Table<Cat>("cats") {
    val id = uuid("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val createdAt = timestamp("created_at").bindTo { it.createdAt }
    val groupId = uuid("group_id").references(CatGroupTable) { it.group }
}
