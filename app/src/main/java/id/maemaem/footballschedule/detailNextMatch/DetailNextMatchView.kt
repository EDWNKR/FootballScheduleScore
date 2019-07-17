package id.maemaem.footballschedule.detailNextMatch

import id.maemaem.footballschedule.model.ModelDetailSchedule
import id.maemaem.footballschedule.model.ModelTeam

interface DetailNextMatchView {
    fun showLoading()
    fun hideLoading()
    fun showDetail(jsonResponse: List<ModelDetailSchedule>)

    fun showImageHome(jsonResponse: List<ModelTeam>)
    fun showImageAway(jsonResponse: List<ModelTeam>)
}