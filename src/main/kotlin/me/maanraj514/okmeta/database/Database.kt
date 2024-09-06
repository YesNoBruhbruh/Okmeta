package me.maanraj514.okmeta.database

interface Database {

    fun disconnect()

    fun isConnected(): Boolean
}