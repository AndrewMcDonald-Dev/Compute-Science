@echo off
rem run.bat
rem Format: run machinefilename_with_or_without_extension
if "%1"=="" goto err
sim %1 %2 %3 %4 %5 %6 %7 %8 %9 /z
goto end
:err
echo Error: File name not specified
echo Format: run machinefilename_with_or_without_extension
:end
