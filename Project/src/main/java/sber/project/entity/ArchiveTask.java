package sber.project.entity;

import sber.project.enums.Category;
import sber.project.enums.Repitable;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ArchiveTask")
public class ArchiveTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "time")
    private LocalDateTime time;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "rating")
    private int rating;
    @Column(name = "category")
    private Category category;
    @Column(name = "repeatable")
    private Repitable repeatable;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
