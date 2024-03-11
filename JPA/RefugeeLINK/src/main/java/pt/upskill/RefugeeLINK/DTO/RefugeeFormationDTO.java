package pt.upskill.RefugeeLINK.DTO;

public class RefugeeFormationDTO {
    private Long id;
    private Long refugeeId;
    private String refugeeName;
    private Long formationId;
    private String formationName;
    private boolean isApproved;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRefugeeId() {
        return refugeeId;
    }

    public void setRefugeeId(Long refugeeId) {
        this.refugeeId = refugeeId;
    }

    public String getRefugeeName() {
        return refugeeName;
    }

    public void setRefugeeName(String refugeeName) {
        this.refugeeName = refugeeName;
    }

    public Long getFormationId() {
        return formationId;
    }

    public void setFormationId(Long formationId) {
        this.formationId = formationId;
    }

    public String getFormationName() {
        return formationName;
    }

    public void setFormationName(String formationName) {
        this.formationName = formationName;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }
}
