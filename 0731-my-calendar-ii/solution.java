import java.util.ArrayList;
import java.util.List;

class MyCalendarTwo {

    private List<int[]> bookings;
    private List<int[]> overlaps;

    public MyCalendarTwo() {
        bookings = new ArrayList<>();
        overlaps = new ArrayList<>();
    }

    public boolean book(int start, int end) {

        // If overlaps with an already double-booked interval,
        // it becomes a triple booking.
        for (int[] interval : overlaps) {
            if (start < interval[1] && end > interval[0]) {
                return false;
            }
        }

        for (int[] interval : bookings) {
            if (start < interval[1] && end > interval[0]) {
                overlaps.add(new int[]{
                    Math.max(start, interval[0]),
                    Math.min(end, interval[1])
                });
            }
        }

        bookings.add(new int[]{start, end});
        return true;
    }
}
