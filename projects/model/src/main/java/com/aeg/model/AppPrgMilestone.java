package com.aeg.model;

import com.aeg.identity.IMSIdentity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "APP_PRG_MILESTONE")
public class AppPrgMilestone implements Serializable {

    @Id
    private IMSIdentity id;



}
