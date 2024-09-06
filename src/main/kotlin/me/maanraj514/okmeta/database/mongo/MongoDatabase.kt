package me.maanraj514.okmeta.database.mongo

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ServerApi
import com.mongodb.ServerApiVersion
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import me.maanraj514.okmeta.database.ConnectedCallback

class MongoDatabase(
    private val userName: String,
    private val password: String,
    private val host: String,
    private val connectedCallback: ConnectedCallback
) : IMongoDatabase {

    private var mongoClient: MongoClient
    private var isConnected = false

    init {
        val connectionString = ConnectionString(
            "mongodb+srv://" +
                    userName +
                    ":" +
                    password +
                    "@" +
                    host +
                    "/?retryWrites=true&w=majority")

        val mongoSettings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .serverApi(
                ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build())
            .retryWrites(true)
            .retryReads(true)
            .build()

        mongoClient = MongoClients.create(mongoSettings)

        connectedCallback.onConnected(mongoClient)
    }

    override fun disconnect() {
        if (isConnected()) {
            mongoClient.close()
            isConnected = false
            connectedCallback.onDisconnect()
        }
    }

    override fun isConnected(): Boolean {
        return isConnected
    }

    override fun getClient(): MongoClient {
        return mongoClient
    }
}