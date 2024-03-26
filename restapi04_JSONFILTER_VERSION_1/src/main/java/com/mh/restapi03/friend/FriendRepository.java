package com.mh.restapi03.friend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend,Integer> {

    // findAll(), save(), findById()

    @Query(value = "select f from Friend f where f.fnick = :friendNickname")
    Friend findByFriendNickname(String friendNickname);

}
