package com.ssafy.bts.Domain.Weekly;

import com.ssafy.bts.Domain.User.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Weekly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int weekId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private int weekYear;

    @Column(nullable = false)
    private int weekMonth;

    @Column(nullable = false)
    private int weekDate;

    @Column(nullable = false, columnDefinition = "TIME")
    @Convert(converter = Jsr310JpaConverters.LocalTimeConverter.class)
    private LocalTime weekStartTime;

    @Column(nullable = false, columnDefinition = "TIME")
    @Convert(converter = Jsr310JpaConverters.LocalTimeConverter.class)
    private LocalTime weekEndTime;

    @Column(nullable = false, columnDefinition = "text")
    private String weekContent;
}
