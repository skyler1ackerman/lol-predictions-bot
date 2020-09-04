package com.github.mckernant1.runner.commands

import com.github.mckernant1.runner.utils.getLeagues
import com.github.mckernant1.runner.utils.getWordsFromMessage
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

abstract class DiscordCommand(protected val event: MessageReceivedEvent) {

    protected lateinit var region: String

    protected var numToGet: Int = 0

    abstract suspend fun execute()

    abstract fun validate(): Boolean


    protected fun validateNumberOfMatches(event: MessageReceivedEvent, position: Int, default: Int = 4): Boolean {
        val words = getWordsFromMessage(event.message)
        numToGet = words.getOrNull(position)?.toInt() ?: default
        return (numToGet in 1..19)
            .also { println("validateNumberOfMatches with number $numToGet and result: $it") }
    }

    protected fun validateRegion(event: MessageReceivedEvent, position: Int): Boolean {
        region = getWordsFromMessage(event.message)[position]
        return (getLeagues().find { it.name.equals(region, ignoreCase = true) } != null)
            .also { println("validateRegion with region: $region and result: $it") }
    }

    protected fun validateWordCount(event: MessageReceivedEvent, range: IntRange): Boolean {
        return getWordsFromMessage(event.message).size in range
    }

}
