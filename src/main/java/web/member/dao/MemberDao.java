package web.member.dao;

import web.member.vo.Member;

public interface MemberDao {

	Member selectByUsernameAndPassowrd(String username, String password);

}