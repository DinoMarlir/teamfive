/**
 * This class represents an exception that is thrown when a game server does not exist.
 * It extends the Exception class and provides a custom error message.
 */
package de.airblocks.teamfive.base.server.exception

class GameServerNotExistsException: Exception("GameServer does not exist!")