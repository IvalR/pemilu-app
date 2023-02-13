package barrans.devel.api.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.constraint.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "menu_access")
public class MenuAccess extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "menuAccessSequence",
            sequenceName = "menu_access_id_sequence",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "menuAccessSequence"
    )
    @Column(name = "id", nullable = false)
    private Long id;

    private Boolean createable;

    private Boolean updateable;

    private Boolean viewable;

    private Boolean deleteable;

    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role_id;

    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "menu_id")
    private MenuMaster menu_id;

    @NotNull
    private Integer Status;

    @CreationTimestamp
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "updated_by", nullable = false)
    private User updatedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getCreateable() {
        return createable;
    }

    public void setCreateable(Boolean createable) {
        this.createable = createable;
    }

    public Boolean getUpdateable() {
        return updateable;
    }

    public void setUpdateable(Boolean updateable) {
        this.updateable = updateable;
    }

    public Boolean getViewable() {
        return viewable;
    }

    public void setViewable(Boolean viewable) {
        this.viewable = viewable;
    }

    public Boolean getDeleteable() {
        return deleteable;
    }

    public void setDeleteable(Boolean deleteable) {
        this.deleteable = deleteable;
    }

    public Role getRole_id() {
        return role_id;
    }

    public void setRole_id(Role role_id) {
        this.role_id = role_id;
    }

    public MenuMaster getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(MenuMaster menu_id) {
        this.menu_id = menu_id;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
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
}
