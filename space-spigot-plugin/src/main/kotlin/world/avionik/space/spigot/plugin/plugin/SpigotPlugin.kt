package world.avionik.space.spigot.plugin.plugin

import org.bukkit.Bukkit
import org.bukkit.plugin.ServicePriority
import org.bukkit.plugin.java.JavaPlugin
import world.avionik.kotlin.paper.register
import world.avionik.space.spigot.api.SpaceSpigotProvider
import world.avionik.space.spigot.plugin.api.SpaceSpigotProviderImpl

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

}