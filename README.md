## Core Java JVM and SpringBoot Cookbooks and Examples

Demo console app for Student Registration (SpringBoot)

[![Java CI](https://github.com/pandamaroder/ContactRegistry/actions/workflows/github-actions-demo.yml/badge.svg)](https://github.com/pandamaroder/ContactRegistry/actions/workflows/github-actions-demo.yml)
[![codecov](https://codecov.io/gh/pandamaroder/ContactRegistry/graph/badge.svg?token=9KNR2SQ3QI)](https://codecov.io/gh/pandamaroder/ContactRegistry)

[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=pandamaroder_ContactRegistry&metric=bugs)](https://sonarcloud.io/summary/new_code?id=pandamaroder_ContactRegistry)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=pandamaroder_ContactRegistry&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=pandamaroder_ContactRegistry)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=pandamaroder_ContactRegistry&metric=coverage)](https://sonarcloud.io/summary/new_code?id=pandamaroder_ContactRegistry)

### Requirements:

- Java 17+
- Spring Boot 2.4

## Description

Консольное приложение, реализованное с помощью Spring Shell.

Каждая из этих команд будет вызывать соответствующий метод
StudentCommands и, в свою очередь, методы StudentService.
Результаты будут отображаться в консоли в соответствии с логикой,
реализованной в приложении и возвращаемых типах данных.

## Run locally

 после запуска консольного приложения - ввести команды, соответствующие методам StudentCommands,
если вы запустили  приложение и видите приглашение вида student-shell>, 
 вы можете ввести следующие команды:

Для добавления нового студента:

addStudent <Имя> <Фамилия> <Возраст>

Для удаления студента по идентификатору:

deleteStudent <Id>

Для получения списка всех студентов:

getAllStudents

Для удаления всех студентов:

clearAllStudents


