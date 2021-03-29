package com.github.mckernant1.lol.blitzcrank.commands.misc

import com.github.mckernant1.lol.blitzcrank.commands.DiscordCommand
import com.github.mckernant1.lol.blitzcrank.utils.userSettingsTable
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

class SetPastaCommand(event: MessageReceivedEvent) : DiscordCommand(event) {
    override suspend fun execute() {
        userSettings.pasta = event.message.contentDisplay.replace("!setPasta ", "")
        userSettingsTable.putSettings(userSettings)
        event.channel.sendMessage("Your Pasta has been set to ${userSettings.pasta}").complete()
    }

    override fun validate(): Boolean = validateWordCount(2..100)
}
