package de.airblocks.teamfive.base.commands

import de.airblocks.teamfive.base.player.GamePlayer
import net.minestom.server.MinecraftServer
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.suggestion.SuggestionEntry

object TestCommand: Command("test") {

    init {
        val argument = ArgumentType.UUID("playername").setSuggestionCallback { commandSender, commandContext, suggestion ->
            MinecraftServer.getConnectionManager().onlinePlayers.forEach {
                suggestion.addEntry(SuggestionEntry(it.uuid.toString()))
            }
        }

        addSyntax({sender, context ->
            val uuid = context.get(argument)
            val gamePlayer = MinecraftServer.getConnectionManager().getOnlinePlayerByUuid(uuid) as GamePlayer

            gamePlayer.update()
            sender.sendMessage("Hello, ${gamePlayer.name} got saved!")
        }, argument)
    }
}