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
import id.maemaem.footballschedule.model.ModelScore

import org.jetbrains.anko.*

class ScoreAdapter(val events: List<ModelScore>, private val listener: (ModelScore) -> Unit)
    : RecyclerView.Adapter<ScoreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
        return ScoreViewHolder(ScoreUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }

    override fun getItemCount(): Int = events.size

}

    class ScoreViewHolder(view: View) : RecyclerView.ViewHolder(view){

        private val txt_date: TextView = view.find(date)
        private val txt_homeTeam: TextView = view.find(homeTeam)
        private val txt_homeScore: TextView = view.find(homeScore)
        private val txt_awayScore: TextView = view.find(awayScore)
        private val txt_awayTeam: TextView = view.find(awayTeam)

        fun bindItem(events: ModelScore, listener: (ModelScore) -> Unit) {
            txt_date.text = events.strDate
            txt_homeTeam.text = events.strHomeTeam
            txt_homeScore.text = events.intHomeScore.toString()
            txt_awayScore.text = events.intAwayScore.toString()
            txt_awayTeam.text = events.strAwayTeam

            itemView.setOnClickListener { listener(events) }
        }
    }

class ScoreUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {

            linearLayout {
                lparams {
                    width = matchParent
                    height = wrapContent
                    margin = dip(10)
                }
                padding = dip(16)
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER
                backgroundResource = border_transparant

                textView {
                    id = date
                    textColor = Color.BLACK
                }.lparams {
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
                    }.lparams {
                        alignParentLeft()
                        centerVertically()
                    }

                    textView {
                        id = homeScore
                        textSize = 23f
                        gravity = Gravity.START
                        setTypeface(null, Typeface.BOLD)
                        textColor = Color.BLACK
                    }.lparams {
                        leftOf(textTengah)
                        centerVertically()
                        marginEnd = dip(20)
                    }

                    textView {
                        id = textTengah
                        textSize = 17f
                        text = "-"
                        setTypeface(null, Typeface.BOLD)
                        textColor = Color.BLACK
                    }.lparams {
                        margin = dip(15)
                        centerHorizontally()
                        centerVertically()
                    }

                    textView {
                        id = awayScore
                        textSize = 23f
                        gravity = Gravity.START
                        setTypeface(null, Typeface.BOLD)
                        textColor = Color.BLACK
                    }.lparams {
                        rightOf(textTengah)
                        centerVertically()
                        marginStart = dip(20)
                    }

                    textView {
                        id = awayTeam
                        textSize = 17f
                        setTypeface(null, Typeface.BOLD)
                        textColor = Color.BLACK
                    }.lparams {
                        alignParentEnd()
                        centerVertically()
                    }

                }
            }

        }
    }
}

