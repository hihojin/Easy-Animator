# Easy-Animator
OOP final project following MVC design pattern.


![night](https://user-images.githubusercontent.com/74577100/193709716-c1e45750-a6c1-4948-a70b-5feef74b6a82.gif)

![big-bang-big-crunch](https://user-images.githubusercontent.com/74577100/193709793-624ad81a-f604-48c0-b8fc-8295458849a5.gif)

![toh-5](https://user-images.githubusercontent.com/74577100/193709980-467f60a1-c6e2-40b9-a247-6525385b709b.gif)

MVC Design guide:
	To run the jar file:
	- Open terminal - change directory to desktop - change directory to finalProject; where
	 main.jar is located in. Then you can write this command line to run the program:
	java -cp main.jar controller.EasyAnimator -in smalldemo.txt -speed 10 -view text
	
	(.txt is optional for input file format. If you don't write filname.txt program will
	automatically add .txt and runs the program. However user need to specify output file format
	type to either .txt or .svg. Or, user will get an message asks user to specify the file type)

	- you can run the command line in any order with: -in. -speed, -view, -out.
	** input -in and -view viewname are required parts to run the program. 
	   output file .txt or .svg gets saved to the finalProject folder. (the same folder)**

- Model:
	Overview: 2 parts + main model that uses these 2 parts
	-part 1: shape group
	- shapes: this sub part takes care of the basic shape properties.
		- shape interface has getters and setters that are useful to have to access to
		properties of shapes throughout the project.
			
		- abstract shape class takes care of the getters and setters as all shapes such as
		rectangle and ellipse/oval have the same set of data given by input text.
			
		- oval and rectangle concrete class then have own constructors, calling
		super constructor (abstract class) and implement unique method to own such as 		  
		tostring().
			
		- created an enum class that holds shape type for type safety. Right now only rectangle
		and oval is present; more can be added.
			
		- color class is created so I can then use color object itself.
			
	- part 2: animation effects group
	- animation effects: has 3 effects that can be applied to shapes: scale, move, coloring
		-animation effect interface mainly has getters for flexibility of accessing data of
		effects and also shape. Has getters for animation effect start, end time, shapes.
		Later, I added more getters that is more specific such as by getting
		initial and updated values of shapes due to animating effect.
			
		-created an enum class for animation effect type. I used to include animation type as
		one of the arguments for constructors however later I instead set effects type for each
		class as a way of classification after realizing that I can mistakenly set different
		effect type when initiating for different effect class.
			
		- abstract animation effect class implements the getters specified in the interface,
		initialize sharing fields such as start, end time, getting shapes, and effect type.
		Implemented tweening in the abstract class as a final method which is shared formula
		for each animation effect concrete class. This tweening is here preparing for the
		actual main model class which has a method that gets shapes within a time frame to
		show those shapes in animation by time. Tweening changes shape's values gradually/smoothly
		by time. It is related to animation effect. Thus, implemented here.
			
		- concrete classes: move, scale, color effect implements toString, representation of each
		animation effect unique to each class. These are created in each class as it is quite 
		convienent when adding animation into a data structure in main model class. I can then 
		add each effect object to the data structure.
			
	- easy animator model (main model class)
	- this model has two array list to store shapes in order they are inserted, or in order
	  data is received, and another to store animation, sorted by animation effect start time.
			
	- shapes that have unique name are stored into the shape list.
			
	- animation effects that has same type and overlapping for the shape can't be added.
	  I used comparator class to order animation by start time.
			
	- For getting animated shapes for that time method, I first check whether
	  animation happens around that given time. If so, tween the shape with the same name as
	  effect has, and add all shapes in the returning list is their appear time <= t <= disappear time.
			
	- Calculated animation end time by updating shape's disappear time as disappear time gets
	  updated by animation time in the builderImpl.
			
	- canvas related methods are added as well.

- Controller:
	- I have 1 controller that calls view's display method.

- View:
	-factory view: takes necessary inputs received from main class, pass them to each class
	by their needs. Passes appendable for flexible output source file name for text and svg view.
	(visual views don't require output source).
		
	- view interface has currently two methods that are display and getviewName.
		
	- concrete view classes such as text and svg view takes appendable object as argument
	and increased flexibility of getting output source file.
	
	-(visual view needs to be finished and playback view need to be added.)
		
- utils:
	- reader class is given; builder interface is given; builderImpl class calls model's method
	that add shapes, add motions, and sets the canvas size. 
		
- main: easy animator class parses string[] args sets to each input, output, view, speed,
	calls util's reader and buildImpl class to create model for the program. Calls controller which
	then runs the program! Default value of speed is 1. If user doesn't specify -speed default speed
	is used to run the program.
	 
- miscellaneous: large text input file such as big-bang-big-crunch takes about 10 seconds to load
		 and shows the output.
- TODO: controller needs to be cleaned up
