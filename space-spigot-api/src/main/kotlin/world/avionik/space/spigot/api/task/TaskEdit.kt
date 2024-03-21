package world.avionik.space.spigot.api.task

import org.bukkit.Bukkit
import world.avionik.kotlin.paper.load
import world.avionik.space.spigot.api.SpaceSpigotProvider

/**
 * @author Niklas Nieberler
 */

class TaskEdit(
    private val value: String,
    private val repeatFunction: () -> Unit
) {

    /**
     * Cancels this task
     */
    fun cancel() {
        val provider = Bukkit.getServicesManager().load<SpaceSpigotProvider>()
            ?: throw IllegalArgumentException("failed to find SpaceSpigotProvider")
        provider.getTaskCacheManager().removeRunnable(this.value)
    }

    /**
     * Repeats this task again
     */
    fun repeat() {
        this.repeatFunction()
    }

}