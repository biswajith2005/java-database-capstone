@Entity
public class Appointment {
    @ManyToOne
    private Doctor doctor;
    
    @ManyToOne
    private Patient patient;
    
    @Future
    private LocalDateTime appointmentTime;
}
