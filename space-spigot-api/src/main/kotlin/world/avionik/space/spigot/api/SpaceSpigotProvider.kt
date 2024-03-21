package world.avionik.space.spigot.api

import org.bukkit.plugin.java.JavaPlugin
import world.avionik.space.spigot.api.task.manager.TaskCacheManager

/**
 * @author Niklas Nieberler
 */

interface SpaceSpigotProvider {

    /**
     * Gets the task cache manager
     * @return instance of [TaskCacheManager]
     */
    fun getTaskCacheManager(): TaskCacheManager

    /**
     * Gets the java plugin of this project
     * @return [JavaPlugin] instance
     */
    fun getJavaPlugin(): JavaPlugin

}