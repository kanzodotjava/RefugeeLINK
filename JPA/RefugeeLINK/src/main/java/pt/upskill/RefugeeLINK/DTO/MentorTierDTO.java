package pt.upskill.RefugeeLINK.DTO;

import java.time.LocalDate;
import java.util.Date;

public class MentorTierDTO {

        private String username;
        private LocalDate dateOfCreation;

        public String getUsername() {
            return username;
        }


        public LocalDate getDateOfCreation() {
            return dateOfCreation;
        }

        public void setUsername(String username) {
                this.username = username;
            }

        public void setDateOfCreation(LocalDate dateOfCreation) {
            this.dateOfCreation = dateOfCreation;
        }
}
