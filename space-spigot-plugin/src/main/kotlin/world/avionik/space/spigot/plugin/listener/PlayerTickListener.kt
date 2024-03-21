package world.avionik.space.spigot.plugin.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import world.avionik.space.spigot.api.event.PlayerTickEvent
import world.avionik.space.spigot.api.extension.isMoving
import world.avionik.space.spigot.api.extension.lastMoving

/**
 * @author Niklas Nieberler
 */

class PlayerTickListener : Listener {

    @EventHandler
    fun handleTick(event: PlayerTickEvent) {
        val player = event.player

        if (player.lastMoving < (System.currentTimeMillis() - 600)) {
            player.isMoving = false
        }
    }

}