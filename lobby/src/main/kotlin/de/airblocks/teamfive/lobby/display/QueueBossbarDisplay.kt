package de.airblocks.teamfive.lobby.display

import de.airblocks.teamfive.lobby.utils.currentQueue
import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.minestom.server.MinecraftServer
import net.minestom.server.entity.Player
import net.minestom.server.timer.TaskSchedule

object QueueBossbarDisplay {

    val playerBossBarMap: MutableMap<Player, BossBar> = mutableMapOf()

    var offset = 0.0; private set

    val DISPLAY_RUNNABLE = MinecraftServer.getSchedulerManager().submitTask {

        playerBossBarMap.forEach {
            val player = it.key
            val bar = it.value
            val queue = player.currentQueue()

            if (queue == null) {
                // Component.text("No queue joined")
                bar.name(
                    MiniMessage.miniMessage().deserialize("<gradient:#707CF7:#F658CF:$offset>No queue joined")
                )
            } else {
                /*
                queue.name.append(
                    Component.text(": waiting for players... (${queue.getPlayers().size}/${queue.minPlayersToStart}/${queue.maxPlayers}) | ${queue.runnable.currentTime}")
                )
                 */
                bar.name(
                    MiniMessage.miniMessage().deserialize(
                        "<gradient:#707CF7:#F658CF:$offset>Queue: ${queue.simpleName} | ${queue.getPlayers().size}/${queue.minPlayersToStart}/${queue.maxPlayers} | ${queue.runnable.currentTime}"
                    )
                )
            }
        }

        offset += 0.05
        if (offset > 1.0) offset -= 2

        return@submitTask TaskSchedule.tick(1)
    }

    fun initialize() {
        DISPLAY_RUNNABLE
    }

    fun addPlayer(player: Player) {
        val bossBar = BossBar.bossBar(Component.empty(), 1f, BossBar.Color.RED, BossBar.Overlay.PROGRESS)
        playerBossBarMap[player] = bossBar
        bossBar.addViewer(player)
    }

    fun removePlayer(player: Player) {
        val bossBar = playerBossBarMap[player]
        bossBar?.removeViewer(player)
        playerBossBarMap.remove(player)
    }
}