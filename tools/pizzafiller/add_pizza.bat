@echo off
set /p pw="Enter password: "
set /p server=<config.cfg
echo select 1; | mysqlsh --sql --uri %server% --password=%pw% --database=pizzaorder > nul 2>&1
if errorlevel 1 (
   echo Wrong password or configuration!
   exit /b %errorlevel%
)

echo.
set /p pname="Enter pizza name: "
set /p pprice="Enter pizza price: "

echo insert into pizza (name, price) values ('%pname%', %pprice%); | mysqlsh --sql --uri %server% --password=%pw% --database=pizzaorder > nul 2>&1
if errorlevel 1 (
   echo Pizza with that name already exists or wrong character in price!
   exit /b %errorlevel%
)

echo select id from pizza where name='%pname%'; | mysqlsh --sql --uri user@localhost:3306 --password=root --database=pizzaorder > tempres.txt
For /F "UseBackQ Delims==" %%A In ("tempres.txt") Do Set "pid=%%A"
del /f "tempres.txt"

:loop
set /p paddon="Enter pizza addon (type end if done): "
if '%paddon%' == 'end' (
	exit /b %errorlevel%
)
echo insert into pizza_addons (pizza_id, addons) values (%pid%, '%paddon%'); | mysqlsh --sql --uri %server% --password=%pw% --database=pizzaorder > nul 2>&1
goto loop
