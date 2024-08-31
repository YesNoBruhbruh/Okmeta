package me.maanraj514.okmeta.command.structure

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

abstract class PluginCommand(commandName: String) : Command(commandName) {

    private var commandInfo: CommandInfo = javaClass.getDeclaredAnnotation(CommandInfo::class.java)

    init {
        Objects.requireNonNull(commandInfo, "commandInfo cannot be null!")

        description = commandInfo.description
        permission = commandInfo.permission
        aliases = commandInfo.aliases.toList()
    }

    override fun execute(sender: CommandSender, commandLabel: String, args: Array<out String>): Boolean {
        if (commandInfo.permission.isNotEmpty()) {
            if (!sender.hasPermission(commandInfo.permission)) {
                return true
            }
        }

        if (commandInfo.playerOnly) {
            if (sender !is Player) {
                return true
            }
            execute(sender, args)
        }

        execute(sender, args)
        return true
    }

    open fun execute(sender: CommandSender, args: Array<out String>) {}
    open fun execute(player: Player, args: Array<out String>) {}
}