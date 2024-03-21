package world.avionik.space.spigot.api.player

import java.util.concurrent.ConcurrentHashMap

@Suppress("UNCHECKED_CAST")
class PlayerData {

    private val map = ConcurrentHashMap<String, Any>()

    fun <T> get(key: String): T {
        return this.map[key] as T
    }

    fun <T> getOrNull(key: String): T? {
        return this.map[key] as T?
    }

    fun exist(key: String): Boolean {
        return this.map.containsKey(key)
    }

    fun set(key: String, value: Any) {
        this.map[key] = value
    }

    fun remove(key: String) {
        this.map.remove(key)
    }

    fun addInt(key: String, value: Int) {
        this.map[key]?.let {
            this.set(key, (this.map[key] as Int) + value)
        }
    }

}