import java.util.ArrayList;
import java.util.List;

class MyCalendar {

    private List<int[]> calendar;

    public MyCalendar() {
        calendar = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        for (int[] event : calendar) {
            int s = event[0];
            int e = event[1];

            if (start < e && end > s) {
                return false;
            }
        }

        calendar.add(new int[]{start, end});
        return true;
    }
}
