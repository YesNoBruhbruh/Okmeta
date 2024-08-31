package me.maanraj514.okmeta.builder

import com.destroystokyo.paper.profile.PlayerProfile
import me.maanraj514.okmeta.utils.toColorizedComponent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.OfflinePlayer
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.LeatherArmorMeta
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.persistence.PersistentDataContainer
import java.util.function.Consumer
import java.util.function.UnaryOperator

class ItemBuilder(private val item: ItemStack) {

    private val meta = item.itemMeta

    constructor(material: Material) : this(ItemStack(material))
    constructor(itemBuilder: ItemBuilder) : this(itemBuilder.item)

    fun name(name: String): ItemBuilder {
        meta.displayName(name.toColorizedComponent())
        return this
    }

    fun name(component: Component): ItemBuilder {
        meta.displayName(component)
        return this
    }

    fun material(material: Material): ItemBuilder {
        item.withType(material)
        return this
    }

    fun lore(lore: List<String>): ItemBuilder {
        meta.lore(lore.map { it.toColorizedComponent() })
        return this
    }

    fun loreLine(lore: String): ItemBuilder {
        val loreList = meta.lore() ?: mutableListOf()
        loreList.add(lore.toColorizedComponent())
        meta.lore(loreList)
        return this
    }

    fun replaceLore(function: UnaryOperator<Component>) : ItemBuilder {
        val loreList = meta.lore() ?: mutableListOf()
        loreList.replaceAll(function)
        meta.lore(loreList)
        return this
    }

    fun replaceLore(placeHolder: String, replacement: String) : ItemBuilder {
        val serializer = PlainTextComponentSerializer.plainText()
        return replaceLore { it: Component -> serializer.serialize(it).replace(placeHolder, replacement).toColorizedComponent()}
    }

    fun addEnchant(enchantment: Enchantment, level: Int, ignoreMinecraftLimit: Boolean): ItemBuilder {
        meta.addEnchant(enchantment, level, ignoreMinecraftLimit)
        return this
    }

    fun removeEnchant(enchantment: Enchantment): ItemBuilder {
        meta.removeEnchant(enchantment)
        return this
    }

    fun addItemFlags(vararg itemFlags: ItemFlag): ItemBuilder {
        meta.addItemFlags(*itemFlags)
        return this
    }

    fun removeItemFlags(vararg itemFlags: ItemFlag): ItemBuilder {
        meta.removeItemFlags(*itemFlags)
        return this
    }

    fun skullOwner(offlinePlayer: OfflinePlayer): ItemBuilder {
        val skullMeta = meta as SkullMeta
        skullMeta.setOwningPlayer(offlinePlayer)
        return this
    }

    fun skullOwner(playerProfile: PlayerProfile): ItemBuilder {
        val skullMeta = meta as SkullMeta
        skullMeta.playerProfile = playerProfile
        return this
    }

    fun customModelData(modelData: Int): ItemBuilder {
        meta.setCustomModelData(modelData)
        return this
    }

    fun persistentData(function: Consumer<PersistentDataContainer>): ItemBuilder {
        function.accept(meta.persistentDataContainer)
        return this
    }

    fun leatherColor(color: Color): ItemBuilder {
        val leatherArmorMeta = meta as LeatherArmorMeta
        leatherArmorMeta.setColor(color)
        return this
    }

    fun glowing(glowing: Boolean): ItemBuilder {
        if (glowing) {
            meta.addEnchant(Enchantment.LUCK_OF_THE_SEA, 1, true)
        } else {
            meta.removeEnchant(Enchantment.LUCK_OF_THE_SEA)
        }
        return this
    }

    fun unbreakable(unbreakable: Boolean): ItemBuilder {
        meta.isUnbreakable = unbreakable
        return this
    }

    fun build(): ItemStack {
        item.setItemMeta(meta)
        return item
    }
}