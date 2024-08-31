package me.maanraj514.okmeta.command

import org.bukkit.Bukkit

class CommandManager() {

    fun createCoreCommand(
        commandName: String,
        description: String,
        usage: String,
        commandList: CommandList?,
        aliases: List<String>,
        permission: String = "",
        subCommands: List<PluginCommand>
    ) {

        Bukkit.getCommandMap()
            .register(commandName, CoreCommand(commandName, description, usage, commandList, aliases, permission, subCommands))
    }
    fun createCoreCommand(
        commandName: String,
        description: String,
        usage: String,
        commandList: CommandList?,
        subCommands: List<PluginCommand>
    ) {

        createCoreCommand(commandName, description, usage, commandList, listOf(), "", subCommands)
    }
}