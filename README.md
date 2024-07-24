# WITH3 Project
Этот проект является Java-приложением, использующим Spring Boot и Maven. Ниже приведены шаги для настройки и развертывания проекта локально.  

### Требования
- Java 17
- Spring Boot 3+
- Maven 3.6.0+
- База данных PostgreSQL

### Инструкции по настройке
#### 1. Скомпилируйте проект  
Используйте Maven для компиляции проекта. Выполните следующую команду в корневом каталоге проекта:
<pre>mvn compile </pre>

#### 2. Создайте файл liquibase.properties  
Создайте файл с именем liquibase.properties в каталоге src/main/resources со следующим содержимым, заменив заполнители на ваши реальные учетные данные базы данных:
<pre>url=jdbc:postgresql://localhost:5432/your_database
username=your_username
password=your_password
changeLogFile=classpath:db/changelog/db.changelog-master.xml </pre>

#### 3. Выполните обновление Liquibase  
Выполните цель обновления Liquibase для применения изменений в базе данных:
<pre>mvn liquibase:update </pre>
После выполнения этих шагов проект должен быть настроен и готов к запуску.

#### 4. Change the codestyle
Download this .xml-file:
https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml

Then in Intellij, go Under Settings -> Editor -> Code Style.  
There in Scheme settings (settings icon on right side) -> import schemes-> intellij idea code style xml.
Select the xml downloaded.

Then in Scheme dropdown select the GoogleStyle IDE (newly added style).
Click on apply and close.