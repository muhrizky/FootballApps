package id.ac.undip.ce.student.muhammadrizqi.footballapps.matches.search

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.text.SimpleDateFormat
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.Event
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R
import id.ac.undip.ce.student.muhammadrizqi.footballapps.util.strTodate
import id.ac.undip.ce.student.muhammadrizqi.footballapps.util.changeFormatDate
import id.ac.undip.ce.student.muhammadrizqi.footballapps.util.toGMTFormat
import id.ac.undip.ce.student.muhammadrizqi.footballapps.matches.EventUI


class MatchesListSearchAdapter(private val events: List<Event>, private val listener: (Event) -> Unit): RecyclerView.Adapter<MatchesListSearchAdapter.EventViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EventViewHolder(EventUI().createView(AnkoContext.create(parent.context, parent)))

    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) = holder.bindItem(events[position], listener)

    class EventViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val eventDate: TextView = itemView.find(R.id.date)
        private val eventTime: TextView = itemView.find(R.id.time)
        private val homeTeam: TextView = itemView.find(R.id.homeTeam)
        private val homeScore: TextView = itemView.find(R.id.homeScore)
        private val awayTeam: TextView = itemView.find(R.id.awayTeam)
        private val awayScore: TextView = itemView.find(R.id.awayScore)

        @SuppressLint("SimpleDateFormat")
        fun bindItem(event: Event, listener: (Event) -> Unit){
            val date = strTodate(event.eventDate)
            val dateTime = toGMTFormat(event.eventDate, event.eventTime)
            eventDate.text = changeFormatDate(date)
            eventTime.text = SimpleDateFormat("HH:mm").format(dateTime)

            homeTeam.text = event.homeTeam
            homeScore.text = event.homeScore

            awayTeam.text = event.awayTeam
            awayScore.text = event.awayScore

            itemView.onClick { listener(event) }
        }
    }
}