Content of the GitHub Repository:




## Table of content

1. [Content of the GitHub Repository](content)
2. [Work Load Distribution](distribution)
3. [About](about)
4. [Setup Instructions](setup)
4. [Prerequisites](#prerequisites)
5. [Getting Started](#getting-started)
6. [Releases](#releases)
7. [Contributors](contributors)

## Work Load Distribution  

**Carina Cölestin:**   

* Design  
* Documentation  
* Implementation Template  
* HTML
* Testing

**Nikolaus Hribernig:**  
  
* Database  
* DAOs 
* Add Cocktail
* Control  
* Security  

**Patrick Plank:**  
  
* DAOs  
* Controller  
* Models  
* Dependencies  
* Testing


## Content of the GitHub Repository
Files for the final version; they should be tagged with an adequate name like "final version", "last final version","new final version" :-) ,.... otherwise we take the last commit of the master branch
Installation instructions (README.md) with all vital information for the setup. (Very important !!!)
Don' forget: Also include all SQL-Scripts needed for adding test data (only if necessary)
Content of README.md

This file must be in the root folder of your project and it contains the following information:

Name of the team members
Short summary of the work load distribution (who did what?)
Optional: Last minute information / Release Notes
Setup instructions
Make sure that your setup instructions are complete and error free by installing your project solely based on the information that's found in this document!
Provide information about "hidden" URLs to execute controller methods to fill the database, like "/fillData" in our lab session examples. (only if necessary)
This document must contain 100% precise instructions on how to perform the actual installation. Be sure to have this process thoroughly tested!

Hint: For this file you have to use Markdown language. You can find cheat sheets for markdown on this pages;

https://guides.github.com/features/mastering-markdown/
https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet
You can upload this file as part of a commit/push or you can create it online in GitHub.


## About
Cocktail Lounge should support hobby barkeepers and professionals to share their own creations of drinks and
have an insight to classical recipes to improve their knowledge about drink-mixing. There should be a
Guestbook/Message board where registered users can communicate with each other. The user should be able
to create an account. Unregistered users should be able to search / view recipes. Registered users should have
the same features and additional can create new recipes, rate recipes and comment on recipes. There should
also be an administrative account which should be able to delete recipes/comments.

* Planning Phase  
	> Project proposal  
	> User Requirements Specification  
	> Planning database structure  
	> Choosing a bootstrap design    
 
 
* Development Phase  
	> Implementing the database  
	> Developing the website design  
	> Implementing the Account structure  
	> Implementing of different security features (hiding of id’s in browser tab etc.)


* Testing Phase  
	> Filling the database with objects  
	> Testing of the different features (rating, commenting)  
   
   
* Presenting Phase  
	> Presenting of the Webpage with the complete features set  


Our Data model includes the following relationships:  
* Cocktail - User  
* Cocktail - Comment  
* Cocktail - Rating  

## Setup Instructions

1. You can fork our project on GitHub and open it in your prefered IDE.  
<a href="https://https://github.com/x-qlusive/CocktailLounge">https://https://github.com/x-qlusive/CocktailLounge</a>

2. The other way is to open eclipse and and import the project direct from the GitHub Repository.

After importing the project you need to adapt the db.properties in /src fitting your database location. 

If you do not use MySQL you need to change hibernate.dialect in the dispatcher-servlet.xml according to your used database. 

Set up a Apache Tomcat 8.0 Server and start project. 


## Prerequisites
To create our Website we worked with Thymeleaf Version 3.0.3, Spring Version 4.3.7, Spring Security Version 4.2.2, Maven Version 3.5.1.
xcfx


## Getting Started

The first step was to think about the idea to create a Website which makes it possible for users to inscribe it's personal creations of cocktails in the easiest way and to store it in a database. Primarily we established a concept which includes contents like design, functionality, task distribution, and other technical details or project management questions. Afterwards we talked about the most important tasks and the time management. Furthermore we have to use gitHub, which
makes it easier for all team members to interact with each other.  


## Releases
The final due was on June 22nd, 2017


## Contributors

This project was created by
* <a href="https://github.com/SuperCari">Cölestin Carina</a>
* <a href="https://github.com/HribernigNikolaus">Hribernig Nikolaus</a>
* <a href="https://github.com/x-qlusive">Plank Patrick</a>


