package me.maanraj514.okmeta.database

interface ConnectedCallback {

    fun onConnected(connection: Any)

    fun onDisconnect()
}