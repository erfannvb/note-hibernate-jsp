package org.example.notehibernatejsp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.notehibernatejsp.base.entity.BaseEntity;

import java.util.Date;

@Entity
@Table(name = "tbl_note")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Note extends BaseEntity<Long> {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    public Note(Long id, String title, String content, Date createdDate) {
        super(id);
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }
}
