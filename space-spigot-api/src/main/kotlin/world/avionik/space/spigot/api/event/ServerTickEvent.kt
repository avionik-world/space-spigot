package world.avionik.space.spigot.api.event

import org.bukkit.event.Event
import org.bukkit.event.HandlerList

/**
 * @author Niklas Nieberler
 */

// This event is executed every tick
class ServerTickEvent : Event() {

    companion object {
        private val handlerList = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList = handlerList
    }

    override fun getHandlers(): HandlerList = handlerList

}