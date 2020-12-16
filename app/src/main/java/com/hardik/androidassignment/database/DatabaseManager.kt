package com.hardik.androidassignment.database

import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import android.os.Build.VERSION
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class DatabaseManager(// *************** Declare all the global variable *******************//
  private val activity: Context
) : SQLiteOpenHelper(activity, dataBaseName, null, dataBaseVersion) {

  private var myDataBase: SQLiteDatabase? = null

  @Throws(IOException::class)
  fun createDatabase() {
    val dbExist = checkDatabase()
    if (dbExist) {
      println(" Database exists.")
    } else {
      this.readableDatabase
      try {
        copyDatabase()
      } catch (e: IOException) {
        throw Error("Error copying database")
      }
    }

    this.close()
  }

  private fun checkDatabase(): Boolean {
    var checkDB = false
    try {
      checkDB = File(dataBasePath + dataBaseName).exists()
    } catch (e: SQLiteException) {
      println("Database doesn't exist.....")
    }

    return checkDB
  }

  @Throws(IOException::class)
  private fun copyDatabase() {

    try {

      // Open your local db as the input stream
      val myInput = activity.assets.open(dataBaseName)

      // Path to the just created empty db
      // String out filename = DB_PATH + DB_NAME;

      getDataBasePath()
      println("Hardik copyDatabase filePath --> $filePath")

      // Open the empty db as the output stream
      val myOutput = FileOutputStream(filePath)

      // transfer byte to input file to output file
      val buffer = ByteArray(1024)
      while (myInput.read(buffer) > 0) {
        myOutput.write(buffer)
      }

      // Close the streams
      myOutput.flush()
      myOutput.close()
      myInput.close()

    } catch (e: Exception) {
      println("Hardik copyDatabase error --> " + e.localizedMessage)
    }
  }

  private fun getDataBasePath() {

    if (VERSION.SDK_INT >= Build.VERSION_CODES.P) {
      val database = this.readableDatabase
      filePath = database.path
      database.close()
    } else {
      filePath = dataBasePath + dataBaseName
    }
  }

  @Throws(SQLException::class)
  fun openDatabase() {
    // Open the database

    getDataBasePath()
    myDataBase = SQLiteDatabase.openDatabase(
        filePath, null,
        SQLiteDatabase.OPEN_READWRITE
    )
  }

  @Synchronized
  override fun close() {
    if (myDataBase != null) {
      myDataBase!!.close()
    }
    super.close()
  }

  override fun onCreate(db: SQLiteDatabase) {
    // not used
  }

  override fun onUpgrade(
    db: SQLiteDatabase,
    oldVersion: Int,
    newVersion: Int
  ) {
    println(" version database --> $oldVersion -- $newVersion")
  }

  companion object {

    // path of your database
    private val dataBasePath = (Environment.getDataDirectory().toString() + "/data/"
        + "com.hardik.androidassignment/databases/")

    // the extension may be .sqlite or .db
    private const val dataBaseName = "country.sqlite"
    private const val dataBaseVersion = 1

    var filePath = ""
  }
}
