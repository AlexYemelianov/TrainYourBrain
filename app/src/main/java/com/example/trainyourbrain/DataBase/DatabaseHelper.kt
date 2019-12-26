import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.ArrayList

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    // looping through all rows and adding to list
    // adding to Students list
    val allQuestionsList: ArrayList<String>
        get() {
            val questionsList = ArrayList<String>()
            var question = ""
            val selectQuery = "SELECT  * FROM $TABLE_QUESTIONS"
            val db = this.readableDatabase
            val c = db.rawQuery(selectQuery, null)
            if (c.moveToFirst()) {
                do {
                    question = c.getString(c.getColumnIndex(KEY_QUESTIONS))
                    questionsList.add(question)
                } while (c.moveToNext())
                Log.d("array", questionsList.toString())
            }
            return questionsList
        }

    init {

        Log.d("table", CREATE_TABLE_QUESTIONS)
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_QUESTIONS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS '$TABLE_QUESTIONS'")
        onCreate(db)
    }

    fun addQuestionsDetail(student: String): Long {
        val db = this.writableDatabase
        // Creating content values
        val values = ContentValues()
        values.put(KEY_QUESTIONS, student)

        return db.insert(TABLE_QUESTIONS, null, values)
    }

    companion object {

        var DATABASE_NAME = "question_database"
        private val DATABASE_VERSION = 1
        private val TABLE_QUESTIONS = "questions"
        private val KEY_ID = "id"
        private val KEY_QUESTIONS = "name"


        private val CREATE_TABLE_QUESTIONS = ("CREATE TABLE "
                + TABLE_QUESTIONS + "(" + KEY_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_QUESTIONS + " TEXT );")
    }
}