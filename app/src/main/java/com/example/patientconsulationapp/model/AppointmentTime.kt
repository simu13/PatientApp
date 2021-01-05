package com.example.patientconsulationapp.model

import java.io.Serializable

class AppointmentTime(
    var name:String ="",
    var time:ArrayList<Times> = ArrayList()
):Serializable