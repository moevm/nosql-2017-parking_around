package parking.services.ParkingAlg;

import parking.commands.ParkingForm;
import parking.domain.Node;

import java.util.ArrayList;

/**
 * Created by Stanislav on 06.12.2017.
 */
public interface ParkingAlgService {

    public ArrayList<Node> work(ParkingForm parkingForm);
}
