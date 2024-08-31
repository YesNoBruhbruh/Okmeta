package me.maanraj514.okmeta.command

import org.bukkit.command.CommandSender

@FunctionalInterface
interface CommandList {

    fun displayCommandList(sender: CommandSender, commandList: List<PluginCommand>)
}