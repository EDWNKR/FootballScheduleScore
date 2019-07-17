package id.maemaem.footballschedule.prevMatch

import id.maemaem.footballschedule.model.ModelScore

interface ScoreView {
    fun showLoading()
    fun hideLoading()
    fun showPrevSchedule(jsonResponse: List<ModelScore>)
}