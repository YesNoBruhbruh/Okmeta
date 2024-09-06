package me.maanraj514.okmeta.database.sql

import me.maanraj514.okmeta.database.Database
import java.sql.Connection

interface SQLDatabase : Database {

    fun getConnection(): Connection
}