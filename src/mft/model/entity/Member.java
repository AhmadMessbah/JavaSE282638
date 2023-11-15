package mft.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

public class Member {
    private int id;
    private String name;
    private String family;
    private String father;
    private String nationalCode;
    private LocalDate birthDate;
    private LocalDate memberShipDate;
    private boolean deleted;


    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}