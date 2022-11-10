#language:ru

Функциональность: Перевод

  # пример теста с одним набором параметров
  Сценарий: : Перевод с карты на свою первую карту (позитивный)
    Пусть пользователь вошел на страницу "Личный кабинет" с именем "vasya" и паролем "qwerty123"
    Когда он переводит "5000 руб" с карты номер "5559 0000 0000 0002" на "1" карту на странице "Ваши карты"
      Тогда баланс "1" карты на странице "Ваши карты" должен стать "15000 руб"

  # пример параметризированного теста
  Структура сценария: : Авторизация в личном кабинете (негативный)
    Пусть открыта страница с формой авторизации "http://localhost:9999"
    Когда пользователь пытается авторизоваться с именем <login> и паролем <password>
    И пользователь вводит проверочный код 'из смс' <code>
    Тогда появляется ошибка о неверном коде проверки
    Примеры:
      | login | password  | code  |
      | "vasya" | "qwerty123" | "00000" |
      | "vasya" | "qwerty123" | "11111" |