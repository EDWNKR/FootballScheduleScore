package id.maemaem.footballschedule.prevMatch

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.ProgressBar
import id.maemaem.footballschedule.NextMatch
import id.maemaem.footballschedule.R.color.colorAccent
import id.maemaem.footballschedule.R.id.btn_jadwal
import id.maemaem.footballschedule.R.id.btn_match_sebelumnya
import id.maemaem.footballschedule.adapterRecyclerView.ScoreAdapter
import id.maemaem.footballschedule.detailPrevMatch.DetailPrevMatch
import id.maemaem.footballschedule.model.ModelScore
import id.maemaem.footballschedule.util.invisible
import id.maemaem.footballschedule.util.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class PrevMatch : AppCompatActivity(), ScoreView {

    private var prevScore: MutableList<ModelScore> = mutableListOf()
    private lateinit var presenter: ScorePresenter
    private lateinit var adapter: ScoreAdapter

    private lateinit var score: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private val idleague: String = "4335"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_prev_match)

        linearLayout {
            lparams (width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            relativeLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(10)
                //orientation = LinearLayout.HORIZONTAL
                //gravity = Gravity.CENTER

                button {
                    id = btn_match_sebelumnya
                    text = "Skor"
                    textSize = 13f
                    setTypeface(null, Typeface.BOLD)
                    textColor = Color.BLACK

                    onClick {

                        presenter.loadJSON(idleague)
                    }

                }.lparams{
                    alignParentLeft()
                    centerVertically()
                }

                button {
                    id = btn_jadwal
                    text = "Jadwal"
                    textSize = 13f
                    setTypeface(null, Typeface.BOLD)
                    textColor = Color.BLACK
                    onClick {
                        startActivity<NextMatch>()
                    }
                }.lparams{
                    alignParentEnd()
                    centerVertically()
                }

            }

            textView {
                textSize = 17f
                text = "Skor Pertandingan LIGA SPANYOL"
                setTypeface(null, Typeface.BOLD)
                textColor = Color.BLACK
                gravity = Gravity.CENTER
            }.lparams{
                width = matchParent
                height = wrapContent
                margin = dip(5)
            }

            textView {
                textSize = 14f
                text = "(15 Hari Sebelumnya)"
                setTypeface(null, Typeface.BOLD)
                gravity = Gravity.CENTER
                //textColor = Color.BLACK
            }.lparams{
                width = matchParent
                height = wrapContent
                margin = dip(0)
            }

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                        android.R.color.holo_blue_bright,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_green_light)

                relativeLayout{
                    lparams (width = matchParent, height = wrapContent)

                    score = recyclerView {
                        lparams (width = matchParent, height = wrapContent)
                        layoutManager = LinearLayoutManager(ctx)  //ctx = context
                    }

                    progressBar = progressBar {
                    }.lparams{
                        centerHorizontally()
                    }
                }
            }
        }
        //menerapkan presenter dan adapter
        adapter =ScoreAdapter(prevScore){
            startActivity<DetailPrevMatch>(
                    "file_name" to it.strFilename.toString(),
                    "homeTeam" to it.strHomeTeam.toString(),
                    "awayTeam" to it.strAwayTeam.toString(),
                    "homeScore" to it.intHomeScore.toString(),
                    "awayScore" to it.intAwayScore.toString())
        }
        score.adapter = adapter
        presenter = ScorePresenter(this)

        //panggil fungsi di presenter
        presenter.loadJSON(idleague)

        swipeRefresh.onRefresh {
            presenter.loadJSON(idleague)
        }

    }
    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPrevSchedule(jsonResponse: List<ModelScore>) {
        swipeRefresh.isRefreshing = false //refresh akan hilang ketika data sudah berhasil diperbarui.
        prevScore.clear()
        prevScore.addAll(jsonResponse)
        adapter.notifyDataSetChanged()
    }

}
