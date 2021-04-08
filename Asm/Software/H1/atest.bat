@echo off
rem atest.bat
@echo =====================================================
@echo This batch file compares the model version of 
@echo masv1 with the student-written version.
@echo The first three test cases should assemble
@echo without errors.  The remaining test cases have
@echo errors for which the two assemblers should      
@echo produce similar error messages.
@echo =====================================================
pause
if "%1"=="java" goto javatest
if exist masv1.exe goto modelck
goto missingsvc
:javatest
if exist masv1.class goto modelck
goto missingsvj
:modelck
if exist masv1m.exe goto proceed
goto missingmv
:proceed
@echo ===== aprog1 ========================================
masv1m aprog1
if errorlevel 1 goto badtc
mycopy aprog1.mac savemac
del aprog1.mac
@echo -----
%1 masv1 aprog1
if errorlevel 1 goto bad1
myfc aprog1.mac savemac
if errorlevel 1 goto bad1c
@echo -----
@echo aprog1 ok
goto done1
:bad1c  
@echo -----
@echo ERROR: masv1m/masv1 discrepancy on aprog1
goto done1
:bad1
@echo -----
@echo ERROR: Bad return code from masv1 assembly of aprog1
:done1
if exist aprog1.mac del aprog1.mac
if exist savemac del savemac
@echo =====================================================
pause

@echo ===== aprog2 ========================================
masv1m .\aprog2
if errorlevel 1 goto badtc
mycopy aprog2.mac savemac
del aprog2.mac
@echo -----
%1 masv1 .\aprog2
if errorlevel 1 goto bad2
myfc aprog2.mac savemac
if errorlevel 1 goto bad2c
@echo -----
@echo aprog2 ok
goto done2
:bad2c  
@echo -----
@echo ERROR: masv1m/masv1 discrepancy on aprog2
goto done2
:bad2
@echo -----
@echo ERROR: Bad return code from masv1 assembly of aprog2
if exist aprog2.mac del aprog2.mac
if exist savemac del savemac
:done2
@echo =====================================================
pause

@echo ===== aprog3 ========================================
mycopy aprog3.mas x.y
masv1m x.y
if errorlevel 1 goto badtc
mycopy x.mac savemac
del x.mac
@echo -----
%1 masv1 x.y
if errorlevel 1 goto bad3
myfc x.mac savemac
if errorlevel 1 goto bad3c
@echo -----
@echo aprog3 ok
goto done3
:bad3c  
@echo -----
@echo ERROR: masv1m/masv1 discrepancy on aprog3
goto done3
:bad3
@echo -----
@echo ERROR: Bad return code from masv1 assembly of aprog3
if exist x.mac del aprog3.mac
if exist savemac del savemac
:done3
@echo =====================================================
@echo =====================================================
@echo Test cases with errors now follow.
@echo The error messages from the two assemblers should be similar.
pause

@echo ===== aprog4 ========================================
masv1m aprog4
@echo -----
%1 masv1 aprog4
if errorlevel 1 goto t4
@echo Error: masv1 should return a bad return code on aprog4
:t4
@echo =====================================================
pause

@echo ===== aprog5 ========================================
masv1m aprog5
@echo -----
%1 masv1 aprog5
if errorlevel 1 goto t5
@echo Error: masv1 should return a bad return code on aprog5
:t5
@echo =====================================================
pause

@echo ===== aprog6 ========================================
masv1m aprog6
@echo -----
%1 masv1 aprog6
if errorlevel 1 goto t6
@echo Error: masv1 should return a bad return code on aprog6
:t6
@echo =====================================================
pause

@echo ===== aprog7 ========================================
masv1m aprog7
@echo -----
%1 masv1 aprog7
if errorlevel 1 goto t7
@echo Error: masv1 should return a bad return code on aprog7
:t7
@echo =====================================================
pause

@echo ===== aprog8 ========================================
masv1m aprog8 
@echo -----
%1 masv1 aprog8
if errorlevel 1 goto t8
@echo Error: masv1 should return a bad return code on aprog8
:t8
@echo =====================================================
pause

@echo ===== aprog9 ========================================
masv1m aprog9
@echo -----
%1 masv1 aprog9
if errorlevel 1 goto t9
@echo Error: masv1 should return a bad return code on aprog9
:t9
@echo =====================================================
pause

@echo ===== aprog10 =======================================
masv1m aprog10
@echo -----
%1 masv1 aprog10
if errorlevel 1 goto t10
@echo Error: masv1 should return a bad return code on aprog10
:t10
@echo =====================================================
pause

@echo ===== aprog11 =======================================
masv1m aprog11
@echo -----
%1 masv1 aprog11
if errorlevel 1 goto t11
@echo Error: masv1 should return a bad return code on aprog11
:t11
@echo =====================================================
pause

@echo ===== aprog12 =======================================
masv1m aprog12
@echo -----
%1 masv1 aprog12
if errorlevel 1 goto t12
@echo Error: masv1 should return a bad return code on aprog12
:t12
@echo =====================================================
pause

@echo ===== aprog13 =======================================
masv1m aprog13
@echo -----
%1 masv1 aprog13
if errorlevel 1 goto t13
@echo Error: masv1 should return a bad return code on aprog13
:t13
@echo =====================================================
pause

@echo ===== no input file specified on the command line ===
masv1m
@echo -----
%1 masv1
if errorlevel 1 goto t14
@echo Error: masv1 should return a bad return code
:t14
@echo =====================================================
pause

@echo ===== non-existent input file =======================
if exist garbage.ggg del garbage.ggg
masv1m garbage.ggg
@echo -----
%1 masv1 garbage.ggg
if errorlevel 1 goto t15
@echo Error: masv1 should return a bad return code
:t15
@echo =====================================================
pause

goto done

:badtc
@echo ERROR: masv1m failure--check masv1m and test cases

:missingsvj
@echo ERROR: Student version of masv1 (masv1.class) not found
goto done
:missingsvc
@echo ERROR: Student version of masv1 (masv1.exe) not found
goto done
:missingmv
@echo ERROR: Model version of masv1 (masv1m.exe) not found
goto done
:done
if exist x.y del x.y
if exist x.mac del x.mac
if exist savemac del savemac
if exist aprog1.mac del aprog1.mac
if exist aprog2.mac del aprog2.mac
if exist aprog3.mac del aprog3.mac
if exist aprog4.mac del aprog4.mac
if exist aprog5.mac del aprog5.mac
if exist aprog6.mac del aprog6.mac
if exist aprog7.mac del aprog7.mac
if exist aprog8.mac del aprog8.mac
if exist aprog9.mac del aprog9.mac
if exist aprog10.mac del aprog10.mac
if exist aprog11.mac del aprog11.mac
if exist aprog12.mac del aprog12.mac
if exist aprog13.mac del aprog13.mac

