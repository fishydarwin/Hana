package me.darwj.hana.string;

import org.jetbrains.annotations.NotNull;

public class Strings {

    /**
     * Merge a string array into one long string, separated by the separator string.
     * Specify "from" as an index, and "to" as length.
     *
     * @param array The array to merge
     * @param separator The separator
     * @param from From where to start merging
     * @param to The limit to merging
     * @return The merged string
     */
    public static @NotNull String merge(String[] array, String separator, int from, int to) {
        StringBuilder build = new StringBuilder();
        for (int i = from; i < to; i++) {
            if (i == to - 1) { build.append(array[i]); }
            else { build.append(array[i]).append(separator); }
        }
        return build.toString();
    }

}
