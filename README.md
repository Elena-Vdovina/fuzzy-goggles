# fuzzy-goggles

# Проект "Budget tracker"

### Общие сведения

Проект предназначен для ведения личных финансов. Позволяет вводить и хранить данные о движении
денежных средств, просматривать, просматривать отдельно доходы и расходы изменять, удалять данные.
Также можно смотреть анализ за месяц________________.
Данные каждого пользователя хранятся в отдельных файлах.

Проект состоит из следующих классов:

- Main - точка входа
- MenuMethods - класс содержит методы для работы основного и вложенных меню.
- FileMethods - класс содержит методы для работы с файлами данных.
- Record - класс описывает объект типа Record и содержит метода для работы с объектом
- RecordMethods - класс содержит методы для работы с таблицами данных типа Record
- Guide - класс содержит методы для работы со справочниками доходов и расходов.
- Colors - вспомогательный класс, содержит константы для работы с цветом.
- а также тесты.

### Файл пользователя

#### Вход в программу

Для входа в программу пользователь указывает полное имя файла.

- если указанный файл существует, является текстовым - программа записывает имя файла в переменную
  pathToFile_ типа String является полем класса MenuMethods и переходит в основной цикл работы.


- если указанный файл существует, но не является текстовым - программа создает новый файл с
  указанным именем и расширением .txt и предлагает внести данные. Для
  продолжения необходимо внести хотя бы одну запись. Программа записывает имя файла и переходит в
  основной цикл работы.


- если указанный файл не существует - программа создает новый файл и предлагает внести данные. Для
  продолжения необходимо внести хотя бы одну запись. Программа записывает имя файла и переходит в
  основной цикл работы.

#### Смена пользователя

Если в процессе работы необходимо сменить пользователя, необходимо использовать пункт 1 основного
меню.

- если указанный файл существует, является текстовым - программа перезаписывает имя файла в
  переменную
  pathToFile_ и переходит в основной цикл работы.


- если указанный файл существует, но не является текстовым или - программа выдает сообщение об этом
  и переходит в основной цикл работы без смены файла.


- если указанный файл не существует - программа предлагает
    - создает новый файл
    - создать новый список данных в текущем файле
    - вернутся в основное меню без смены пользователя

  В зависимости от выбора пользователя программа создает новый файл, новый список в текущем файле
  или возвращается в основное меню без смены файла.

### Работа с основной таблицей данных

### Работа со справочниками