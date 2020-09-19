## League of legends esports bot
This bot provides schedule, results, and standings for all regions listed in the esports api

For support please file a Github issue: https://github.com/mckernant1/lol-predictions-bot/issues/new

### Commands
!info lists this menu

\<league\> refers to one of the league codes (lcs, lpl, lck, ...) 

[number of matches] is optional, picks the number of matches to display. Default is 4

### Esports Commands
!schedule <league> [number of matches] -> Displays the upcoming games for the region

!results <league> [number of matches] -> Displays the most recent results for the region 

!standings <league> -> Disaplys the standings for the region 

### Predictions Commands
!predict <league> [number of matches] -> Prints a message where you can set your predictions. Disappears after 5 mins.

!predictions <league> [number of matches] -> Prints out he predictions for upcoming matches

!report <league> [number of matches] -> Reports the most recent matches and who predicted what

!stats <league> -> Displays the predictions standings
