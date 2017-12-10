package parking.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import parking.commands.DistanceForm;
import parking.domain.Node;

/**
 * Created by Stanislav on 14.11.2017.
 */
@Component
public class NodeToDistanceForm implements Converter<Node, DistanceForm> {
    @Override
    public DistanceForm convert(Node node){
       DistanceForm distanceForm = new DistanceForm();
       distanceForm.setIdFrom(node.getId());
       distanceForm.setNameFrom(node.getName());
       return distanceForm;
    }
}