package world.avionik.space.spigot.api.extension

import org.bukkit.Bukkit
import world.avionik.kotlin.paper.load
import world.avionik.space.spigot.api.SpaceSpigotProvider

/**
 * @author Niklas Nieberler
 */

fun sync(function: () -> Unit) {
    val provider = Bukkit.getServicesManager().load<SpaceSpigotProvider>()
        ?: throw IllegalArgumentException("failed to find SpaceSpigotProvider")
    Bukkit.getScheduler().callSyncMethod(provider.getJavaPlugin(), function)
}