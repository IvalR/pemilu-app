package barrans.devel.api.model;



import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "user")
public class User extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "userSequence",
            sequenceName = "user_id_sequence",
            allocationSize = 1,
            initialValue = 2
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "userSequence"
    )
    @Column(name = "id", nullable = false)
    public Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email",length = 50,nullable = false)
    private String email;

    @Column(name = "pob",length = 20,nullable = false)
    private String pob;

    @Column(name = "dob",nullable = false)
    private Date dob;

    @OneToOne(targetEntity = Role.class)
    @JoinColumn(name = "role_id")
    private Role roleId;

    @Column(name = "status")
    public Integer status;

    @Column(name = "password", length = 100, nullable = false)
    public String password;

    @CreationTimestamp
    @Column(name = "created_date", nullable = false)
    public LocalDateTime createdDate;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    @UpdateTimestamp
    @Column(name = "updated_date")
    public LocalDateTime updatedDate;

    public User() {
        super();
    }

    public static Boolean isEmptyEmail(String email){
        return User.find("email = ?1", email).firstResultOptional().isEmpty();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPob() {
        return pob;
    }

    public void setPob(String pob) {
        this.pob = pob;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getStatus() {
        return status;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }


    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean comparePassword(String password) {
        return this.password.equals(password);
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }
}
