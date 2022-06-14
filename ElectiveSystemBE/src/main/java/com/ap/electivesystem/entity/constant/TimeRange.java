package com.ap.electivesystem.entity.constant;

import java.util.ArrayList;
import java.util.List;

public class TimeRange {

    public static final List<String> TimeRange = new ArrayList<>();

    static {
        TimeRange.add("8:00 - 9:35");
        TimeRange.add("9:50 - 11:25");
        TimeRange.add("13:00 - 14:35");
        TimeRange.add("14:50 - 16:25");
        TimeRange.add("18:00 - 20:35");
    }

    public static boolean legalTimeRange(String time) {
        return TimeRange.contains(time);
    }

}
