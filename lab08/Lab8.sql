----------------8.1---------------

-- SELECT * FROM game ORDER BY time DESC;

-- SELECT * FROM game WHERE time >= '2018-10-14 12:00:00' ORDER BY time DESC;

--SELECT * FROM player WHERE name <> '';

--SELECT * FROM playergame WHERE score >= 2000;

-- SELECT * FROM player WHERE RIGHT(emailAddress,9) = 'gmail.edu';

------------------8.2---------------------------
--SELECT * FROM player,playergame WHERE player.ID = playergame.PlayerID AND player.name='The King';

-- SELECT player.name FROM playergame, player,game WHERE game.time='2006-06-28 13:20:00' AND game.ID = playergame.gameID AND playergame.playerID = player.ID AND playergame.score != 0.00;

-- c. Makes sure Id of the first instance of player table is greater then the second instance of the player table, all whiel making sure their names are equal. 

-- d. Perhaps you want to find the permutations of x players,
-- for this you could usse a self join to find the different
-- combinations of players that are not you and you, but you and
-- everyone else..
