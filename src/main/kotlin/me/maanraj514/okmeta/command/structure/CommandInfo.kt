package me.maanraj514.okmeta.command.structure

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class CommandInfo(
    val description: String = "",
    val permission: String = "",
    val aliases: Array<String> = [],
    val playerOnly: Boolean = false
)
