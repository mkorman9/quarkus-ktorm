package com.github.mkorman9

import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes
import org.jboss.logging.Logger

@ApplicationScoped
class StartupService(
    private val log: Logger,
    private val duckService: DuckService,
    private val catService: CatService
) {
    fun onStartup(@Observes startupEvent: StartupEvent) {
        duckService.addDuck("Daffy", 7)
        duckService.addDuck("Donald", 6)
        duckService.addDuck("Daisy", 6)

        log.info("Ducks: ${duckService.findDucks()}")

        val catGroup = catService.addGroup("Pirates")
        catService.addCat("Garfield", catGroup)
        catService.addCat("Grumpy Cat", catGroup)
        catService.addCat("Salem", catGroup)

        log.info("Cats: ${catService.findCatsInGroup(catGroup.id)}")
    }
}
