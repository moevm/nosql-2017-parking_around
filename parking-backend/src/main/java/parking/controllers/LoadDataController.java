package parking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import parking.services.DataService;

@RestController
@RequestMapping("/load")
public class LoadDataController {

    private DataService dataService;

    @Autowired
    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public void buildRoute(@RequestParam("latitude") Double latitude,
                           @RequestParam("longitude") Double longitude, @RequestParam("lambda") Double lambda) {
        dataService.mainImport(latitude, longitude, lambda);
    }
}
