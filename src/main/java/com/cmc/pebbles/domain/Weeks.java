package com.cmc.pebbles.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@DynamicInsert
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Weeks {
//    @Column(columnDefinition = "boolean default false")
    //@ColumnDefault("0")
    private Boolean Mon = false;
    private Boolean Tue = false;
    private Boolean Wed = false;
    private Boolean Thu = false;
    private Boolean Fri = false;
    private Boolean Sat = false;
    private Boolean Sun = false;
}
