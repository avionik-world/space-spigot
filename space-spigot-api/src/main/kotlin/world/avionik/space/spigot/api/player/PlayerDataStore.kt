package world.avionik.space.spigot.api.player

import org.bukkit.entity.Player
import java.util.*

object PlayerDataStore {

    // use weak hash map, because
    // now we don't need to care about
    // removing entries when players
    // leave the server
    val data = WeakHashMap<Player, PlayerData>()

}