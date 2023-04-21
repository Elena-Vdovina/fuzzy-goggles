# fuzzy-goggles

# Проект "Budget tracker"

## Общие сведения

Проект предназначен для ведения личных финансов. Позволяет вводить и хранить данные о движении
денежных средств, просматривать всю таблицу данных, добавлять, изменять, удалять данные,
просматривать отдельно доходы и расходы, просматривать данные по каждой категории за текущий месяц
(по умолчанию записи сортируются по дате (затем по категории по алфавиту, при совпадении - по сумме
по возрастанию)).
Также можно посмотреть анализ за текущий месяц.

Программа позволяет создавать и хранить и использовать несколько файлов бюджетов.

Точкой входа в программу является класс ***Main***.

Проект состоит из следующих классов:

- ***Main*** - точка входа
- ***MenuMethods*** - класс содержит методы для работы основного и вложенных меню.
- ***FileMethods*** - класс содержит методы для работы с файлами данных.
- ***Record*** - класс описывает объект типа Record и содержит метода для работы с объектом
- ***RecordMethods*** - класс содержит методы для работы с таблицами данных типа Record
- ***RecordDateCategoryAmountComparator*** - класс содержит компаратор для сортировки записей
- ***BudgetMethods*** - класс содержит методы для построения диаграмм
- ***DateMethods*** - класс содержит методы для работы с датами
- ***Guide*** - класс содержит методы для работы со справочниками доходов и расходов.
- ***Colors*** - вспомогательный класс, содержит константы для работы с цветом.
- ***RecordTests*** - тесты класса Record для некоторых значений и для компаратора.

## Вход в программу

Выводится меню, состоящее из списка файлов в директории по умолчанию; последний пункт меню -
команда для ввода пути к файлу.

- если выбран файл из списка - открывается основное меню.

- если выбран последний пункт - переход ко вводу имени файла.

## Ввод имени файла

Для входа в программу пользователь указывает полный путь к файлу либо относительно текущей
директории.

- если указанный файл существует, является текстовым - программа переходит в основное меню.


- если указанный файл существует, но не является текстовым - программа создает новый файл с
  указанным именем и расширением .txt, предлагает внести данные. После введения хотя бы одной
  записи программа переходит в основное меню.


- если указанный файл не существует - программа создает новый файл и предлагает внести данные. После
  введения хотя бы одной записи программа переходит в основное меню.

## Основное меню

### Выбрать другой файл

При выборе пункта 1 основного меню программа запрашивает имя другого файла.

- если указанный файл существует, является текстовым - программа остается в основном меню.


- если указанный файл существует, но не является текстовым или - программа выдает сообщение об этом
  и возвращается в основное меню без смены файла.


- если указанный файл не существует - программа предлагает:
    - создать новый файл
    - создать новый список данных в текущем файле
    - вернуться в основное меню без смены пользователя

  В зависимости от выбора пользователя программа создает новый файл, новый список в текущем файле
  или возвращается в основное меню без смены файла.

### Создать новый файл

При выборе пункта 2 основного меню программа запрашивает имя нового файла. Так как новый файл не
существует программа, предлагает:

- создать новый файл
- создать новый список данных в текущем файле
- вернуться в основное меню без смены пользователя

В зависимости от выбора пользователя программа создает новый файл, новый список в текущем файле
или возвращается в основное меню без смены файла.

### Работать с данными

При выборе пункта 3 основного меню программа переходит в подменю работы с таблицей данных.

### Работать со справочниками

При выборе пункта 4 основного меню программа переходит в подменю работы со справочниками.

### Выход

При выборе пункта 5 основного меню программа завершает свою работу.

## Работа с основной таблицей данных

При входе в подменю работы с таблицей данных происходит чтение данных из файла.

### Просмотр данных

При выборе пункта 1 подменю работы с таблицей данных происходит вывод на экран весь
несортированный список данных. Все записи пронумерованы для того чтобы пользователь мог узнать
номер записи.

### Добавить запись

При выборе пункта 2 подменю работы с таблицей данных программа запрашивает данные, формирует запись
и записывает ее в список и весь список в файл пользователя.

### Изменить запись

При выборе пункта 3 подменю работы с таблицей данных программа запрашивает номер записи в списке
(можно узнать при выводе всего списка на экран). Программа выводит существующие данные каждого поля
на экран и предлагает ввести новые данные, формирует запись и перезаписывает ее в список под тем же
номером и весь список в файл пользователя.

### Удалить запись

При выборе пункта 4 подменю работы с таблицей данных программа запрашивает номер записи в списке
(можно узнать при выводе всего списка на экран). Просит подтвердить пользователя свой выбор и
удаляет ее в случае подтверждения и перезаписывает весь список в файл пользователя.

### Только расходы

При выборе пункта 5 подменю работы с таблицей данных программа выводит на экран только расходы за
текущий месяц и их сумму. По умолчанию сортировка по дате (при совпадении даты - по категории,
при совпадении категории - по сумме). Программа предлагает просмотреть данные по другим месяцам с
начала года по текущий месяц.

### Только доходы

При выборе пункта 6 подменю работы с таблицей данных программа выводит на экран только доходы за
текущий месяц и их сумму. По умолчанию сортировка по дате (при совпадении даты - по категории,
при совпадении категории - по сумме). Программа предлагает просмотреть данные по другим месяцам с
начала года по текущий месяц.

### Структура доходов/расходов

При выборе пункта 7 подменю работы с таблицей данных программа выводит на экран диаграмму расходов
и доходов по категориям. Программа предлагает просмотреть данные по другим месяцам с
начала года по текущий месяц.

### Выборка по категории

При выборе пункта 8 подменю работы с таблицей данных программа предлагает пользователю выбрать
категорию и выводит на экран выборку из основной таблицы по этой категории за текущий месяц. Если по
этой категории в текущем месяце не было записей программа сообщит об этом и вернется в подменю
работы с таблицей данных. Программа предлагает просмотреть данные по другим месяцам с
начала года по текущий месяц.

### Вернуться в основное меню

При выборе пункта 9 подменю работы с таблицей данных программа возвращается в основное меню.

## Работа со справочниками

### Просмотр категорий доходов

При выборе пункта 1 подменю работы со справочниками программа выводит на экран список категорий
доходов.

### Добавить категорию доходов

При выборе пункта 2 подменю работы со справочниками программа позволяет пользователю дополнить
справочник доходов.

### Просмотр категорий расходов

При выборе пункта 3 подменю работы со справочниками программа выводит на экран список категорий
расходов.

### Добавить категорию расходов

При выборе пункта 4 подменю работы со справочниками программа позволяет пользователю дополнить
справочник расходов.

### Вернуться в основное меню

При выборе пункта 5 подменю работы со справочниками данных программа возвращается в основное меню.
############



