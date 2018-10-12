package id.ac.undip.ce.student.muhammadrizqi.footballapps.teams

import android.view.ViewGroup
import android.widget.LinearLayout
import org.jetbrains.anko.*
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R
class TeamUI: AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui){
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            padding = dip(16)
            orientation = LinearLayout.HORIZONTAL

            imageView {
                id = R.id.team_badge
            }.lparams(width = dip(50), height = dip(50))

            textView {
                id = R.id.team_name
                textSize = 16f
            }.lparams{
                margin = dip(15)
            }
        }
    }
}