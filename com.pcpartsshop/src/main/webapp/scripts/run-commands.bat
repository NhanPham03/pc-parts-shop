@echo off
setlocal enabledelayedexpansion

set mysql_path=D:\MySQL\MySQL Server 8.1\bin\mysql.exe
set database_name=pc_parts_shop
set username=root
set password=12345

for /f "tokens=*" %%a in (product-insert.txt) do (
    "%mysql_path%" -u %username% -p%password% %database_name% -e "%%a"
)

echo All insert commands executed successfully!
pause
