package com.rails.core.frame.schedule;

import com.rails.core.frame.commonstatic.StaticParams;
import com.rails.core.module.user.domain.SystemUser;
import com.rails.core.module.user.domain.UserRole;
import com.rails.core.module.user.service.SystemUserService;
import com.rails.core.module.user.service.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduleTask {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Resource(name = "SystemUserServiceImpl")
    private SystemUserService systemUserService;

    @Resource(name = "UserRoleServiceImpl")
    private UserRoleService userRoleService;


    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        logger.info("The time is now " + dateFormat.format(new Date()));
    }

    @PostConstruct
    public void init() {
        try {
            logger.info("start initializing...");
            systemUserService.clearTable();
            userRoleService.clearTable();
            SystemUser user = new SystemUser();
            user.setPassword("user");
            user.setUserName("user");
            systemUserService.save(user);
            SystemUser admin = new SystemUser();
            admin.setPassword("admin");
            admin.setUserName("admin");
            systemUserService.save(admin);

            UserRole userRole = new UserRole();
            userRole.setRole(StaticParams.USERROLE.ROLE_USER);
            userRole.setUserId(1L);
            userRoleService.save(userRole);

            UserRole adminRole = new UserRole();
            adminRole.setRole(StaticParams.USERROLE.ROLE_ADMIN);
            adminRole.setUserId(2L);
            userRoleService.save(adminRole);

            logger.info("initialization finished.");
        } catch (Exception e) {
            logger.error(String.format("ERROR when initializing:%s", e));
        }

    }

}
