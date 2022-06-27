package com.example.riddler

import com.example.riddler.data.model.Lobby
import com.example.riddler.data.model.LobbyPlayers
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.Exception

class Util {
    companion object {

        val gson = Gson()
        //TODO find a better way to convert from snapshot to Lobby
        fun mapToLobby(map: Map<String, Any>) : Lobby {
            val host = map.get("host").toString()
            val pin = map.get("pin").toString()
            val gameStarted = map.get("gameStarted") as Boolean
            var playerList = ArrayList<LobbyPlayers>()

            try {
                val pList = map.get("players") as List<HashMap<String,Any>>
                println(pList)
                for (player in pList) {
                    playerList.add(
                        LobbyPlayers(
                        player.get("playerId") as Long,
                        player.get("playerName") as String)
                    )
                }
            } catch (e: Exception) {
                println(e)
            }
            return Lobby(host, pin, gameStarted, playerList)
        }

        //convert a data class to a map
        fun <T> T.serializeToMap(): Map<String, Any> {
            return convert()
        }

        //convert a map to a data class
        inline fun <reified T> Map<String, Any>.toDataClass(): T {
            return convert()
        }

        //convert an object of type I to type O
        inline fun <I, reified O> I.convert(): O {
            val json = gson.toJson(this)
            return gson.fromJson(json, object : TypeToken<O>() {}.type)
        }
    }
}