package com.healthcare.www.membership.service;

import com.healthcare.www.membership.domain.Membership;

public interface MembershipService {
    int getPoint(String userId);

    void updateMembership(Membership membership);
}
