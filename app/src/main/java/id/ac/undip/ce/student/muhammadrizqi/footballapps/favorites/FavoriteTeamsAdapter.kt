package id.ac.undip.ce.student.muhammadrizqi.footballapps.favorites

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk27.coroutines.onClick
import id.ac.undip.ce.student.muhammadrizqi.footballapps.model.Team
import id.ac.undip.ce.student.muhammadrizqi.footballapps.R
import id.ac.undip.ce.student.muhammadrizqi.footballapps.teams.TeamUI

class FavoriteTeamsAdapter(private val teams: MutableList<id.ac.undip.ce.student.muhammadrizqi.footballapps.db.Team>, private val listener: (Team) -> Unit): RecyclerView.Adapter<FavoriteTeamsAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount() = teams.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val teamBadge: ImageView = view.find(R.id.team_badge)
        private val teamName: TextView = view.find(R.id.team_name)

        fun bindItem(team: Team, listener: (Team) -> Unit){
            Picasso.get().load(team.teamBadge).into(teamBadge)
            teamName.text = team.teamName

            itemView.onClick { listener(team) }
        }
    }
}