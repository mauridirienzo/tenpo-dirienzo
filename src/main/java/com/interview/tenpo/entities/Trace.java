package com.interview.tenpo.entities;

import lombok.*;

import javax.persistence.*;

@ToString
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "https_trace")
public class Trace {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private int responseCode;
    @Column
    private String endpoint;
    @Column
    private String timeStamp;

}
