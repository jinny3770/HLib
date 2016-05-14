package com.swdesign.hlib.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.swdesign.hlib.domain.MemberDomain;

public class MemberInfoDAO {
	
	
	
	
	public MemberInfoDAO() {
		try {
			Context context =new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public MemberDomain getMember(String memberID) {
		
		MemberDomain memberDomain = new MemberDomain();
		
		return memberDomain;
	}
	
	public void saveMember(MemberDomain memberDomain) {
		
	}
}
