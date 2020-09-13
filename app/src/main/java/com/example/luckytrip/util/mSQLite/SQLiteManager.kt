package com.example.luckytrip.util.mSQLite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.luckytrip.data.models.Rooms
import com.google.gson.Gson

object SQLiteManager {

    var obj: CreateDB? = null
    lateinit var db: SQLiteDatabase

    private var gSon : Gson? = null


    fun initWith(context: Context) {

        gSon = Gson()

        obj = CreateDB(context)
        db = obj!!.writableDatabase

    }

    fun getSQLiteData(): Rooms?  {

        val cur = db.rawQuery("select * from results", null)
        if (cur.count > 0) {
            while (cur.moveToNext()) {

                val entities = cur.getString(cur.getColumnIndex("rooms"))

                return gSon?.fromJson(entities, Rooms::class.java)
            }

            cur.close()

        } else {

            return null

        }

        return null

    }

    fun insertIntoSQLiteDB(it: Rooms) {

        db.execSQL("delete from results")

        val entities: String? =
            gSon?.toJson(it, Rooms::class.java)

        db.execSQL("insert into results values(?)", arrayOf(entities))

    }
}