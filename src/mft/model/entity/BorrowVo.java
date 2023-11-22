package mft.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class BorrowVo {
    private int id;
    private String memberFullName;
    private String bookFullInfo;
    private LocalDateTime borrowTimeStamp;
    private LocalDateTime returnTimeStamp;

    public BorrowVo(Borrow borrow) {
        this.id = borrow.getId();
        this.memberFullName = borrow.getMember().getName() + " " + borrow.getMember().getFamily();
        this.bookFullInfo = borrow.getBook().getName() + " By " + borrow.getBook().getAuthor();
        this.borrowTimeStamp = borrow.getBorrowTimeStamp();
        this.returnTimeStamp = borrow.getReturnTimeStamp();
    }
}
