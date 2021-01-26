## League of legends esports bot

[![Dashboard](https://img.shields.io/static/v1?label=AWS&message=Dashboard&color=green)](https://cloudwatch.amazonaws.com/dashboard.html?dashboard=Predictions-Bot-Dashboard&context=eyJSIjoidXMtZWFzdC0xIiwiRCI6ImN3LWRiLTY1MzUyODg3Mzk1MSIsIlUiOiJ1cy1lYXN0LTFfWWdlV3dsS0tGIiwiQyI6Ijc4OHJ1bGIzdDNvaTc3dTJjbGhoOTlzbGNpIiwiSSI6InVzLWVhc3QtMTo0ODhlOWRmNi1hOThlLTQzMTItOGE0YS0zMzZkYTVkNzI2ZWMiLCJNIjoiUHVibGljIn0=)
[![Discord Chat](https://img.shields.io/discord/802610953396551720?label=support)](https://discord.gg/Dvq8f5KxZT)  


## Bot Usage
This bot stores user data about predictions and various settings the user specifies. You can request this data at any time.

## Info
This bot provides schedule, results, and standings for all regions listed in the esports api.
To add this bot to your discord server [Click here](https://discord.com/api/oauth2/authorize?client_id=725169546633281628&permissions=2112&scope=bot)

For support please file a Github issue: https://github.com/mckernant1/lol-predictions-bot/issues/new

### Commands
!info lists this menu

\<league\> refers to one of the league codes (lcs, lpl, lck, ...) 

[number of matches] is optional, picks the number of matches to display. Default is the next or previous days matches

### Esports Commands
`!schedule <league> [number of matches]` -> Displays the upcoming games for the region

`!results <league> [number of matches]` -> Displays the most recent results for the region 

`!standings <league>` -> Displays the standings for the region 
`!roster <team code>` -> Displays the teams roster

### Predictions Commands
`!predict <league> [number of matches]` -> Prints a message where you can set your predictions. Disappears after 5 mins.

`!predictions <league> [number of matches]` -> Prints out he predictions for upcoming matches

`!report <league> [number of matches]` -> Reports the most recent matches and who predicted what

`!stats <league> [number of matches]` -> Displays the predictions standings. Default number of matches is the whole split

### User Settings

`!setTimezone <Timezone>` -> This will set your timezone. Timezone should be formatted like America/Los_Angeles. If you try PST or a code it will tell your what it should be
