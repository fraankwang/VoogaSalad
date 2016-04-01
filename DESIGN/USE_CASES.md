#Use Cases: Authoring Environment
1. Create new tower/open tower editor
	2. Set tower image
	3. Set tower name
	4. Set tower damage
	5. Set tower attack speed
	6. Set tower attack type
	7. Set tower attack range
	8. Set tower cost
	9. Set tower upgrades
	10. Set tower rule(s)
11. Create new enemy/open enemy editor
	12. Set enemy image
	13. Set enemy name
	14. Set enemy movement speed
	15. Set enemy health
	16. Set enemy armor type
	17. Set enemy movement type
	18. Set enemy rule(s)
19. Create new level/open level editor
	20. Create path
	21. Add enemy option
	22. Add tower option
	23. Set level rule(s)

#Use Cases: Frontend Player
24. Load Game
25. Award Points
26. Pay for tower
27. Change time step
28. Drag and drop tower
29. Pause and play capabilities
30. Play phase tower upgrades
31. Enemy status

#Use Cases: Backend General
* Run Game
	33. Be able to run game one step at a time (step function)
* Update Game State
	34. Check if Game is won or not
	35. Check all rules
	36. Update Game Score
	37. Update Game Value (how much money?)
* Save Game 
	38. Create XML of Game 
* Load Game 
	39. Load XML file and create Game object
	40. Take in Game object 
* Engine
	* Modes
		41. Allow user to organize game into different modes. 
* Rules
	42. Allow user to write tower rules
	43. Allow user to write enemy rules
	44. Allow user to write win/loss rules
* Game Statistics
	45. Keep track of game score
	46. Keep track of game money
* Authoring
	47. Create a tower with a specific type of bullets
	48. Create an enemy with a specific health
	49. Create an enemy from a loaded image
	50. Create a tower with a specific range of firing 
	51. Create a mode with a set number of levels
	52. Set a map for a specific level
	53. Create a list of sprites that can be added per level during game play


