package com.hardik.androidassignment.database

import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.hardik.androidassignment.data.model.local.DataModel
import java.io.IOException
import java.util.ArrayList

class DataBaseHelper(activity: Context) {

  private var dbHelper: DatabaseManager? = null
  private var myDataBase: SQLiteDatabase? = null

  init {
    try {
      dbHelper = DatabaseManager(activity)
    } catch (e: IOException) {
      println("Hardik DBQuery error --> " + e.localizedMessage)
    }
  }

  @Throws(SQLException::class)
  fun createDatabase(): DataBaseHelper {
    try {
      dbHelper!!.createDatabase()
    } catch (e: IOException) {
      println("Hardik createDatabase error --> " + e.localizedMessage)
    }

    return this
  }

  @Throws(SQLException::class)
  fun open(): DataBaseHelper {
    try {
      dbHelper!!.openDatabase()
      dbHelper!!.close()
      myDataBase = dbHelper!!.readableDatabase
    } catch (e: SQLException) {
      println("Hardik open error --> " + e.localizedMessage)
    }

    return this
  }

  private fun close() {
    dbHelper!!.close()
  }

  //______________________________________________________________________________________________
  //__________________________________      QUERIES      ________________________________________
  //______________________________________________________________________________________________

  fun insertData(dataList: ArrayList<DataModel>) {

    try {

      open()

      myDataBase!!.execSQL("delete from countryData")

      for (i in 0 until dataList.size) {

        val values = ContentValues()
        values.put("title", dataList[i].title)
        values.put("description", dataList[i].description)
        values.put("imageHref", dataList[i].imageHref)
        myDataBase!!.insert("countryData", null, values)

      }

      close()

    } catch (e: Exception) {
      println("Hardik insertData error --> " + e.localizedMessage)
    }

  }

  fun getData(): ArrayList<DataModel> {

    val list = ArrayList<DataModel>()

    try {

      open()

      val c = myDataBase!!.rawQuery(
          "select title, description, imageHref from countryData",
          null
      )

      while (c.moveToNext()) {
        list.add(DataModel(c.getString(0), c.getString(1), c.getString(2)))
      }

      close()

    } catch (e: Exception) {
      println("Hardik insertData error --> " + e.localizedMessage)
    }

    return list
  }
}