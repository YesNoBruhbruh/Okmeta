package me.maanraj514.okmeta.database.mongo

import com.mongodb.client.MongoClient
import me.maanraj514.okmeta.database.ConnectedCallback

interface MongoConnectedCallback : ConnectedCallback {

    fun onConnected(client: MongoClient)
}