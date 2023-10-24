package mft.model.bl;

import mft.model.da.MemberDa;
import mft.model.entity.Member;

public class MemberBl {

    public static Member findById(int memberId) throws Exception{
        try(MemberDa memberDa = new MemberDa()) {
            return memberDa.findById(memberId);
        }
    }
}
