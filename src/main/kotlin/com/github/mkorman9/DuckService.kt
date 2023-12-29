package com.github.mkorman9

import com.fasterxml.uuid.Generators
import jakarta.enterprise.context.ApplicationScoped
import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.insert
import org.ktorm.dsl.map
import org.ktorm.dsl.select
import java.time.Instant
import java.util.UUID

data class Duck(
    val id: UUID,
    val name: String,
    val height: Int,
    val createdAt: Instant
)

@ApplicationScoped
class DuckService(
    private val db: Database
) {
    fun findDucks(): List<Duck> {
        return db
            .from(DuckTable)
            .select()
            .map { row ->
                Duck(
                    id = row[DuckTable.id]!!,
                    name = row[DuckTable.name]!!,
                    height = row[DuckTable.height]!!,
                    createdAt = row[DuckTable.createdAt]!!
                )
            }
    }

    fun addDuck(name: String, height: Int) {
        db.insert(DuckTable) {
            set(it.id, ID_GENERATOR.generate())
            set(it.name, name)
            set(it.height, height)
            set(it.createdAt, Instant.now())
        }
    }

    companion object {
        private val ID_GENERATOR = Generators.timeBasedEpochGenerator()
    }
}