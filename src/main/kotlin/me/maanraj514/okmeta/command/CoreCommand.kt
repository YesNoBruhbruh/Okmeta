package me.maanraj514.okmeta.command

import me.maanraj514.okmeta.utils.sendColorizedMessage
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import java.util.*

class CoreCommand(
    private val name: String,
    private val commandDescription: String,
    private val commandUsage: String,
    private val commandList: CommandList?,
    private val commandAliases: List<String> = mutableListOf(),
    private val permission: String = "",
    private val subCommands: List<PluginCommand>
) : Command(name) {

    init {
        usage = commandUsage
        description = commandDescription
        aliases = commandAliases
    }

    override fun execute(sender: CommandSender, commandLabel: String, args: Array<out String>): Boolean {
        if (args.isNotEmpty()) {
            val commandName = args[0]

            for (command in subCommands) {
                // if args0 is a command, and can be an alias also.
                if (commandName == command.name || (command.aliases.isNotEmpty() && command.aliases.contains(commandName))) {
                    command.execute(sender, commandLabel, args)
                    return true
                }
            }
        } else if (commandList == null) {
            sender.sendColorizedMessage("<gold>================</gold>")
            for (command in subCommands) {
                sender.sendColorizedMessage("<white>${command.name} - ${command.description}</white>")
            }
            sender.sendColorizedMessage("<gold>================</gold>")
        } else {
            commandList.displayCommandList(sender, subCommands)
        }
        return true
    }

    override fun tabComplete(sender: CommandSender, alias: String, args: Array<out String>): MutableList<String> {
        if (args.size == 1) {
            val subCommandArgs = mutableListOf<String>()

            for (command in subCommands) {
                subCommandArgs.add(command.name)
            }

            return subCommandArgs
        }

        return Collections.emptyList()
    }
}