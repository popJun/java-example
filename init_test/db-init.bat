@echo off
echo.
echo [��Ϣ] �ؽ��������ݿⲢ�����ʼ���ݡ�
echo.
pause
echo.
echo [��Ϣ] �˲���������������ݱ�����ݣ����ָ���ʼ״̬��
echo.
echo [��Ϣ] ȷ�ϼ����𣿷�����رմ��ڡ�
echo.
pause
echo.
echo [��Ϣ] �����ȷ�ϼ����𣿷�����رմ��ڡ�
echo.
pause
echo.

cd %~dp0
cd ..
cd C:\Users\Administrator\Desktop\init_test

call mvn clean antrun:run -Pinit-db -e

cd db
pause