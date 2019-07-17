package id.maemaem.footballschedule.detailPrevMatch

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.bumptech.glide.Glide
import id.maemaem.footballschedule.R.color.colorAccent
import id.maemaem.footballschedule.R.drawable.border_transparant
import id.maemaem.footballschedule.R.id.*
import id.maemaem.footballschedule.model.ModelDetailSchedule
import id.maemaem.footballschedule.model.ModelTeam
import id.maemaem.footballschedule.util.invisible
import id.maemaem.footballschedule.util.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class DetailPrevMatch : AppCompatActivity(), DetailPrevMatchView {

    private var filename: String? = ""
    private var strHomeTeam: String? = ""
    private var strAwayTeam: String? = ""

    private var strHomeScore: String? = ""
    private var strAwayScore: String? = ""

    private lateinit var presenter: DetailPrevMatchPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private lateinit var details: ModelDetailSchedule
    private lateinit var images: ModelTeam

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        strHomeScore = intent.getStringExtra("homeScore")
        strAwayScore = intent.getStringExtra("awayScore")

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

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(colorAccent,
                        android.R.color.holo_blue_bright,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_green_light)

                relativeLayout {
                    lparams(width = matchParent, height = wrapContent){

                    }
                    padding = dip(16)
                    //orientation = LinearLayout.HORIZONTAL
                    //gravity = Gravity.CENTER

                    progressBar = progressBar {
                    }.lparams{
                        centerHorizontally()
                    }

                    //untuk home
                    relativeLayout {
                        lparams(width = wrapContent, height = wrapContent){
                            centerVertically()
                            alignParentLeft()
                            margin = dip(10)
                        }

                        imageView {
                            id = image_home
                        }.lparams(width = dip(100), height = dip(100)){
                            centerHorizontally()
                        }

                        textView {
                            id = homeScore
                            textSize = 20f
                            gravity = Gravity.START
                            setTypeface(null, Typeface.BOLD)
                            textColor = Color.BLACK
                            text = strHomeScore
                        }.lparams{
                            below(image_home)
                            centerHorizontally()
                        }

                        textView {
                            id = homeTeam
                            textSize = 17f
                            gravity = Gravity.START
                            setTypeface(null, Typeface.BOLD)
                            textColor = Color.BLACK
                        }.lparams{
                            below(homeScore)
                            centerHorizontally()
                        }

                        imageView {
                            id = jersey_home
                        }.lparams(width = dip(75), height = dip(75)){
                            centerHorizontally()
                            below(homeTeam)
                            topMargin = dip(30)
                        }

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

                    //untuk away
                    relativeLayout {
                        lparams(width = wrapContent, height = wrapContent){
                            centerVertically()
                            alignParentEnd()
                            margin = dip(10)
                        }

                        imageView {
                            id = image_away
                        }.lparams(width = dip(100), height = dip(100)){
                            centerHorizontally()
                        }

                        textView {
                            id = awayScore
                            textSize = 20f
                            setTypeface(null, Typeface.BOLD)
                            textColor = Color.BLACK
                            text = strAwayScore
                        }.lparams{
                            below(image_away)
                            centerHorizontally()
                        }

                        textView {
                            id = awayTeam
                            textSize = 17f
                            setTypeface(null, Typeface.BOLD)
                            textColor = Color.BLACK
                        }.lparams{
                            below(awayScore)
                            centerHorizontally()
                        }

                        imageView {
                            id = jersey_away
                        }.lparams(width = dip(75), height = dip(75)){
                            centerHorizontally()
                            below(awayTeam)
                            topMargin = dip(30)
                        }

                    }
                }
            }
        }

        val intent = intent
        filename = intent.getStringExtra("file_name")
        strHomeTeam = intent.getStringExtra("homeTeam")
        strAwayTeam = intent.getStringExtra("awayTeam")



        presenter = DetailPrevMatchPresenter(this)

        //panggil fungsi di presenter
        presenter.getDetail(filename)
        //ambil image home
        presenter.getImageHome(strHomeTeam)
        //ambil image away
        presenter.getImageAway(strAwayTeam)


        swipeRefresh.onRefresh {
            presenter.getDetail(filename)
            //ambil image home
            presenter.getImageHome(strHomeTeam)
            //ambil image away
            presenter.getImageAway(strAwayTeam)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showDetail(jsonResponse: List<ModelDetailSchedule>) {
        details = ModelDetailSchedule (
                jsonResponse[0].strDate,
                jsonResponse[0].strHomeTeam,
                jsonResponse[0].strAwayTeam)
        swipeRefresh.isRefreshing = false

        val txt_date: TextView = find(date)
        val txt_homeTeam: TextView = find(homeTeam)
        val txt_awayTeam: TextView = find(awayTeam)

        txt_date.text = jsonResponse[0].strDate
        txt_homeTeam.text = jsonResponse[0].strHomeTeam
        txt_awayTeam.text = jsonResponse[0].strAwayTeam
    }

    override fun showImageHome(jsonResponse: List<ModelTeam>) {
        images = ModelTeam (
                jsonResponse[0].strTeamBadge,
                jsonResponse[0].strTeamJersey)
        swipeRefresh.isRefreshing = false

        val img_home: ImageView = find(image_home)
        val img_jersey_home: ImageView = find(jersey_home)

        Glide.with(this).load(jsonResponse[0].strTeamBadge).into(img_home)
        Glide.with(this).load(jsonResponse[0].strTeamJersey).into(img_jersey_home)
    }

    override fun showImageAway(jsonResponse: List<ModelTeam>) {
        images = ModelTeam (
                jsonResponse[0].strTeamBadge,
                jsonResponse[0].strTeamJersey)
        swipeRefresh.isRefreshing = false

        val img_away: ImageView = find(image_away)
        val img_jersey_away: ImageView = find(jersey_away)

        Glide.with(this).load(jsonResponse[0].strTeamBadge).into(img_away)
        Glide.with(this).load(jsonResponse[0].strTeamJersey).into(img_jersey_away)
    }
}


