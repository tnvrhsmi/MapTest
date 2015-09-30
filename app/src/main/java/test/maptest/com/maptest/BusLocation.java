package test.maptest.com.maptest;

/**
 * Created by tanvihashimee on 29/09/15.
 */
public class BusLocation  {
    private String bpLocName = "";
    private double bpLocLongitude = 0;
    private double bpLocLatitude = 0;
    private String earliestBus= "";
    private String BusCount= "";
    private String timeByCar= "";
    private String score= "";
    private String color= "";

    public String getBpLocName() {
        return bpLocName;
    }

    public void setBpLocName(String bpLocName) {
        this.bpLocName = bpLocName;
    }

    public double getBpLocLatitude() {
        return bpLocLatitude;
    }

    public void setBpLocLatitude(double bpLocLatitude) {
        this.bpLocLatitude = bpLocLatitude;
    }

    public String getEarliestBus() {
        return earliestBus;
    }

    public void setEarliestBus(String earliestBus) {
        this.earliestBus = earliestBus;
    }

    public String getBusCount() {
        return BusCount;
    }

    public void setBusCount(String busCount) {
        BusCount = busCount;
    }

    public String getTimeByCar() {
        return timeByCar;
    }

    public void setTimeByCar(String timeByCar) {
        this.timeByCar = timeByCar;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getBpLocLongitude() {
        return bpLocLongitude;
    }

    public void setBpLocLongitude(double bpLocLongitude) {
        this.bpLocLongitude = bpLocLongitude;
    }
}
