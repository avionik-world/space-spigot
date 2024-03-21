package world.avionik.space.spigot.plugin.plugin

import org.bukkit.Bukkit
import org.bukkit.plugin.ServicePriority
import org.bukkit.plugin.java.JavaPlugin
import world.avionik.kotlin.paper.register
import world.avionik.space.spigot.api.SpaceSpigotProvider
import world.avionik.space.spigot.api.event.ServerTickEvent
import world.avionik.space.spigot.api.task.task
import world.avionik.space.spigot.plugin.api.SpaceSpigotProviderImpl
import world.avionik.space.spigot.plugin.listener.ServerTickListener

/**
 * @author Niklas Nieberler
 */

class SpigotPlugin : JavaPlugin() {

    override fun onLoad() {
        val spaceSpigotProvider = SpaceSpigotProviderImpl(this)
        Bukkit.getServicesManager().register<SpaceSpigotProvider>(
            spaceSpigotProvider,
            this,
            ServicePriority.Highest
        )
    }

    override fun onEnable() {
        val pluginManager = server.pluginManager

        task {
            pluginManager.callEvent(ServerTickEvent())
        }.runTaskTimer(0, 1)

        pluginManager.registerEvents(ServerTickListener(), this)
    }

}