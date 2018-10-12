package id.ac.undip.ce.student.muhammadrizqi.footballapps.favorites

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R
import id.ac.undip.ce.student.muhammadrizqi.footballapps.db.EventDB
import id.ac.undip.ce.student.muhammadrizqi.footballapps.matches.EventUI
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R.id.date
import id.ac.undip.ce.student.muhammadrizqi.footballapps.util.changeFormatDate
import id.ac.undip.ce.student.muhammadrizqi.footballapps.util.strTodate



class FavoriteMatchesAdapter(private val matches: List<EventDB>, private val listener: (EventDB) -> Unit): RecyclerView.Adapter<FavoriteMatchesAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(EventUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount() = matches.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(matches[position], listener)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val eventDate: TextView = view.find(date)
        private val homeTeam: TextView = view.find(R.id.homeTeam)
        private val homeScore: TextView = view.find(R.id.homeScore)
        private val awayTeam: TextView = view.find(R.id.awayTeam)
        private val awayScore: TextView = view.find(R.id.awayScore)

        fun bindItem(match: EventDB, listener: (EventDB) -> Unit){
            val date = strTodate(match.eventDate)
            eventDate.text = changeFormatDate(date)

            homeTeam.text = match.homeTeam
            homeScore.text = match.homeScore

            awayTeam.text = match.awayTeam
            awayScore.text = match.awayScore

            itemView.onClick { listener(match) }
        }
    }
}