package world.avionik.space.spigot.api.extension

import org.bukkit.entity.Player
import world.avionik.space.spigot.api.player.PlayerData
import world.avionik.space.spigot.api.player.PlayerDataStore

/**
 * @author Niklas Nieberler
 */

/**
 * @return a [PlayerData] object which can hold
 * arbitrary data from different extensions.
 */
fun Player.data(): PlayerData {
    val data = PlayerDataStore.data[this]
    if (data == null) {
        PlayerDataStore.data[this] = PlayerData()
    }
    return PlayerDataStore.data[this]!!
}

private val movingMap = mutableMapOf<Player, Boolean>()
private val lastMovingMap = mutableMapOf<Player, Long>()

var Player.isMoving: Boolean
    get() = movingMap[this] ?: false
    set(value) {
        movingMap[this] = value
    }

var Player.lastMoving: Long
    get() = lastMovingMap[this] ?: 0
    set(value) {
        lastMovingMap[this] = value
    }
