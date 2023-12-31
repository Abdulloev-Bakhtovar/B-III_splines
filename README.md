# Spline Drawing App

Описание

Эта программа - приложение для рисования сплайнов, созданное с использованием Java Swing. Пользователи могут добавлять точки на холст, перемещать их и наблюдать, как сплайн динамически обновляется, соединяя точки.
Особенности

    Добавление и перемещение точек: Пользователи могут кликать по холсту, чтобы добавить новые точки, и перетаскивать их для изменения формы сплайна.
    Отображение точек и линий: Через чекбокс можно переключать отображение дополнительных линий и точек.
    Очистка рисунка: Кнопка для удаления всех точек и очистки холста.

Компоненты

    SplineDrawingApp: Основной класс приложения, создающий и управляющий графическим интерфейсом.
    DrawingPanel: Панель для рисования, где происходит отрисовка сплайнов и обработка событий мыши.

Работа с приложением

При запуске приложения открывается окно с холстом. Пользователи могут кликать по холсту для добавления точек, которые будут соединены сплайном. Точки можно перемещать, изменяя форму сплайна. Используйте чекбокс для отображения/скрытия точек и соединительных линий, и кнопку "Clear" для очистки холста.
Использование

    Запустите приложение, выполните метод main в классе SplineDrawingApp.
    Кликните по холсту, чтобы добавить точки.
    Перетаскивайте точки для изменения формы сплайна.
    Используйте чекбокс и кнопку "Clear" для управления отображением и очисткой холста.

Требования

    Java SE Development Kit (JDK)
    Swing (входит в состав JDK)

Как запустить

    Установите JDK, если еще не установлено.
    Скомпилируйте код: javac SplineDrawingApp.java.
    Запустите приложение: java SplineDrawingApp.

Лицензия

Этот проект распространяется под лицензией MIT, которая позволяет свободное использование, изменение и распространение программного обеспечения.
