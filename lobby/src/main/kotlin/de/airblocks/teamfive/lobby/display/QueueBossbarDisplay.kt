package de.airblocks.teamfive.lobby.display

import de.airblocks.teamfive.lobby.utils.currentQueue
import net.kyori.adventure.bossbar.BossBar
import net.kyori.adventure.text.Component
import net.minestom.server.MinecraftServer
import net.minestom.server.entity.Player
import net.minestom.server.timer.TaskSchedule

object QueueBossbarDisplay {

    val playerBossBarMap: MutableMap<Player, BossBar> = mutableMapOf()

    val DISPLAY_RUNNABLE = MinecraftServer.getSchedulerManager().submitTask {

        playerBossBarMap.forEach {
            val player = it.key
            val bar = it.value
            val queue = player.currentQueue()

            if (queue == null) {
                bar.name(Component.text("No queue joined"))
            } else {
                bar.name(queue.name.append(
                    Component.text(": waiting for players... (${queue.getPlayers().size}/${queue.minPlayersToStart}/${queue.maxPlayers}) | ${queue.runnable.currentTime}")
                ))
            }
        }

        return@submitTask TaskSchedule.seconds(1)
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