package model.usertype.prototype;

import model.comparator.Comparator;
import model.comparator.DateTimeComparator;
import model.usertype.type.DateTimeClass;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.stream.Collectors;

public class DateTimeType implements ProtoType {

    @Override
    public String typeName() {
        return "DateTime";
    }

    @Override
    public Object create() {

        //генерация случайных чисел
        // дата [1; 31]
        int minDay = 1, maxDay = 31;
        // месяц [1; 12]
        int minMonth = 1, maxMonth = 12;
        // год [988; 2048]
        int minYear = 988, maxYear = 2048;
        // часы [0; 23]
        int minHour = 0, maxHour = 23;
        // минуты, секунды [0 ; 59]
        int minTime = 0, maxTime = 59;

        Random rand = new Random();
        int day = rand.nextInt(maxDay - minDay) + 1;
        int month = rand.nextInt(maxMonth - minMonth) + 1;
        int year = rand.nextInt(maxYear - minYear) + 1;
        int hour = rand.nextInt(maxHour - minHour);
        int minute = rand.nextInt(maxTime - minTime);
        int second = rand.nextInt(maxTime - minTime);
        DateTimeClass dateTimeValue;
        //Если рандом нам сгенерировал дату, которой быть не может, то генерируем со статичными значениями
        try {
        dateTimeValue = new DateTimeClass(day, month, year, hour, minute, second);
        }
        catch(Exception ex) {
            System.out.println("Bad date, generating using a static values");
            dateTimeValue = new DateTimeClass();
        }
        return dateTimeValue;
    }

    @Override
    public Object clone(Object obj) {
        DateTimeClass copyDateTime;
        try {
            copyDateTime = new DateTimeClass(((DateTimeClass)obj).getDay(),
                    ((DateTimeClass)obj).getHour(), ((DateTimeClass)obj).getYear(),
                    ((DateTimeClass)obj).getHour(), ((DateTimeClass)obj).getMinute(),
                    ((DateTimeClass)obj).getSecond());
        }
        catch(Exception ex) {
            copyDateTime = new DateTimeClass();
        }
        return copyDateTime;
    }

    @Override
    public Object readValue(InputStream inputStream) {
        return parseValue(new BufferedReader(new InputStreamReader(inputStream))
                .lines().collect(Collectors.joining("\n")));
    }

    @Override
    public Object parseValue(String someString) {
        String[] words = someString.split(" ");
        String[] dateStr = words[0].split("/");
        String[] timeStr = words[1].split(":");
        Integer[] dateInt = new Integer[3];
        Integer[] timeInt = new Integer[3];

        for (int i = 0; i < 3; i++) {
            dateInt[i] = Integer.parseInt(dateStr[i]);
            timeInt[i] = Integer.parseInt(timeStr[i]);
        }

        DateTimeClass dateTimeValue;
        try {
            dateTimeValue = new DateTimeClass(dateInt[0], dateInt[1], dateInt[2], timeInt[0], timeInt[1], timeInt[2]);
        }
        catch(Exception ex) {
            System.out.println("Bad date, generating using a static values");
            dateTimeValue = new DateTimeClass();
        }

        return dateTimeValue;
    }

    @Override
    public Comparator getTypeComparator() {
        Comparator comparator = new DateTimeComparator();
        return comparator;
    }
}
