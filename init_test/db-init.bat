@echo off
echo.
echo [信息] 重建您的数据库并导入初始数据。
echo.
pause
echo.
echo [信息] 此操作会清空您的数据表和数据，并恢复初始状态。
echo.
echo [信息] 确认继续吗？否则请关闭窗口。
echo.
pause
echo.
echo [信息] 您真的确认继续吗？否则请关闭窗口。
echo.
pause
echo.

cd %~dp0
cd ..
cd C:\Users\Administrator\Desktop\init_test

call mvn clean antrun:run -Pinit-db -e

cd db
pause