package fake.IdApi.fakeId.Controller;

import fake.IdApi.fakeId.Models.RefugeeId;
import fake.IdApi.fakeId.Service.RefugeeIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ids")
public class RefugeeIdController{

        private final RefugeeIdService refugeeIdService;

        @Autowired
        public RefugeeIdController(RefugeeIdService refugeeIdService) {
            this.refugeeIdService = refugeeIdService;
        }

         @GetMapping("/all")
         public List<RefugeeId> getAllIDs() {
            return refugeeIdService.getAllIDs();
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

        @GetMapping("/exists/{id}")
        public boolean idExists(@PathVariable int id) {
        return refugeeIdService.idExists(id);
    }

    }