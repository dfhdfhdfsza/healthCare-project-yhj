package com.healthcare.www.membership.service;

import com.healthcare.www.membership.domain.Membership;
import com.healthcare.www.membership.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MembershipServiceImpl implements MembershipService {

    private final MembershipRepository membershipRepository;

    // 유저 보유 적립금 조회
    @Override
    public int getPoint(String userId) {
        return membershipRepository.findById(userId).get().getPoint();
    }
    
    // 유저 보유 적립금 업데이트
    @Override
    public void updateMembership(Membership membership) {
        membershipRepository.save(membership);
    }
    
}
