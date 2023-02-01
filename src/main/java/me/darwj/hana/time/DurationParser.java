package me.darwj.hana.time;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public class DurationParser {

    /**
     * Converts a Duration to years, months, days, hours, minutes, and seconds.
     * It is assumed that one month is 30 days, and 1 year is 12 months.
     * @param duration The duration to parse into units
     * @return An array like so:<br>[0] years<br>[1] months<br>[2] days<br>[3] hours<br>[4] minutes<br>[5] seconds<br>
     */
    @Contract(pure = true)
    public static long @NotNull [] parseUnits(Duration duration) {

        long years = duration.getSeconds() / 31556926;
        long months = duration.getSeconds() % 31556926 / 2629743;
        long days = duration.getSeconds() % 31556926 % 2629743 / 86400;
        long hours = duration.getSeconds() % 31556926 % 2629743 % 86400 / 3600;
        long minutes = duration.getSeconds() % 31556926 % 2629743 % 86400 % 3600 / 60;
        long seconds = duration.getSeconds() % 31556926 % 2629743 % 86400 % 3600 % 60;

        return new long[] {
                Math.abs(years),
                Math.abs(months),
                Math.abs(days),
                Math.abs(hours),
                Math.abs(minutes),
                Math.abs(seconds)
        };

    }

}
