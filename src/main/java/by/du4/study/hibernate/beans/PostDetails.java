package by.du4.study.hibernate.beans;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "PostDetails")
@Table(name = "post_details")
public class PostDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "created_by")
    private String createdBy;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Post post;

    public PostDetails() {}

    public PostDetails(String createdBy) {
        createdOn = new Date();
        this.createdBy = createdBy;
    }

    //region Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
    //endregion


    @Override
    public String toString() {
        return "PostDetails{" +
                "id=" + id +
                ", createdOn=" + createdOn +
                ", createdBy='" + createdBy +
                '}';
    }
}
