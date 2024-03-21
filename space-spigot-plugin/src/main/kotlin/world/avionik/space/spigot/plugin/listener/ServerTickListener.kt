package world.avionik.space.spigot.plugin.listener

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import world.avionik.space.spigot.api.event.*

/**
 * @author Niklas Nieberler
 */

class ServerTickListener : Listener {

    @EventHandler
    fun handleServerTick(event: ServerTickEvent) {
        Bukkit.getOnlinePlayers().forEach {
            Bukkit.getPluginManager().callEvent(PlayerTickEvent(it))
        }
    }

}