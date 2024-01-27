package com.kdt.goohae.service.admin;


import com.kdt.goohae.domain.admin.ManagerVO;
import com.kdt.goohae.mapper.admin.AdminMapper;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    // 필드
    private final AdminMapper mapper;

    // 생성자
    public AdminServiceImpl(AdminMapper mapper) {
        this.mapper = mapper;
    }


    /**
     * 로그인을 위한 메서드
     * @param vo ManagerVO
     * @return ManagerVO
     */
    @Override
    public ManagerVO selectOne(ManagerVO vo) {
        return mapper.selectOne(vo);
    }

    @Override
    public int addManager(ManagerVO vo) { return mapper.insertManager(vo); }
}
