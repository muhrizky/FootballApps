package id.ac.undip.ce.student.muhammadrizqi.footballapps.players

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import  id.ac.undip.ce.student.muhammadrizqi.footballapps.model.Player
import  id.ac.undip.ce.student.muhammadrizqi.footballapps.R

class PlayersAdapter(private val players: List<Player>, private val listener: (Player) -> Unit): RecyclerView.Adapter<PlayersAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(PlayerUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount() = players.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(players[position], listener)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val playerBadge: ImageView = view.find(R.id.player_badge)
        private val playerName: TextView = view.find(R.id.player_name)

        fun bindItem(player: Player, listener: (Player) -> Unit){
            Picasso.get().load(player.playerBadge).into(playerBadge)
            playerName.text = player.playerName

            itemView.onClick {
                listener(player)
            }
        }
    }

    class PlayerUI: AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui){
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = R.id.player_badge
                }.lparams(width = dip(50), height = dip(50))

                textView {
                    id = R.id.player_name
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                }
            }
        }

    }
}