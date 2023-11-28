:: Cambiar la ruta por: C:/compilador/ejecucion.bat
cd C:\Users\gonza\compilador
g++ -o programa codigoObjeto.cpp
gcc -S codigoObjeto.cpp
:: Descomentar la siguiente linea para eliminar el cpp
:: del /f /a codigoObjeto.cpp
cls
programa.exe
