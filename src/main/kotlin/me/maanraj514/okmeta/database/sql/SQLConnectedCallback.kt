package me.maanraj514.okmeta.database.sql

import me.maanraj514.okmeta.database.ConnectedCallback
import java.sql.Connection

interface SQLConnectedCallback : ConnectedCallback {

    fun onConnected(connection: Connection)
}