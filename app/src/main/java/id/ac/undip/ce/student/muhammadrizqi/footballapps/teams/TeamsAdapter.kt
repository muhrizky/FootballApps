package id.ac.undip.ce.student.muhammadrizqi.footballapps.teams

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.Team

class TeamsAdapter(private val teams: List<Team>, private val listener: (Team) -> Unit): RecyclerView.Adapter<TeamsAdapter.TeamViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TeamViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))

    override fun getItemCount() = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int){
        holder.bindItem(teams[position], listener)
    }

    class TeamViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val teamBadge: ImageView = view.find(R.id.team_badge)
        private val teamName: TextView = view.find(R.id.team_name)

        fun bindItem(teams: Team, listener: (Team) -> Unit){
            Picasso.get().load(teams.teamBadge).into(teamBadge)
            teamName.text = teams.teamName

            itemView.onClick {
                listener(teams)
            }
        }
    }
}