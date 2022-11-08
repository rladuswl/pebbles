package com.cmc.pebbles.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Weeks {
    private Boolean Mon;
    private Boolean Tue;
    private Boolean Wed;
    private Boolean Thu;
    private Boolean Fri;
    private Boolean Sat;
    private Boolean Sun;
}
