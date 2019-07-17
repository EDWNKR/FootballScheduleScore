package id.maemaem.footballschedule.adapterRecyclerView

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import id.maemaem.footballschedule.R.drawable.border_transparant
import id.maemaem.footballschedule.R.id.*
import id.maemaem.footballschedule.model.ModelSchedule
import org.jetbrains.anko.*

class NextScheduleAdapter(val events: List<ModelSchedule>, private val listener: (ModelSchedule) -> Unit)
    : RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }

    override fun getItemCount(): Int = events.size

}

class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val txt_date: TextView = view.find(date)
    private val txt_homeTeam: TextView = view.find(homeTeam)
    private val txt_awayTeam: TextView = view.find(awayTeam)

    fun bindItem(events: ModelSchedule, listener: (ModelSchedule) -> Unit) {
        txt_date.text = events.strDate
        txt_homeTeam.text = events.strHomeTeam
        txt_awayTeam.text = events.strAwayTeam

        itemView.setOnClickListener { listener(events)
        }
    }
}
class TeamUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {

            linearLayout {
                lparams{
                    width = matchParent
                    height = wrapContent
                    margin = dip(10)}
                padding = dip(16)
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER
                backgroundResource = border_transparant

                textView {
                    id = date
                    textColor = Color.BLACK
                }.lparams{
                    height = matchParent
                    width = wrapContent

                }

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(16)
                    //orientation = LinearLayout.HORIZONTAL
                    //gravity = Gravity.CENTER


                    textView {
                        id = homeTeam
                        textSize = 17f
                        gravity = Gravity.START
                        setTypeface(null, Typeface.BOLD)
                        textColor = Color.BLACK
                    }.lparams{
                        alignParentLeft()
                        centerVertically()
                    }

                    textView {
                        textSize = 17f
                        text = "VS"
                        setTypeface(null, Typeface.BOLD)
                        textColor = Color.BLACK
                    }.lparams{
                        margin = dip(15)
                        centerHorizontally()
                        centerVertically()
                    }

                    textView {
                        id = awayTeam
                        textSize = 17f
                        setTypeface(null, Typeface.BOLD)
                        textColor = Color.BLACK
                    }.lparams{
                        alignParentEnd()
                        centerVertically()
                    }

                }
            }

        }
    }

}


