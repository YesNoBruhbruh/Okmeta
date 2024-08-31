package me.maanraj514.okmeta.builder

import me.maanraj514.okmeta.utils.toColorizedComponent
import org.bukkit.Location
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.potion.PotionEffect

class EntityBuilder(
    private val entityType: EntityType,
    private val location : Location
) {

    constructor(entity: Entity) : this(entity.type, entity.location)

    // Entity stuff
    private var name: String = ""
    private var glowing: Boolean = false
    private var gravity: Boolean = true
    private var invincible: Boolean = false
    private var invisible: Boolean = false
    private var nameVisible: Boolean = false

    // LivingEntity stuff
    private var health: Double = 10.0
    private var potionEffects: MutableList<PotionEffect> = mutableListOf()
    private var attributes: MutableMap<Attribute, Double> = mutableMapOf()
    private var move: Boolean = true
    private var pickupItems: Boolean = false

    fun name(name: String): EntityBuilder {
        this.name = name
        return this
    }

    fun glowing(glowing: Boolean): EntityBuilder {
        this.glowing = glowing
        return this
    }

    fun gravity(gravity: Boolean): EntityBuilder {
        this.gravity = gravity
        return this
    }

    fun invincible(invincible: Boolean): EntityBuilder {
        this.invincible = invincible
        return this
    }

    fun invisible(invisible: Boolean): EntityBuilder {
        this.invisible = invisible
        return this
    }

    fun nameVisible(nameVisible: Boolean): EntityBuilder {
        this.nameVisible = nameVisible
        return this
    }

    fun health(health: Double): EntityBuilder {
        this.health = health
        return this
    }

    fun potionEffects(potionEffects: MutableList<PotionEffect>): EntityBuilder {
        this.potionEffects = potionEffects
        return this
    }

    fun attributes(attributes: MutableMap<Attribute, Double>): EntityBuilder {
        this.attributes = attributes
        return this
    }

    fun attribute(attribute: Attribute, baseValue: Double): EntityBuilder {
        attributes[attribute] = baseValue
        return this
    }

    fun move(move: Boolean): EntityBuilder {
        this.move = move
        return this
    }

    fun pickupItems(pickupItems: Boolean): EntityBuilder {
        this.pickupItems = pickupItems
        return this
    }

    fun spawn() {
        val entity = location.world.spawnEntity(location, entityType)
        entity.customName(name.toColorizedComponent())
        entity.isGlowing = glowing
        entity.setGravity(gravity)
        entity.isInvulnerable = invincible
        entity.isInvisible = invisible
        entity.isCustomNameVisible = nameVisible

        if (entity is LivingEntity) {
            entity.health = health
            entity.addPotionEffects(potionEffects)
            for (attribute in attributes) {
                entity.getAttribute(attribute.key)?.baseValue = attribute.value
            }
            entity.setAI(move)
            entity.canPickupItems = pickupItems
        }
    }
}