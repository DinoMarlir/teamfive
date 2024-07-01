package de.airblocks.teamfive.lobby.commands

import de.airblocks.teamfive.lobby.LobbyServer
import net.kyori.adventure.text.Component
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.suggestion.SuggestionEntry

class QueueCommand: Command("queue") {

    init {
        addSubcommand(object : Command("list") {
            init {
                setDefaultExecutor { sender, context ->
                    sender.sendMessage("List of queues:")
                    LobbyServer.queues.values.forEach {
                        sender.sendMessage(Component.text("- ").append(it.name))
                    }
                }
            }
        })

        addSubcommand(object : Command("forceStartGame") {
            init {
                val nameArgument = ArgumentType.String("name").setSuggestionCallback { commandSender, commandContext, suggestion ->
                    LobbyServer.queues.forEach { queue ->
                        suggestion.addEntry(SuggestionEntry(queue.value.simpleName, queue.key.description))
                    }
                }

                addSyntax({ sender, context ->
                    val queueName = context.get(ArgumentType.String("name"))
                    val queueQuery = LobbyServer.queues.values.firstOrNull {
                        it.simpleName == queueName
                    } ?: run {
                        sender.sendMessage("Queue $queueName not found")
                        return@addSyntax
                    }

                    queueQuery.startNewGame()
                }, nameArgument)
            }
        })
    }
}