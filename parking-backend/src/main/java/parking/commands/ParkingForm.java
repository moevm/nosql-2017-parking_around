package parking.commands;

/**
 * Created by Stanislav on 06.12.2017.
 */
public class ParkingForm {
    private float destinationLongitude;
    private float destinationLatitude;
    private float Rmax;
    private int N;
    private String answer = " ";

    public float getDestinationLongitude() {
        return destinationLongitude;
    }

    public void setDestinationLongitude(float destinationLongitude) {
        this.destinationLongitude = destinationLongitude;
    }

    public float getDestinationLatitude() {
        return destinationLatitude;
    }

    public void setDestinationLatitude(float destinationLatitude) {
        this.destinationLatitude = destinationLatitude;
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
