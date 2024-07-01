package de.airblocks.teamfive.lobby.queue

import net.minestom.server.MinecraftServer
import net.minestom.server.timer.Task
import net.minestom.server.timer.TaskSchedule

abstract class QueueRunnable(val queue: Queue<*>) {

    val startTime: Int = 30
    private var currentTime: Int = startTime; private set


    fun createQueueRunnable(): Task {
        val task = MinecraftServer.getSchedulerManager().submitTask {
            if (!queue.canStart()) {
                currentTime = startTime
            } else {
                currentTime--
            }

            if (currentTime <= 0) {
                queue.startNewGame()
            }

            return@submitTask TaskSchedule.seconds(1)
        }

        return task
    }

    private fun Queue<*>.canStart(): Boolean {
        return getPlayers().size >= minPlayersToStart
    }
}