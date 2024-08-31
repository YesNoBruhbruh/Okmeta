package me.maanraj514.okmeta.utils.location

import org.bukkit.Bukkit
import org.bukkit.Location

fun Location.setToDefault() {
    world = Bukkit.getWorlds()[0]
    x = 0.0
    y = 0.0
    z = 0.0
}
object LocationUtil {

    fun isLocationSame(locationA: Location, locationB: Location): Boolean {
        return locationA.x == locationB.x &&
                locationA.y == locationB.y &&
                locationA.z == locationB.z
    }

    fun locationToString(location: Location): String {
        return "${location.world.name}, ${location.x}, ${location.y}, ${location.z}"
    }

    fun stringToLocation(string: String): Location {
        val args = string.split(", ")
        val world = Bukkit.getWorld(args[0])
        val x = args[1].toDouble()
        val y = args[2].toDouble()
        val z = args[3].toDouble()

        return Location(world, x, y, z)
    }
}