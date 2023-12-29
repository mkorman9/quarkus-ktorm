package com.github.mkorman9

import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Produces
import jakarta.inject.Singleton
import org.ktorm.database.Database
import javax.sql.DataSource

@ApplicationScoped
class DatabaseProvider {
    @Singleton
    @Produces
    fun database(dataSource: DataSource): Database {
        return Database.connect(dataSource)
    }
}
