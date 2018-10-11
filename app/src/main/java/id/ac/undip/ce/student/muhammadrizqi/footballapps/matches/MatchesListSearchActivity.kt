package id.ac.undip.ce.student.muhammadrizqi.footballapps.matches

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.Menu
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.textView
import org.jetbrains.anko.wrapContent

class MatchesListSearchActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        linearLayout {
            lparams(width = matchParent, height = matchParent)
            gravity = Gravity.CENTER

            textView("Hello Kotlin!"){
                gravity = Gravity.CENTER
            }.lparams(width = wrapContent, height = wrapContent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)

        return super.onCreateOptionsMenu(menu)
    }
}