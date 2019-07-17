package id.maemaem.footballschedule.nextMatch

import id.maemaem.footballschedule.model.ModelSchedule

interface ScheduleView {
    fun showLoading()
    fun hideLoading()
    fun showNextScheduleRetro(jsonResponse: List<ModelSchedule>)
}