# CPS3230-Software-Project-Part-2
Fundamentals of Software Testing Assignment Part 2 (of 3)

## MarketAlert UM 
MarketAlertUM.com is a website that provides customised alerts to users when their favourite online stores or marketplaces list a product that may be of interest to them. You have been hired as a software engineer to help develop and test the system.

## Info: User Interface
The website can be accessed at https://www.marketalertum.com. The website allows you to log in, view alerts customised to you, and log out.

## Task 2: Runtime verification
Using the symbolic automaton specified in the documentation/report, program and compile a monitor which is able to verify your system at runtime - Using Eclipse as an IDE and Larva as a runtime verification tool. 

code for task 2: CPS3230-Software-Project-Part-2/cps3230Assignment-RuntimeVerification/

## Task 3: Model-based testing
Using the symbolic automaton specified for Task 1, implement a model-based test suite for your system. Document the output of your test suite, particularly by showing bugs detected and the coverage metrics achieved. Used a screenscraping approach to check if user is logged in, logged out etc etc. 
Used Intellij as in IDE and implemented a mavel project. 

code for task 3: CPS3230-Software-Project-Part-2/cps3230Assignment-ModelTesting/
 - guards, transitions and tests can be found: CPS3230-Software-Project-Part-2/cps3230Assignment-ModelTesting/src/test/java/com/cps3230/
 - Note the above are separted into 2, as my FSM was split into two parts for simplicity: login system and alert system
   - loggin system checking if user is logged in - bad or good login 
   - on good login the user is allowed to add new alert/s (unless maximum is reached) and delete the alerts via post and delete requests


