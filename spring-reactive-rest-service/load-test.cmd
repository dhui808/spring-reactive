echo off

set volume=%~1

if "%volume%" == "" set volume=10

set STARTTIME=%TIME%
set /a STARTTIME=(%STARTTIME:~0,2%) * 3600 + (1%STARTTIME:~3,2%-100) * 60 + (1%STARTTIME:~6,2%-100)

for /l %%a in (1, 1, %volume%) do (
	curl localhost:8080/hello
)

set ENDTIMEime=%TIME%
set /a ENDTIMEime=(%ENDTIMEime:~0,2%) * 3600 + (1%ENDTIMEime:~3,2%-100) * 60 + (1%ENDTIMEime:~6,2%-100)

set /a total_time=%ENDTIMEime% - %STARTTIME%
echo total_time=%total_time%
