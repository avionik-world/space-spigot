package world.avionik.space.spigot.plugin.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import world.avionik.space.spigot.api.extension.isMoving
import world.avionik.space.spigot.api.extension.lastMoving

/**
 * @author Niklas Nieberler
 */

class PlayerMoveListener : Listener {

    @EventHandler
    fun handleMove(event: PlayerMoveEvent) {
        val player = event.player

        val toLocation = event.to
        val fromLocation = event.from

        player.isMoving = (fromLocation.x != toLocation.x || fromLocation.y != toLocation.y || fromLocation.z != toLocation.z)
        player.lastMoving = System.currentTimeMillis()
    }

}