package world.avionik.space.spigot.api.task

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import world.avionik.kotlin.paper.load
import world.avionik.space.spigot.api.SpaceSpigotProvider
import java.util.*

/**
 * @author Niklas Nieberler
 */

/**
 * Creates a new bukkit task
 * @param value for the task
 * @param javaPlugin for the scheduler
 * @param task to execute
 */
fun task(value: String, javaPlugin: JavaPlugin, task: Task): TaskExecutor {
    return TaskExecutor(task, javaPlugin, value)
}

/**
 * Creates a new bukkit task
 * @param value for the task
 * @param task to execute
 */
fun task(value: String, task: Task): TaskExecutor {
    val provider = Bukkit.getServicesManager().load<SpaceSpigotProvider>()
        ?: throw IllegalArgumentException("failed to find SpaceSpigotProvider")
    return TaskExecutor(task, provider.getJavaPlugin(), value)
}

/**
 * Creates a new bukkit task
 * @param task to execute
 */
fun task(task: Task): TaskExecutor {
    val provider = Bukkit.getServicesManager().load<SpaceSpigotProvider>()
        ?: throw IllegalArgumentException("failed to find SpaceSpigotProvider")
    return TaskExecutor(task, provider.getJavaPlugin(), UUID.randomUUID().toString())
}

/**
 * Sets a value th an [BukkitRunnable]
 * @param value to set to the runnable
 */
fun BukkitRunnable.value(value: String): BukkitRunnable {
    val provider = Bukkit.getServicesManager().load<SpaceSpigotProvider>()
        ?: throw IllegalArgumentException("failed to find SpaceSpigotProvider")
    provider.getTaskCacheManager().setRunnable(value, this)
    return this
}
