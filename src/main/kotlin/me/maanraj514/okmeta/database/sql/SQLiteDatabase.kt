package me.maanraj514.okmeta.database.sql

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class SQLiteDatabase(path: String, private val connectedCallback: SQLConnectedCallback) : SQLDatabase {

    private var connection: Connection

    init {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:$path")

            if (isConnected()) {
                connectedCallback.onConnected(connection)
            }
        } catch (e: SQLException) {
            throw RuntimeException(e)
        }
    }

    override fun disconnect() {
        if (isConnected()) {
            try {
                connection.close()
                connectedCallback.onDisconnect()
            } catch (e: SQLException) {
                throw RuntimeException(e)
            }
        }
    }

    override fun isConnected(): Boolean {
        return try {
            !connection.isClosed
        } catch (e: SQLException) {
            false
        }
    }

    override fun getConnection(): Connection {
        return connection
    }
}