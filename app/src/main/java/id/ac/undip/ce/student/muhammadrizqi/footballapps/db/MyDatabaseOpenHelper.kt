package id.ac.undip.ce.student.muhammadrizqi.footballapps.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "Favorite.db", null){
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper{
            if(instance == null){
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }

            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(EventDB.TABLE_MATCH, true,
                EventDB.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                EventDB.EVENT_ID to TEXT + UNIQUE,
                EventDB.EVENT_NAME to TEXT,
                EventDB.EVENT_DATE to TEXT,
                EventDB.HOME_TEAM_ID to TEXT,
                EventDB.HOME_TEAM_NAME to TEXT,
                EventDB.HOME_TEAM_SCORE to TEXT,
                EventDB.AWAY_TEAM_ID to TEXT,
                EventDB.AWAY_TEAM_NAME to TEXT,
                EventDB.AWAY_TEAM_SCORE to TEXT)

        db?.createTable(TeamDB.TABLE_TEAM, true,
                TeamDB.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                TeamDB.TEAM_ID to TEXT + UNIQUE,
                TeamDB.TEAM_NAME to TEXT,
                TeamDB.TEAM_BADGE to TEXT,
                TeamDB.TEAM_STADIUM to TEXT,
                TeamDB.TEAM_YEAR to TEXT,
                TeamDB.TEAM_DESC to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(EventDB.TABLE_MATCH, true)
        db?.dropTable(TeamDB.TABLE_TEAM, true)
    }

}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)