package mission.controller;

import mission.data.MissionRepository;
import mission.model.Mission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mission")
public class MissionController {

    @Autowired
    MissionRepository missionRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Mission create(@RequestBody Mission mission){
        System.out.println(mission.toString());

        Mission result = missionRepository.save(mission);
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value="/{missionId}")
    public Mission get(@PathVariable String missionId){

        return missionRepository.findOne(missionId);
    }

    @RequestMapping(method = RequestMethod.GET, value="")
    public List<Mission> getAll(){

        return missionRepository.findAll();
    }


}
