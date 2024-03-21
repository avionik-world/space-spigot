package world.avionik.space.spigot.plugin.api

import org.bukkit.plugin.java.JavaPlugin
import world.avionik.space.spigot.api.SpaceSpigotProvider
import world.avionik.space.spigot.api.task.manager.TaskCacheManager

/**
 * @author Niklas Nieberler
 */

class SpaceSpigotProviderImpl(
    private val javaPlugin: JavaPlugin
) : SpaceSpigotProvider {

    private val taskCacheManager = TaskCacheManager()

    override fun getTaskCacheManager(): TaskCacheManager  = this.taskCacheManager

    override fun getJavaPlugin(): JavaPlugin = this.javaPlugin

}