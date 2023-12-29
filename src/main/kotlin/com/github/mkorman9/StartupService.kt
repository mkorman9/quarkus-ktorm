package com.github.mkorman9

import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import org.jboss.logging.Logger

@ApplicationScoped
class StartupService(
    private val log: Logger,
    private val duckService: DuckService
) {
    fun onStartup(@Observes startupEvent: StartupEvent) {
        duckService.addDuck("Daffy", 7)
        duckService.addDuck("Donald", 6)
        duckService.addDuck("Daisy", 6)

        log.info("Ducks: ${duckService.findDucks()}")
    }
}
