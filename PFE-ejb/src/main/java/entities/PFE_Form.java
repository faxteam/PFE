package entities;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: PFE_Form
 *
 */
@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "form_id")
public class PFE_Form extends Form {

    private static final long serialVersionUID = 1L;

    private String description;

    private String functionalities;

    @ElementCollection
    private List<String> keyWords;

    private String problematic;

    private String title;

    private Boolean accepted;

    private Boolean archieved;

    @Temporal(TemporalType.DATE)
    private Date defenseDate;
    //pq jarrive pas a faire un clean install? att att touche rien 
    //take the wheel ok je reve ou il nest pas entrain de le lancer? att je regarder
    // je te pose une question. as tu dans ta base de donnnee une table qui porte le meme nom
    @OneToOne
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private Employee supervisor;

    @OneToOne
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private Employee reporter;

    @OneToOne
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private Employee president;

    @Temporal(TemporalType.DATE)
    private Date createdOn;

    @Temporal(TemporalType.DATE)
    private Date modifiedOn;

    @OneToOne
    @JoinColumn(name = "evaluation_id", insertable = false, updatable = false)
    private Evaluation evaluation;

    public PFE_Form() {
        super();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFunctionalities() {
        return functionalities;
    }

    public void setFunctionalities(String functionalities) {
        this.functionalities = functionalities;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }

    public String getProblematic() {
        return problematic;
    }

    public void setProblematic(String problematic) {
        this.problematic = problematic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public Boolean isArchieved() {
        return archieved;
    }

    public void setArchieved(Boolean archieved) {
        this.archieved = archieved;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Date getDefenseDate() {
        return defenseDate;
    }

    public void setDefenseDate(Date defenseDate) {
        this.defenseDate = defenseDate;
    }

    public Employee getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
    }

    public Employee getReporter() {
        return reporter;
    }

    public void setReporter(Employee reporter) {
        this.reporter = reporter;
    }

    public Employee getPresident() {
        return president;
    }

    public void setPresident(Employee president) {
        this.president = president;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

}
