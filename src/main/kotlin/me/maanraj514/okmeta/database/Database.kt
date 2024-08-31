package me.maanraj514.okmeta.database

import java.sql.Connection

interface Database {

    fun disconnect()

    fun isConnected(): Boolean

    fun getConnection(): Connection
}