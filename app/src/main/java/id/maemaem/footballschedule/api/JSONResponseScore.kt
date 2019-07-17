package id.maemaem.footballschedule.api

import id.maemaem.footballschedule.model.ModelDetailSchedule
import id.maemaem.footballschedule.model.ModelSchedule
import id.maemaem.footballschedule.model.ModelScore
import id.maemaem.footballschedule.model.ModelTeam

class JSONResponseScore {
    private val events: List<ModelScore>? = null
    fun getEvents(): List<ModelScore>? {
        return events
    }
}