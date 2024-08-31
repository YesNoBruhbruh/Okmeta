package me.maanraj514.okmeta.database

class DatabaseManager {

    private val databases = mutableMapOf<String, Database>()

    fun createDatabase(databaseName: String, database: Database) {
        databases.putIfAbsent(databaseName, database)
    }

    fun disconnectDatabase(databaseName: String) {
        databases[databaseName]?.disconnect()
    }

    fun deleteDatabase(databaseName: String) {
        disconnectDatabase(databaseName)
        databases.remove(databaseName)
    }

    fun getDatabase(databaseName: String): Database? {
        return databases[databaseName]
    }

    fun disconnectAll() {
        if (databases.isEmpty()) return

        for (database in databases.values) {
            database.disconnect()
        }
    }
}