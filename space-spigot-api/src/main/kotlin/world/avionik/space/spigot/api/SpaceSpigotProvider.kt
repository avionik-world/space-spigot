package world.avionik.space.spigot.api

import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Niklas Nieberler
 */

interface SpaceSpigotProvider {

    /**
     * Gets the java plugin of this project
     * @return [JavaPlugin] instance
     */
    fun getJavaPlugin(): JavaPlugin

}