@echo off
rem arun.bat
rem Format: arun masfilename_less_extension
if "%1"=="" goto err
mas %1  
@echo off            
if errorlevel 1 goto end
sim %1 %2 %3 %4 %5 %6 %7 %8 %9 /z
goto end
:err
echo Error: File name not specified
echo Format: arun masfilename_less_extension
:end
