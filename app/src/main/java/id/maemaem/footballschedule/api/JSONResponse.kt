package id.maemaem.footballschedule.api

import id.maemaem.footballschedule.model.ModelDetailSchedule
import id.maemaem.footballschedule.model.ModelSchedule
import id.maemaem.footballschedule.model.ModelScore
import id.maemaem.footballschedule.model.ModelTeam

class JSONResponse {
    private val events: List<ModelSchedule>? = null
    fun getEvents(): List<ModelSchedule>? {
        return events
    }


    private val event: List<ModelDetailSchedule>? = null
    fun getEvent(): List<ModelDetailSchedule>? {
        return event
    }

    private val teams: List<ModelTeam>? = null
    fun getTeams(): List<ModelTeam>? {
        return teams
    }
}