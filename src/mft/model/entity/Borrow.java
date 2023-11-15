package mft.model.entity;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

public class Borrow {
    private int id;
    private Member member;
    private Book book;
    private LocalDateTime borrowTimeStamp;
    private LocalDateTime returnTimeStamp;
    private String description;
    private boolean deleted;

    @Override
    public String toString() {
        Gson gson= new Gson();
        return gson.toJson(this);
    }
}
