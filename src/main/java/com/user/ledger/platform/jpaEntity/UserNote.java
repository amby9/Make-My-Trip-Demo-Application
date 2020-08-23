package com.user.ledger.platform.jpaEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="user_note")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserNote {

    @Id
    private String userId;

    private String note;
}
