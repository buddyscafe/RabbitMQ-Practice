package reward.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reward.data.RewardRepository;
import reward.model.Reward;

import java.util.List;

@RestController
@RequestMapping("/reward")
public class RewardController {

    @Autowired
    RewardRepository rewardRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Reward create(@RequestBody Reward reward){
        System.out.println(reward.toString());

        Reward result = rewardRepository.save(reward);
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value="/{rewardId}")
    public Reward get(@PathVariable String rewardId){
        return rewardRepository.findOne(rewardId);
    }

    @RequestMapping(method = RequestMethod.GET, value="")
    public List<Reward> getAll(){
        return rewardRepository.findAll();
    }


}
