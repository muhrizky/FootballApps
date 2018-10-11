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
        db?.createTable(Match.TABLE_MATCH, true,
                Match.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Match.EVENT_ID to TEXT + UNIQUE,
                Match.EVENT_NAME to TEXT,
                Match.EVENT_DATE to TEXT,
                Match.HOME_TEAM_ID to TEXT,
                Match.HOME_TEAM_NAME to TEXT,
                Match.HOME_TEAM_SCORE to TEXT,
                Match.AWAY_TEAM_ID to TEXT,
                Match.AWAY_TEAM_NAME to TEXT,
                Match.AWAY_TEAM_SCORE to TEXT)

        db?.createTable(Team.TABLE_TEAM, true,
                Team.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Team.TEAM_ID to TEXT + UNIQUE,
                Team.TEAM_NAME to TEXT,
                Team.TEAM_BADGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Match.TABLE_MATCH, true)
        db?.dropTable(Team.TABLE_TEAM, true)
    }

}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)