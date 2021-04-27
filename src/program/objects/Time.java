package program.objects;

public class Time {
    public static int addTime(int stime, int time) {
        int temp = stime % 100;
        stime -= temp;

        temp += time;
        stime += (temp / 60) * 100;
        stime += temp % 60;

        return stime;
    }

    public static int subTime(int time1, int time2) {

        int  min1 = time1 % 100;
        time1 -= min1;
        int min2 = time2 % 100;
        time2 -= min2;

        int minDif = min1 - min2;
        if (minDif < 0) {
            minDif = 60 + minDif;
            time1 -= 100;
        }

        int hDif = time1 - time2;
        hDif = hDif < 0 ? 2400 - hDif : hDif;

        return hDif + minDif;
    }


    public static int toMinutes(int time){
        int  min = time % 100;

        time = (time / 100) * 60;
        time += min;

        return time;
    }

}
