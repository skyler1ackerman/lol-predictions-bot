package com.github.mckernant1.lol.blitzcrank.integration

import org.testng.Assert.assertEquals
import org.testng.annotations.Test

internal class WorldsSchedule : TestBase() {
    @Test(groups = ["integration"], timeOut = testTimeoutMillis)
    fun checkWorldsScheduleOk() {
        val message = testerBotChannel.sendMessage("!schedule worlds").complete()
        var mostRecentMessage = testerBotChannel.history.retrievePast(1).complete().first().contentRaw
        while (mostRecentMessage == "!schedule worlds") {
            println("Waiting for responce")
            mostRecentMessage = testerBotChannel.history.retrievePast(1).complete().first().contentRaw
        }
        assertEquals(message.retrieveReactionUsers("\uD83D\uDC4C").complete().size, 1)
        assertEquals(message.retrieveReactionUsers("❌").complete().size, 0)
    }
}
