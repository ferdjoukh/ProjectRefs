![Java CI with Maven](https://github.com/ferdjoukh/ProjectRefs/workflows/Java%20CI%20with%20Maven/badge.svg)

# ProjectRefs

is a small java tool for generating **project References** in **PowerPoint** format.

## How it works ?

1. Project References (Title, Client, Context, Solution, Banner image, etc) are written in a simple textual language (see provided examples)
1. The tool checks that your files are valid (no missing mandatory data)
1. A PowerPoint is generated according to a template (master slide).

### Advantages

1. Quick and easy to use
1. Possibility to update the template and easily regenerate all references 
1. Checks force the harmonization of all the references of a team

## Write a Reference in textual language

A reference is written using a simple textual format (based on ini format).

It requires the following data :

1. **header** section
	- title
	- client
	- image
1. **context** section
	- list (separated with semicolons)
1. **objectives** section
1. **results** section
1. **solution** section


Some other data can be specified in header section :

- author
- team
- manager
- duration (a string)
- people (number of FTE)
- language

### An example

```ini
[header]

title=PerfoRevolution : A Workbench for Performance Studies
image=images/image1.jpg
client=Airbus

author=Alice Bob
team=Digital Engineering
language=english
manager=Bob Alice
duration=2 years
people=4 people

[context]

list=context1;context2;context3


[objectives]

list=objective 1;objective 2

[results]

list=result 1;result 2;result 3

[solution]

technologies=Eclipse RCP;Model-Driven Engineering;EMF;Xtext;EMF Forms
programming=Java;Python
devOps=Git;Jenkins;Maven/Tycho;Scrum;Kanban
```


### Some tips

- All keywords are in lower case
- The different elements of context section are separated with semicolons.
- image path are relative to the reference path (if reference is in *docs/ref1* and image in *img/img1.png*, then the tool will find the image in : *docs/img/img1.png*) 
- The naming of sub lists in solution section is free

## Run the tool

1. download the zip of the application. It contains :
	1. The executable jar file (which comes with all dependencies)
	1. The pptx template file (mandatory)
	1. some examples
	1. This README document
1. Unzip the previous archive
1. Ensure that Java is installed in your computer
1. Run the help command
	
	 `java -jar projectRefs.jar h`

1. Generate the PPTX of a given example :

	 `java -jar projectRefs.jar f resources/anExample o test.pptx`

1. Create your own references	 
