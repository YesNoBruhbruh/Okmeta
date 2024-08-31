package me.maanraj514.okmeta.database

import java.sql.Connection

interface ConnectedCallback {

    fun onConnected(connection: Connection)

    fun onDisconnect()
}