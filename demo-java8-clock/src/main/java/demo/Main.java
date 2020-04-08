package demo;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class Main {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        switch(localDateTime.get(ChronoField.AMPM_OF_DAY)){
            case 0:
                System.out.println("现在是早上");
                break;
            case 1:
                System.out.println("现在是下午");
                break;
        }

        System.out.println(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()));

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(localDateTime.format(dateTimeFormatter));

        LocalDateTime localDateTime1 = LocalDateTime.parse("2019-09-06 09:58:02",dateTimeFormatter);
        System.out.println(ZoneId.systemDefault());
        System.out.println(localDateTime1.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

        System.out.println(localDateTime.plusWeeks(1));
        System.out.println(localDateTime1.minusDays(15));
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getDayOfWeek());
        System.out.println(localDateTime.get(ChronoField.DAY_OF_WEEK));

        Instant now = Instant.now();
        System.out.println(now);

        Instant instant = Instant.ofEpochMilli(1567735082000L);
        System.out.println(instant);

        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Shanghai"));
        System.out.println(localDateTime2);

        Duration duration = Duration.between(localDateTime,localDateTime2);
        System.out.println(duration.toDays());
        System.out.println(duration.toHours());
    }
}
