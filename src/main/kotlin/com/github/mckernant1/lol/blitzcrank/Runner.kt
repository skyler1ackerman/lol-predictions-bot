package com.github.mckernant1.lol.blitzcrank

import com.github.mckernant1.assertions.Assertions.assertEnvironmentVariablesExist
import com.github.mckernant1.lol.blitzcrank.core.MessageListener
import com.github.mckernant1.lol.blitzcrank.register.infoCommands
import com.github.mckernant1.lol.blitzcrank.register.lolCommands
import com.github.mckernant1.lol.blitzcrank.register.pastaCommands
import com.github.mckernant1.lol.blitzcrank.register.settingsCommands
import com.github.mckernant1.lol.blitzcrank.timers.publishBotMetrics
import com.github.mckernant1.lol.blitzcrank.timers.reminderChecker
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.ChunkingFilter
import net.dv8tion.jda.api.utils.MemberCachePolicy
import net.dv8tion.jda.api.utils.cache.CacheFlag
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("MainLogger")

fun main() {
//    convertPredictions()
    assertEnvironmentVariablesExist(
        "BOT_TOKEN",
        "ESPORTS_API_KEY",
        "PREDICTIONS_TABLE_NAME",
        "USER_SETTINGS_TABLE_NAME",
        "BOT_APPLICATION_ID"
    )
    val botToken = System.getenv("BOT_TOKEN")
    val bot = startBot(botToken)
    registerCommands(bot)
    publishBotMetrics(bot)
    reminderChecker(bot)
}

fun startBot(token: String): JDA {
    return JDABuilder.create(
        token,
        GatewayIntent.GUILD_MEMBERS,
        GatewayIntent.GUILD_MESSAGES,
        GatewayIntent.GUILD_MESSAGE_TYPING,
        GatewayIntent.GUILD_MESSAGE_REACTIONS,
        GatewayIntent.DIRECT_MESSAGES,
        GatewayIntent.DIRECT_MESSAGE_REACTIONS,
        GatewayIntent.DIRECT_MESSAGE_TYPING
    )
        .disableCache(
            CacheFlag.ACTIVITY,
            CacheFlag.VOICE_STATE,
            CacheFlag.EMOTE,
            CacheFlag.CLIENT_STATUS,
            CacheFlag.ONLINE_STATUS
        ).setChunkingFilter(ChunkingFilter.exclude(264445053596991498))
        .setMemberCachePolicy(MemberCachePolicy.ALL)
        .addEventListeners(MessageListener())
        .build()
        .awaitReady()
}

private fun registerCommands(
    bot: JDA
) {
    listOf(
        infoCommands,
        lolCommands,
        pastaCommands,
        settingsCommands
    ).flatten()
        .forEach {
            logger.info("Upserting command ${it.name}")
            bot.upsertCommand(it).complete()
        }
    logger.info("Done inserting commands!")
}
