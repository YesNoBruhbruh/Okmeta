package me.maanraj514.okmeta.utils

import net.kyori.adventure.audience.Audience
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

fun Audience.sendColorizedMessage(msg: String) {
    sendMessage(MiniMessage.miniMessage().deserialize(msg))
}
fun Component.string(): String {
    return MiniMessage.miniMessage().serialize(this)
}
fun String.toColorizedComponent() : Component {
    return MiniMessage.miniMessage().deserialize(this)
}
object MessageUtil {
}