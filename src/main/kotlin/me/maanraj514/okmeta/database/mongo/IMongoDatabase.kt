package me.maanraj514.okmeta.database.mongo

import com.mongodb.client.MongoClient
import me.maanraj514.okmeta.database.Database

interface IMongoDatabase : Database {

    fun getClient(): MongoClient
}