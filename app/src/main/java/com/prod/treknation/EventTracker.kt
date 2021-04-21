package com.prod.treknation

import com.amplitude.api.Amplitude
import org.json.JSONObject

class EventTracker {

    companion object {
        var emailId: String? = null
        var userName: String? = null

        var email: String = "email"
        var name_value: String = "Name_value"
        val get_started: String = "get_started";
        val stage_pr_process_select = "stage_pr_process_select"
        val value = "value"
        val stage_pr_process_value = "stage_pr_process_value"
        val user_details = "user_details"
        val location_details = "location_details"
        val no_of_applications = "no_of_applications"
        val ee_draws = "ee_draws"
        val dashboard_onboarded = "dashboard_onboarded"
        val dashboard_tab = "dashboard_tab"
        val dashboard_EEDraws = "dashboard_EEDraws"
        val dashboard_CRSCalculator = "dashboard_CRSCalculator"
        val dashboard_Blogs = "dashboard_Blogs"
        val crs_relation_status = "crs_relation_status"
        val crs_total_score = "crs_total_score"
        val crs_age_score = "crs_age_score"
        val settings_menu = "settings_menu"


        fun logEvent(string: String) {
            Amplitude.getInstance().logEvent(string)

        }

        fun logEvent(string: String, map: HashMap<String, String>) {
/*
            emailId?.let {
                map.put(email, it)
            }

            userName?.let {
                map.put(name_value, it)
            }
*/
            val jsonObject = JSONObject(map as Map<*, *>)
            Amplitude.getInstance().logEvent(string, jsonObject)

        }

        fun setUserProperties(email: String, usersName: String) {

            // Log revenue in events
            val userProperties = JSONObject()
            userProperties.put("email", email)
            userProperties.put("Name", usersName)
            Amplitude.getInstance().setUserProperties(userProperties)


        }
    }
}