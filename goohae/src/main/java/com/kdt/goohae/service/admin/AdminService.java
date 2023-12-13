package com.kdt.goohae.service.admin;

import com.kdt.goohae.domain.admin.ManagerVO;

public interface AdminService {

    /* 로그인을 위한 메서드 */
    ManagerVO selectOne(ManagerVO vo);

}
