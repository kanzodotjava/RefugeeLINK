package fake.IdApi.fakeId.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class RefugeeId {

        @Id
        private int id;

        public RefugeeId() {
        }

        public RefugeeId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
