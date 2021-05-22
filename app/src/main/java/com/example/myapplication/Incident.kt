package com.example.myapplication



data class Incident(
    val STATUS: String,
    val TICKETID: String,
    val REPORTEDBY: String,
    val CLASSIDMAIN : String,
    val CRITIC_LEVEL: Int,
    val ISKNOWNERRORDATE: String?,
    val TARGETFINISH: String?,
    val DESCRIPTION: String,
    val EXTSYSNAME: String,
    val NORM: String,
    val LNORM: String
)
