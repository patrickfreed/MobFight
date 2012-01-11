MobFight WIP
By: PatrickFreed

====================
General Description
====================
Someone with gameleader permission types /mobfight create game game1 25 15.
This starts a server wide game where players in that game can only hurt players on
the opposing team. Now, in order to join the game, they type /mobfight join <game>
<mob> and are teleported to a team's spawn, setby /mobfight setspawn team1. 
They are tp'd there and transformed into the mob they have chosen. Each mobs have their own powers (see below). You get 1 point for each
kill and -1 for each suicide (configurable, see config below). When the score limit
is reached, the game is ended and a new one starts with empty teams.

================
Economy Support
================
The game creator can choose to have the winners gain a certain amount of money when
they win. Now, to prevent money boosting, that amount is taken from every player at
the beginning of the game and returned double to the winning team. The money is not
actually transferred, so it is irrelevant how much money is put into the pot.

Each member of the winning team only gain what is specefied by the game creator,
regardless of how much the other team put in.

===========
Config.yml
===========
Points-per-kill: 1
Points-per-suicide: -1
Winning Message: You just won game +game by defeating +team2!
Losing Message: You just lost game +game to +team2!
Kill Message: You gain +points for killing +killedplayer!
Death Message: You were killed by +killingplayer!
UseEconomy: true

===================
Mob Powers (Draft)
===================
Pig: gives health to nearby teammates or gives health when hit by other teammates.
Zombie: Slightly stronger, hand to hand combat (relatively weak) with strong bite (if bite misses, mouth starts to bleed, loses health)
Skeleton: Weak mob, but has long range bow.
Cow: Very strong mob, acts as a moving shield. Attack too, but very weak.
Creeper: Very weak, but can explode. (Keep in mind it counts as suicide)
Chicken: very small and sneaky, might be able to fly (weak peck, drops egg bombs [if I can get fly to work])
Sheep: Probably won't be in the plugin.
Slime: Probably won't be in the plugin.
Spider: Heavy attack but is large and slow. Not sure if the wall climbing will be possible.
Squid: Will probably not be in the plugin until per-mob spawns can be set.

=================
Folder Structure
=================
plugins/Mobfight
config.yml
plugins/MobFight/Data
<gamename>.yml
arenas.yml
players.yml

==========
Commands:
==========
    /mobfight join [game] 
        - Joins team in game
            - mobfight.join
    /mobfight end [game] 
        - Ends the game, assigns a winner, and deletes player lists from file
            - mobfight.gameleader
    /mobfight leave - done
        - User exits the game, teleports to sign room of team player left
            - mobfight.leave
    /mobfight delete [game] 
        - deletes game from file, ends and selects a winner
            - mobfight.*
    
    /mobfight removeplayer [player] - done
        - removes player from team in game
            - mobfight.*

    /mobfight setspawn [game] [team] - done
        - sets where the people go to after right clicking sign
            - mobfight.*
            - leader of game
    
    /mobfight creategame <game> <arena> <score limit> [money] - done
        - creates game with team and team with score limit, money is optional; creator is a game leader
            - mobfight.*
    
    /mobfight setwarp <arena> - done

    Permissions (Misc)
    - mobfight.*
        - all commands
    - mobfight.basic
        - mobfight.leave
        - mobfight.join
            - ability to right click signs to join games