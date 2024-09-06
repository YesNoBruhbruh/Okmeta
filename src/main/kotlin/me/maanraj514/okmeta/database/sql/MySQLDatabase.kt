package me.maanraj514.okmeta.database.sql

import me.maanraj514.okmeta.database.ConnectedCallback
import me.maanraj514.okmeta.database.Database
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class MySQLDatabase(
    host: String,
    port: Int,
    password: String,
    username: String,
    database: String,
    useSSL: Boolean,
    private val connectedCallback: ConnectedCallback
) :
    SQLDatabase {

    private var connection: Connection

    init {

        try {
            connection =
                DriverManager.getConnection("jdbc:mysql://$host:$port/$database?useSSL=$useSSL", username, password)

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