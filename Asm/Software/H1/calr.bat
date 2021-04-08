@echo off
rem calr.bat
rem Format: calr cppfilename_less_extension
if "%1"=="" goto err
mas %1.cpp              
if errorlevel 1 goto end
lin %1 sup
if errorlevel 1 goto end
sim %1 %2 %3 %4 %5 %6 %7 %8 %9/z
goto end
:err
echo Error: File name not specified
echo Format: calr cppfilename_less_extension
:end
