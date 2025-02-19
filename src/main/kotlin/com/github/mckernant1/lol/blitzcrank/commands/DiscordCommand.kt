package com.github.mckernant1.lol.blitzcrank.commands

import com.github.mckernant1.lol.blitzcrank.exceptions.InvalidCommandException
import com.github.mckernant1.lol.blitzcrank.exceptions.LeagueDoesNotExistException
import com.github.mckernant1.lol.blitzcrank.exceptions.TeamDoesNotExistException
import com.github.mckernant1.lol.blitzcrank.model.CommandInfo
import com.github.mckernant1.lol.blitzcrank.model.UserSettings
import com.github.mckernant1.lol.blitzcrank.utils.apiClient
import com.github.mckernant1.lol.blitzcrank.utils.getWordsFromString
import net.dv8tion.jda.api.entities.Message
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

abstract class DiscordCommand(protected val event: CommandInfo) {

    constructor(event: MessageReceivedEvent) : this(CommandInfo(event))
    constructor(event: SlashCommandEvent) : this(CommandInfo(event))

    protected lateinit var region: String
    protected var numToGet: Int? = null
    protected val words = getWordsFromString(event.commandString)

    protected val logger: Logger = LoggerFactory.getLogger("${event.author.id}-${words}")

    protected val userSettings by lazy {
        UserSettings.getSettingsForUser(event.author.id)
    }

    protected val longDateFormat: DateTimeFormatter by lazy {
        DateTimeFormatter
            .ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.LONG)
            .withZone(
                ZoneId.of(userSettings.timezone)
            )
    }

    protected val mediumDateFormat: DateTimeFormatter by lazy {
        DateTimeFormatter
            .ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.MEDIUM)
            .withZone(
                ZoneId.of(userSettings.timezone)
            )
    }

    abstract fun execute(): Unit

    @Throws(InvalidCommandException::class)
    abstract fun validate(): Unit

    protected fun validateNumberPositive(position: Int) {
        numToGet = try {
            words.getOrNull(position)?.toInt()
        } catch (e: NumberFormatException) {
            throw InvalidCommandException("Input must be a number")
        }

        if (numToGet == null || numToGet!! >= 1) {
            logger.info("validateNumberOfMatches with number $numToGet")
        } else {
            throw InvalidCommandException("Number cannot be negative")
        }
    }

    protected fun validateRegion(position: Int) {
        region = words[position]
        try {
            apiClient.getLeagueByCode(region.uppercase())
            logger.info("validateRegion with region: '${region.uppercase()}'")
        } catch (e: Exception) {
            throw LeagueDoesNotExistException("League '$region' does not exist. Available regions: ${
                apiClient.leagues.joinToString(", ") { it.leagueId.uppercase() }
            }")
        }

    }

    protected fun validateTeam(position: Int) {
        val teamName = words[position]
        try  {
            apiClient.getTeamByCode(teamName.uppercase())
            logger.info("Team '$teamName' has been selected")
        } catch(e: Exception) {
            throw TeamDoesNotExistException("Team '$teamName' does not exists")
        }

    }

    protected fun validateWordCount(range: IntRange) {
        if (words.size !in range) {
            throw InvalidCommandException("Invalid word count. There should be between $range words, but there are '${words.size}'")
        }
    }

    protected fun reply(message: String): Message = event.channel.sendMessage(message).complete()
}
