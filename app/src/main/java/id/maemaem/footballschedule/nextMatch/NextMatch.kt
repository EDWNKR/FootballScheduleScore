package id.maemaem.footballschedule

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
import id.maemaem.footballschedule.R.color.colorAccent
import id.maemaem.footballschedule.R.id.btn_jadwal
import id.maemaem.footballschedule.R.id.btn_match_sebelumnya
import id.maemaem.footballschedule.adapterRecyclerView.NextScheduleAdapter
import id.maemaem.footballschedule.detailNextMatch.DetailNextMatch
import id.maemaem.footballschedule.prevMatch.PrevMatch
import id.maemaem.footballschedule.nextMatch.SchedulePresenter
import id.maemaem.footballschedule.nextMatch.ScheduleView
import id.maemaem.footballschedule.model.ModelSchedule
import id.maemaem.footballschedule.util.invisible
import id.maemaem.footballschedule.util.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class NextMatch : AppCompatActivity(), ScheduleView {

    //lateinit digunakan untuk menginisialisasi nilai variabel sebelum Anda mengaksesnya.

    private var nextSchedule: MutableList<ModelSchedule> = mutableListOf()
    private lateinit var presenter: SchedulePresenter
    private lateinit var adapter: NextScheduleAdapter

    private lateinit var schedule: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private val idleague: String = "4335"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_next_match)

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
                        startActivity<PrevMatch>()
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
                        presenter.loadJSON(idleague)
                    }
                }.lparams{
                    alignParentEnd()
                    centerVertically()
                }

            }

            textView {
                textSize = 17f
                text = "Jadwal Pertandingan LIGA SPANYOL"
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
                text = "(Untuk 15 Hari Kedepan)"
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

                    schedule = recyclerView {
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
        //menerapkan presenter dan adapter
        adapter = NextScheduleAdapter(nextSchedule){
            startActivity<DetailNextMatch>(
                    "file_name" to it.strFilename.toString(),
                    "homeTeam" to it.strHomeTeam.toString(),
                    "awayTeam" to it.strAwayTeam.toString())
        }

        schedule.adapter = adapter
        presenter = SchedulePresenter(this)

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

    override fun showNextScheduleRetro(jsonResponse: List<ModelSchedule>) {
        swipeRefresh.isRefreshing = false //refresh akan hilang ketika data sudah berhasil diperbarui.
        nextSchedule.clear()
        nextSchedule.addAll(jsonResponse)
        adapter.notifyDataSetChanged()
    }

}
