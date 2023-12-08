package com.example.ordermanagement.model;

import com.example.ordermanagement.model.user.UserRole;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class UserRoleTest {

    @ParameterizedTest
    @EnumSource(UserRole.class)
    void allRoleSatusShouldBeShorterThan5Chars(UserRole userRole){

        assertThat(userRole.toString().length(),lessThan(6));
    }

    @ParameterizedTest
    @EnumSource(UserRole.class)
    void allRoleStatusShouldBeLongerThan1Chars(UserRole userRole){
        assertThat(userRole.toString().length(),greaterThanOrEqualTo(3));
    }
    @ParameterizedTest
    @EnumSource(UserRole.class)
    void allRoleShouldBeSizeNoMoreThan2(UserRole userRole){
        assertThat(userRole.getClass().getFields().length,is(3));
    }
}