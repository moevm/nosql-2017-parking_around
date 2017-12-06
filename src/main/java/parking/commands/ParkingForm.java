package parking.commands;

/**
 * Created by Stanislav on 06.12.2017.
 */
public class ParkingForm {
    private float DistinationLongitude;
    private float DistinationLatitude;
    private float Rmax;
    private int N;
    private String answer = " ";

    public float getDistinationLongitude() {
        return DistinationLongitude;
    }

    public void setDistinationLongitude(float distinationLongitude) {
        DistinationLongitude = distinationLongitude;
    }

    public float getDistinationLatitude() {
        return DistinationLatitude;
    }

    public void setDistinationLatitude(float distinationLatitude) {
        DistinationLatitude = distinationLatitude;
    }

    public float getRmax() {
        return Rmax;
    }

    public void setRmax(float rmax) {
        Rmax = rmax;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }
}
