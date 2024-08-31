package me.maanraj514.okmeta.utils.location

import org.bukkit.Location
import kotlin.math.max
import kotlin.math.min

class Cuboid(private var min: Location, private var max: Location) {

    private var minX = 0
    private var maxX = 0
    private var minY = 0
    private var maxY = 0
    private var minZ = 0
    private var maxZ = 0

    fun assignCorrectBounds() {
        maxX = max(max.blockX, min.blockX)
        minX = min(max.blockX, min.blockX)

        maxY = max(max.blockY, min.blockY)
        minY = min(max.blockY, min.blockY)

        maxZ = max(max.blockZ, min.blockZ)
        minZ = min(max.blockZ, min.blockZ)

        min = Location(min.world, minX.toDouble(), minY.toDouble(), minZ.toDouble())
        max = Location(max.world, maxX.toDouble(), maxY.toDouble(), maxZ.toDouble())
    }

    fun isInBounds(location: Location): Boolean {
        return (location.blockX in minX..maxX) && (location.y <= maxY && location.y >= minY) && (location.blockZ in minZ..maxZ)
    }

    fun getCenter(): Location {
        return Location(min.world, (minX + maxX) / 2.0, (minY + maxY) / 2.0, (minZ + maxZ) / 2.0)
    }

    fun locationsFromTwoPoints(): List<Location> {
        val locations: MutableList<Location> = ArrayList()

        val topBlockX: Int = min.blockX.coerceAtLeast(max.blockX)
        val bottomBlockX: Int = min.blockX.coerceAtMost(max.blockX)

        val topBlockY: Int = min.blockY.coerceAtLeast(max.blockY)
        val bottomBlockY: Int = min.blockY.coerceAtMost(max.blockY)

        val topBlockZ: Int = min.blockZ.coerceAtLeast(max.blockZ)
        val bottomBlockZ: Int = min.blockZ.coerceAtMost(max.blockZ)

        for (x in bottomBlockX..topBlockX + 1) {
            for (z in bottomBlockZ..topBlockZ + 1) {
                for (y in bottomBlockY..topBlockY + 1) {
                    locations.add(min.world.getBlockAt(x, y, z).location)
                }
            }
        }

        return locations
    }
}