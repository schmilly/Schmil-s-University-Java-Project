------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all he/she needs to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: Schmillys Akari
PURPOSE OF PROJECT: To Develop a digital version of the Akari Puzzle.
VERSION or DATE: Version (Alpha) 0.1
HOW TO START THIS PROJECT: Press Akar(String) and enter the filename/location of your Akari File
							set out in fromat detailed below. Try and Click bulbs until Solution is 
							found, currently I am not sure if solution will actually appear but the 
							method testing it passed all tests so I would say it should. 

AUTHORS: William "Schmilly" Forrest Stewart van den Wall Bake (Student Number 23086983)

USER INSTRUCTIONS: Puzzle is won by lighting up all squares with bubls, while making sure numbered
					Squares have correct amount of bubls next to them. When Solution is found a Terminal
					Window Should pop up to congratulate you for the victory.
					
					**KNOWN BUGS**
					- I either don't know the solutions to my own puzzle, but I cannot get the Solution Found Screen 
					  to show up.
					- Check Solution Button is also entierly non function when pressing Check Solution, debugging shows that the button press is detected
					just the system does not output the fact that values don't output
					- Despite being programmed in as variables, Windowsize and margins (displacement) can not be 
					  rescaled without breaking the abilitiy to click on Puzzle Squares
					- Currently the puzzle is very slightly offset, so one can click in one square, but if close enough to margins
					  will result in left click being applied to square next to it. This is worsened when Winowsize and or Displacement
					  variables are changed. Also clear button has to be essentially clicked on, or clicked below for it to work properly
