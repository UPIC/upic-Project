package com.upic.po;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class IntegralLogId implements Serializable {
    String studentNum;

    String projectNum;
}