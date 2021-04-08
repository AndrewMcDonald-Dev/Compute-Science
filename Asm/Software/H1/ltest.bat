@echo off
rem ltest.bat
@echo =====================================================
@echo This batch file compares the model version of linv1
@echo with the student-written version.  The first two
@echo test cases should work without errors.  The remaining
@echo test cases have errors for which the two linkers
@echo should produce similar error messages. 
@echo =====================================================
@echo Hit ENTER to assemble test cases with mas
pause

if "%1"=="java" goto javatest
if exist linv1.exe goto modelck
goto missingsvc
:javatest
if exist linv1.class goto modelck
goto missingsvj
:modelck
if exist linv1m.exe goto proceed
goto missingmv
:proceed

mas /n lmod1
if errorlevel 1 goto badtc1
mas /n lmod2
if errorlevel 1 goto badtc2
mas /n lmod3
if errorlevel 1 goto badtc3
mas /n lmod4
if errorlevel 1 goto badtc4
mas /n lmod5
if errorlevel 1 goto badtc5
mas /n lmod6
if errorlevel 1 goto badtc6
mas /n lmod7
if errorlevel 1 goto badtc7
mas /n lmod8
if errorlevel 1 goto badtc8
mas /n lmod9
if errorlevel 1 goto badtc9
mas /n lmod10
if errorlevel 1 goto badtc10
mas /n lmod11
if errorlevel 1 goto badtc11
mas /n lmod12
if errorlevel 1 goto badtc12
mas /n lmod13
if errorlevel 1 goto badtc13
mas /n lmod16
if errorlevel 1 goto badtc16
mas /n lmod17
if errorlevel 1 goto badtc17
mas /n lmod18
if errorlevel 1 goto badtc18
@echo =====================================================
@echo Hit ENTER to start testing
pause

@echo ===== lmod1, lmod2 ==================================
linv1m lmod1 lmod2
if errorlevel 1 goto badtc
mycopy lmod1.mac savemac
del lmod1.mac
@echo -----
%1 linv1 lmod1 lmod2
if errorlevel 1 goto bad12
myfc lmod1.mac savemac
if errorlevel 1 goto bad12c
@echo -----
@echo lmod1, lmod2 ok
goto done12
:bad12
@echo -----
@echo ERROR: Bad return code on linv1 link of lmod1, lmod2
goto done12
:bad12c
@echo -----
@echo ERROR: linv1m/linv1 discrepancy on lmod1, lmod2
:done12
if exist lmod1.mac del lmod1.mac
if exist savemac del savemac
@echo =====================================================
pause

@echo ===== lmod3, lmod4 ==================================
mycopy lmod4.mob x.y
linv1m .\lmod3 lmod4
if errorlevel 1 goto badtc
mycopy lmod3.mac savemac
del lmod3.mac
@echo -----
mycopy lmod4.mob x.y
%1 linv1 .\lmod3 x.y
if errorlevel 1 goto bad34
myfc lmod3.mac savemac
if errorlevel 1 goto bad34c
@echo -----
@echo lmod3, lmod4 ok
goto done34
:bad34
@echo -----
@echo ERROR: Bad return code on linv1 link of lmod3, lmod3
goto done34
:bad34c
@echo -----
@echo ERROR: linv1m/linv1 discrepancy on lmod3, lmod4
:done34
if exist lmod3.mac del lmod3.mac
if exist savemac del savemac
@echo =====================================================
@echo =====================================================
@echo Test cases with errors now follow.
@echo The error messages from the two linkers should be similar.
pause

@echo ===== lmod5, lmod6 ==================================
linv1m lmod5 lmod6
@echo -----
%1 linv1 lmod5 lmod6
if errorlevel 1 goto t56
@echo ERROR: linv1 should return a bad return code on lmod5, lmod6
:t56
@echo =====================================================
pause

@echo ===== lmod7, lmod8, lmod9 ===========================
linv1m lmod7 lmod8 lmod9
@echo -----
%1 linv1 lmod7 lmod8 lmod9
if errorlevel 1 goto t789
@echo ERROR: linv1 should return a bad return code on lmod7, lmod8, lmod9
:t789
@echo =====================================================
pause

@echo ===== lmod10, lmod11 ================================
linv1m lmod10 lmod11
@echo -----
%1 linv1 lmod10 lmod11
if errorlevel 1 goto t1011
@echo ERROR: linv1 should return a bad return code on lmod10, lmod11
:t1011
@echo =====================================================
pause

@echo ===== lmod12, lmod13 ================================
linv1m lmod12 lmod13
@echo -----
%1 linv1 lmod12 lmod13
if errorlevel 1 goto t1213
@echo ERROR: linv1 should return a bad return code on lmod12 lmod13
:t1213
@echo =====================================================
pause

@echo ===== lmod14, lmod15 ================================
linv1m lmod14 lmod15
@echo -----
%1 linv1 lmod14 lmod15
if errorlevel 1 goto t1415
@echo ERROR: linv1 should return a bad return code on lmod14, lmod15
:t1415
@echo =====================================================
pause

@echo ===== lmod16 ========================================
linv1m lmod16
@echo -----
%1 linv1 lmod16
if errorlevel 1 goto t16
@echo ERROR: linv1 should return a bad return code on lmod16
:t16
@echo =====================================================
pause

@echo ===== lmod17 ========================================
linv1m lmod17
@echo -----
%1 linv1 lmod17
if errorlevel 1 goto t17
@echo ERROR: linv1 should return a bad return code on lmod17
:t17
@echo =====================================================
pause

@echo ===== lmod18 ========================================
linv1m lmod18
@echo -----
%1 linv1 lmod18
if errorlevel 1 goto t18
@echo ERROR: linv1 should return a bad return code on lmod18
:t18
@echo =====================================================
pause

@echo ===== no input file specified on the command line ===
linv1m
@echo -----
%1 linv1
if errorlevel 1 goto t19
@echo ERROR: linv1 should return a bad return code
:t19
@echo =====================================================
pause

@echo ===== non-existent input file =======================
if exist garbage.ggg del garbage.ggg
linv1m garbage.ggg
@echo -----
%1 linv1 garbage.ggg
if errorlevel 1 goto t20
@echo ERROR: linv1 should return a bad return code
:t20
@echo =======================================================
pause

goto done

:badtc1
@echo Error: bad test case--check lmod1.mas
goto done
:badtc2
@echo Error: bad test case--check lmod2.mas
goto done
:badtc3
@echo Error: bad test case--check lmod3.mas
goto done
:badtc4
@echo Error: bad test case--check lmod4.mas
goto done
:badtc5
@echo Error: bad test case--check lmod5.mas
goto done
:badtc6
@echo Error: bad test case--check lmod6.mas
goto done
:badtc7
@echo Error: bad test case--check lmod7.mas
goto done
:badtc8
@echo Error: bad test case--check lmod8.mas
goto done
:badtc9
@echo Error: bad test case--check lmod9.mas
goto done
:badtc10
@echo Error: bad test case--check lmod0.mas
goto done
:badtc11
@echo Error: bad test case--check lmod11.mas
goto done
:badtc12
@echo Error: bad test case--check lmod12.mas
goto done
:badtc13
@echo Error: bad test case--check lmod13.mas
goto done
:badtc16
@echo Error: bad test case--check lmod16.mas
goto done
:badtc17
@echo Error: bad test case--check lmod17.mas
goto done
:badtc18
@echo Error: bad test case--check lmod18.mas
goto done

:badtc
@echo ERROR: linv1m failure--check linv1m and test cases
goto done

:missingsvc
@echo ERROR: Student version of linv1 (linv1.exe) not found
goto done
:missingsvj
@echo ERROR: Student version of linv1 (linv1.class) not found
goto done
:missingmv
@echo ERROR: Model version of linv1 (linv1m.exe) not found
goto done
:done
if exist x.y del x.y
if exist savemac del savemac
if exist lmod1.mob  del lmod1.mob
if exist lmod2.mob  del lmod2.mob
if exist lmod3.mob  del lmod3.mob
if exist lmod4.mob  del lmod4.mob
if exist lmod5.mob  del lmod5.mob
if exist lmod6.mob  del lmod6.mob
if exist lmod7.mob  del lmod7.mob
if exist lmod8.mob  del lmod8.mob
if exist lmod9.mob  del lmod9.mob
if exist lmod10.mob del lmod10.mob
if exist lmod11.mob del lmod11.mob
if exist lmod12.mob del lmod12.mob
if exist lmod13.mob del lmod13.mob
if exist lmod16.mob del lmod16.mob
if exist lmod17.mob del lmod17.mob
if exist lmod18.mob del lmod18.mob
if exist lmod1.mac  del lmod1.mac
if exist lmod2.mac  del lmod2.mac
if exist lmod3.mac  del lmod3.mac
if exist lmod4.mac  del lmod4.mac
if exist lmod5.mac  del lmod5.mac
if exist lmod6.mac  del lmod6.mac
if exist lmod7.mac  del lmod7.mac
if exist lmod8.mac  del lmod8.mac
if exist lmod9.mac  del lmod9.mac
if exist lmod10.mac del lmod10.mac
if exist lmod11.mac del lmod11.mac
if exist lmod12.mac del lmod12.mac
if exist lmod13.mac del lmod13.mac
if exist lmod16.mac del lmod16.mac
if exist lmod17.mac del lmod17.mac
if exist lmod18.mac del lmod18.mac
