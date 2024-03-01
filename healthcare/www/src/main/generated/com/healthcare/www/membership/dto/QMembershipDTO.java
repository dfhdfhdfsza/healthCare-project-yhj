package com.healthcare.www.membership.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.healthcare.www.membership.dto.QMembershipDTO is a Querydsl Projection type for MembershipDTO
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMembershipDTO extends ConstructorExpression<MembershipDTO> {

    private static final long serialVersionUID = 1717899917L;

    public QMembershipDTO(com.querydsl.core.types.Expression<? extends com.healthcare.www.membership.domain.Membership> membership) {
        super(MembershipDTO.class, new Class<?>[]{com.healthcare.www.membership.domain.Membership.class}, membership);
    }

}

