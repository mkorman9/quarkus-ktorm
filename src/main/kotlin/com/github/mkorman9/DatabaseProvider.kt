package com.github.mkorman9

import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Produces
import jakarta.inject.Singleton
import org.ktorm.database.Database
import org.ktorm.support.postgresql.PostgreSqlDialect
import javax.sql.DataSource

@ApplicationScoped
class DatabaseProvider {
    @Singleton
    @Produces
    fun database(dataSource: DataSource): Database = Database.connect(
        dataSource,
        dialect = PostgreSqlDialect()
    )
}
