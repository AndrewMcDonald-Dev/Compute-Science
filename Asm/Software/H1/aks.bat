@echo off
rem aks.bat
rem Format: aks masfilename_less_extension
if "%1"=="" goto err
mas %1              
if errorlevel 1 goto end
lin %1 ksup
if errorlevel 1 goto end
sim %1 %2 %3 %4 %5 %6 %7 %8 %9
goto end
:err
echo Error: File name not specified
echo Format: aks masfilename_less_extension
:end
