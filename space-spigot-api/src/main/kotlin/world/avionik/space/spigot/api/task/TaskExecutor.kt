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

class TaskExecutor(
    private val task: Task,
    private val javaPlugin: JavaPlugin,
    private val value: String = UUID.randomUUID().toString()
) {

    fun runTask() {
        object : BukkitRunnable() {
            override fun run() {
                task.run(TaskEdit(value) { runTask() })
                removeSchedulerFromCache()
            }
        }.value(this.value).runTask(this.javaPlugin)
    }

    fun runTaskAsynchronously() {
        object : BukkitRunnable() {
            override fun run() {
                task.run(TaskEdit(value) { runTaskAsynchronously() })
                removeSchedulerFromCache()
            }
        }.value(this.value).runTaskAsynchronously(this.javaPlugin)
    }

    fun runTaskLater(delay: Long) {
        object : BukkitRunnable() {
            override fun run() {
                task.run(TaskEdit(value) { runTaskLater(delay) })
                removeSchedulerFromCache()
            }
        }.value(this.value).runTaskLater(this.javaPlugin, delay)
    }

    fun runTaskLaterAsynchronously(delay: Long) {
        object : BukkitRunnable() {
            override fun run() {
                task.run(TaskEdit(value) { runTaskLaterAsynchronously(delay) })
                removeSchedulerFromCache()
            }
        }.value(this.value).runTaskLaterAsynchronously(this.javaPlugin, delay)
    }

    fun runTaskTimer(delay: Long, period: Long) {
        object : BukkitRunnable() {
            override fun run() {
                task.run(TaskEdit(value) { runTaskTimer(delay, period) })
            }
        }.value(this.value).runTaskTimer(this.javaPlugin, delay, period)
    }

    fun runTaskTimerAsynchronously(delay: Long, period: Long) {
        object : BukkitRunnable() {
            override fun run() {
                task.run(TaskEdit(value) { runTaskTimerAsynchronously(delay, period) })
            }
        }.value(this.value).runTaskTimerAsynchronously(this.javaPlugin, delay, period)
    }

    private fun removeSchedulerFromCache() {
        val provider = Bukkit.getServicesManager().load<SpaceSpigotProvider>()
            ?: throw IllegalArgumentException("failed to find SpaceSpigotProvider")
        provider.getTaskCacheManager().removeRunnable(this.value)
    }

}