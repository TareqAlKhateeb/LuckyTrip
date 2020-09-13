package com.example.luckytrip.util.mSQLite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CreateDB(con: Context): SQLiteOpenHelper(con,"Rooms.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table results(rooms text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}