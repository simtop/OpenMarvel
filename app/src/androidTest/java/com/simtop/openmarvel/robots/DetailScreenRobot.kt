package com.simtop.openmarvel.robots

fun detailScreen(func: DetailScreenRobot.() -> Unit) = DetailScreenRobot()
    .apply { func() }

open class DetailScreenRobot : BaseTestRobot() {

}