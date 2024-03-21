package world.avionik.space.spigot.api.task.manager

import com.google.common.collect.HashBiMap
import org.bukkit.scheduler.BukkitRunnable

/**
 * @author Niklas Nieberler
 */

class TaskCacheManager {

    private val runnableMap = HashBiMap.create<String, BukkitRunnable>()

    /**
     * Set a value to the [BukkitRunnable]
     * @param value the new runnable value
     * @param bukkitRunnable the bukkitRunnable that gets the value
     */
    fun setRunnable(value: String, bukkitRunnable: BukkitRunnable) {
        this.runnableMap[value] = bukkitRunnable
    }

    /**
     * Gets [BukkitRunnable] from the value
     * @param value the runnable value
     * @return instance of [BukkitRunnable]
     */
    fun getRunnableByValue(value: String): BukkitRunnable? {
        return getRunnables()[value]
    }

    /**
     * Gets the value from a runnable
     * @param bukkitRunnable the runnable
     */
    fun getRunnableValueByRunnable(bukkitRunnable: BukkitRunnable): String? {
        return getRunnables().inverse()[bukkitRunnable]
    }

    /**
     * Gets a HashBiMap of all runnables
     */
    fun getRunnables(): HashBiMap<String, BukkitRunnable> = this.runnableMap

    /**
     * Gets true when a runnable exist with the value
     * @param value the value of a runnable
     */
    fun existRunnableWithValue(value: String): Boolean {
        return this.runnableMap.containsKey(value)
    }

    /**
     * Gets a runnable with a value
     * @param value the runnable value
     */
    fun removeRunnable(value: String) {
        if (!existRunnableWithValue(value))
            return
        getRunnableByValue(value)?.cancel()
        this.runnableMap.remove(value)
    }

}