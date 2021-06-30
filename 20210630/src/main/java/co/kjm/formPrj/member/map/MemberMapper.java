package co.kjm.formPrj.member.map;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import co.kjm.formPrj.member.vo.MemberVO;

public interface MemberMapper {
	
	@Select("select * from member")
	List<MemberVO> memberSelectList();
	
	MemberVO memberSelect(MemberVO vo);
	MemberVO memberLogin(MemberVO vo);
	int memberInsert(MemberVO vo);
	int memberUpdate(MemberVO vo);
	int memberDelete(MemberVO vo);
	
}
