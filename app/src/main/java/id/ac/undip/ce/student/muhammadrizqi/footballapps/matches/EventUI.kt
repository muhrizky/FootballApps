package id.ac.undip.ce.student.muhammadrizqi.footballapps.matches

import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R
import org.jetbrains.anko.cardview.v7.cardView

class EventUI: AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui){
        cardView {
            lparams(width = matchParent, height = wrapContent){
                gravity = Gravity.CENTER
                margin = dip(4)
                radius = 4f
            }

            linearLayout {
                orientation = LinearLayout.VERTICAL

                textView("Minggu, 04 Maret 2018"){
                    id = R.id.date
                    textColorResource = R.color.colorPrimary
                    topPadding = dip(8)
                    bottomPadding = dip(4)

                }.lparams(width = wrapContent, height = wrapContent){
                    gravity = Gravity.CENTER
                }

                textView("21:00"){
                    id = R.id.time
                    textSize = 10f
                    textColorResource = R.color.colorPrimary
                    bottomPadding = dip(4)

                }.lparams(width = wrapContent, height = wrapContent){
                    gravity = Gravity.CENTER
                }

                relativeLayout {

                    textView{
                        id = R.id.homeTeam
                        textSize = 14f
                        textColor = Color.BLACK
                        gravity = Gravity.END
                    }.lparams(width = wrapContent, height = wrapContent){
                        leftOf(R.id.homeScore)
                        rightMargin = dip(10)
                    }

                    textView{
                        id = R.id.homeScore
                        textSize = 12f
                        gravity = Gravity.CENTER
                        textColor = Color.BLACK
                    }.lparams(width = wrapContent, height = wrapContent){
                        leftOf(R.id.vs)
                    }

                    textView("vs"){
                        id = R.id.vs
                        textSize = 10f
                        gravity = Gravity.CENTER
                    }.lparams(width = wrapContent, height = wrapContent){
                        centerInParent()
                        leftMargin = dip(6)
                        rightMargin = dip(6)
                    }

                    textView{
                        id = R.id.awayScore
                        textSize = 12f
                        gravity = Gravity.CENTER
                        textColor = Color.BLACK
                    }.lparams(width = wrapContent, height = wrapContent){
                        rightOf(R.id.vs)
                    }

                    textView{
                        id = R.id.awayTeam
                        textSize = 14f
                        textColor = Color.BLACK
                        gravity = Gravity.START
                    }.lparams(width = wrapContent, height = wrapContent){
                        rightOf(R.id.awayScore)
                        leftMargin = dip(10)
                    }

                }.lparams(width = matchParent, height = wrapContent)

            }.lparams(width = matchParent, height = wrapContent){
                gravity = Gravity.CENTER
                bottomMargin = dip(8)
            }
        }
    }

}