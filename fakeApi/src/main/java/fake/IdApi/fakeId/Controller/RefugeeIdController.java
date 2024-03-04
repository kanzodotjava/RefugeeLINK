package fake.IdApi.fakeId.Controller;

import fake.IdApi.fakeId.Models.RefugeeId;
import fake.IdApi.fakeId.Service.RefugeeIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ids")
public class RefugeeIdController{

        private final RefugeeIdService refugeeIdService;

        @Autowired
        public RefugeeIdController(RefugeeIdService refugeeIdService) {
            this.refugeeIdService = refugeeIdService;
        }

        @GetMapping("/{id}")
        public RefugeeId getID(@PathVariable int id) {
            return refugeeIdService.getID(id);
        }

        @PutMapping("/{id}")
        public RefugeeId putID(@PathVariable int id) {
            return refugeeIdService.putID(new RefugeeId(id));
        }

        @DeleteMapping("/{id}")
        public void deleteID(@PathVariable int id) {
            refugeeIdService.deleteID(id);
        }
    }