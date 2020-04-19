package com.datingapp.matching.data.dto;


import com.datingapp.matching.data.common.EnumUserGender;
import com.datingapp.matching.data.common.EnumUserStatus;
import lombok.*;

import java.io.Serializable;

/**
 * Created on 30.03.2020
 *
 * @author volkanulutas
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AvailableUserDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -4777500450151947929L;

    private String id;

    private boolean isDeleted;

    private String username;

    private String name;

    private String surname;

    private long birthDate;

    private String birthPlace;

    private EnumUserStatus status;

    private EnumUserGender gender;

    private UserPreferenceDto userPreference;
}
