package com.hackathon.server.repository.user;

import com.hackathon.server.model.user.UserPermission;
import com.hackathon.server.model.user.enums.UserRight;
import com.hackathon.server.model.user.enums.UserViewRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermission, Long> {

    @Query("SELECT p FROM UserPermission p " +
            "JOIN p.userGroup g " +
            "JOIN g.users u ON u.id = :userId")
    List<UserPermission> findPermissionsByUserId(@Param("userId")Long userId);

    @Query("SELECT CASE WHEN count(p) > 0 THEN true ELSE false END FROM UserPermission p " +
            "JOIN p.userGroup g JOIN g.users u ON u.username = ?#{principal.username} " +
            "WHERE p.viewRight = :right " +
            "AND p.userViewRole = :viewRole " +
            "AND p.viewName = :viewName ")
    boolean existPermission(@Param("viewName")String viewName, @Param("right") UserRight right,
                            @Param("viewRole") UserViewRole viewRole);

}
