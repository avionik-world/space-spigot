package world.avionik.space.spigot.plugin.api

import org.bukkit.plugin.java.JavaPlugin
import world.avionik.space.spigot.api.SpaceSpigotProvider

/**
 * @author Niklas Nieberler
 */

class SpaceSpigotProviderImpl(
    private val javaPlugin: JavaPlugin
) : SpaceSpigotProvider {

    override fun getJavaPlugin(): JavaPlugin = this.javaPlugin

}