package de.airblocks.teamfive.lobby.queue

class QueueRunnableImpl(queue: Queue<*>, override val startTime: Int): QueueRunnable(queue)