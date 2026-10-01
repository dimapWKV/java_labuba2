#!/bin/bash

echo "[1/4] Очистка предыдущей сборки..."
rm -rf out
rm -f lab2.jar
mkdir -p out

echo "[2/4] Компиляция всех Java-файлов из src..."
# find рекурсивно находит все .java файлы во всех подпакетах
javac -encoding UTF-8 -d out $(find src -name "*.java")

if [ $? -ne 0 ]; then
    echo "ОШИБКА: Компиляция не удалась!"
    exit 1
fi

echo "[3/4] Сборка JAR с манифестом..."
jar cfm lab2.jar manifest.mf -C out .

echo "[4/4] Готово!"
echo "=========================================="
echo "Запуск: java -jar lab2.jar"
echo "=========================================="