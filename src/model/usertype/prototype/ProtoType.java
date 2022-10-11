package model.usertype.prototype;

import model.comparator.Comparator;

import java.io.InputStreamReader;

public interface ProtoType {
        // Имя типа
        public String typeName();

        // Создание объекта
        public Object create();

        // Клонирование текущего
        public Object clone();

        // Создание и чтения объекта
        public Object readValue(InputStreamReader in);

        // Создает и парсит содержимое из строки
        public Object parseValue(String someString);

        // Возврат компаратора для сравнения
        public Comparator getTypeComparator();
}
