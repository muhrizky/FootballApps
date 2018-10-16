package id.ac.undip.ce.student.muhammadrizqi.footballapps.matches

import android.annotation.SuppressLint
import android.content.Intent
import android.provider.CalendarContract
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.text.SimpleDateFormat
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.Event
import id.ac.undip.ce.student.muhammadrizqi.footballapps.util.toGMTFormat
import id.ac.undip.ce.student.muhammadrizqi.footballapps.util.dateTimeToFormat
import id.ac.undip.ce.student.muhammadrizqi.footballapps.util.changeFormatDate
import id.ac.undip.ce.student.muhammadrizqi.footballapps.util.strTodate
import java.util.*
import java.util.concurrent.TimeUnit


class MatchesListAdapter(private val events: List<Event>, private val listener: (Event) -> Unit): RecyclerView.Adapter<MatchesListAdapter.EventViewHolder>(){
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
        private val btnNotify: ImageButton = itemView.find(R.id.btn_notify)

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

            if(dateTime!!.after(Date())){
                btnNotify.visibility = View.VISIBLE
            }

            itemView.onClick { listener(event) }
            btnNotify.onClick {
                val intent = Intent(Intent.ACTION_INSERT)
                intent.type = "vnd.android.cursor.item/event"

                val dateTime = event.eventDate + " " + event.eventTime
                Log.d("dateTime", "DateTime adalah " + dateTime)
                val startTime = dateTime.dateTimeToFormat()
                Log.d("startTime", "startTime adalah " + startTime)
                val endTime = startTime + TimeUnit.MINUTES.toMillis(90)
                Log.d("endTime", "endTime adalah " + endTime)

                intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime)
                intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime)
                intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, false)
                intent.putExtra(CalendarContract.CalendarAlerts.ALARM_TIME, TimeUnit.MINUTES.toMillis(90))

                intent.putExtra(CalendarContract.Events.TITLE,
                        "${event.homeTeam} vs ${event.awayTeam}")

                intent.putExtra(CalendarContract.Events.DESCRIPTION,
                        "Reminder ${event.homeTeam} and ${event.awayTeam} from My Football App")


                itemView.context.startActivity(intent)
            }
        }
    }
}