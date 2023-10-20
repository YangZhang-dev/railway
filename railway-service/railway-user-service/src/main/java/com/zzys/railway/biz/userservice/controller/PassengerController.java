package com.zzys.railway.biz.userservice.controller;


import com.zzys.railway.biz.userservice.dto.req.PassengerRemoveReqDTO;
import com.zzys.railway.biz.userservice.dto.req.PassengerReqDTO;
import com.zzys.railway.biz.userservice.dto.resp.PassengerActualRespDTO;
import com.zzys.railway.biz.userservice.dto.resp.PassengerRespDTO;
import com.zzys.railway.biz.userservice.service.PassengerService;
import com.zzys.railway.framework.starter.convention.result.Result;
import com.zzys.railway.framework.starter.idempotent.annotation.Idempotent;
import com.zzys.railway.framework.starter.idempotent.enums.IdempotentSceneEnum;
import com.zzys.railway.framework.starter.idempotent.enums.IdempotentTypeEnum;
import com.zzys.railway.framework.starter.user.core.UserContext;
import com.zzys.railway.framework.starter.web.Results;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 乘车人控制层
 */
@RestController
@RequiredArgsConstructor
public class PassengerController {

    private final PassengerService passengerService;

    /**
     * 根据用户名查询乘车人列表
     */
    @GetMapping("/api/user-service/passenger/query")
    public Result<List<PassengerRespDTO>> listPassengerQueryByUsername() {
        return Results.success(passengerService.listPassengerQueryByUsername(UserContext.getUsername()));
    }

    /**
     * 根据乘车人 ID 集合查询乘车人列表
     */
    @GetMapping("/api/user-service/inner/passenger/actual/query/ids")
    public Result<List<PassengerActualRespDTO>> listPassengerQueryByIds(@RequestParam("username") String username, @RequestParam("ids") List<Long> ids) {
        return Results.success(passengerService.listPassengerQueryByIds(username, ids));
    }

    /**
     * 新增乘车人
     */
    @Idempotent(
            uniqueKeyPrefix = "railway-user:lock_passenger-alter:",
            key = "T(com.zzys.railway.frameworks.starter.user.core.UserContext).getUsername()",
            type = IdempotentTypeEnum.SPEL,
            scene = IdempotentSceneEnum.RESTAPI,
            message = "正在新增乘车人，请稍后再试..."
    )
    @PostMapping("/api/user-service/passenger/save")
    public Result<Void> savePassenger(@RequestBody PassengerReqDTO requestParam) {
        passengerService.savePassenger(requestParam);
        return Results.success();
    }

    /**
     * 修改乘车人
     */
    @Idempotent(
            uniqueKeyPrefix = "railway-user:lock_passenger-alter:",
            key = "T(com.zzys.railway.frameworks.starter.user.core.UserContext).getUsername()",
            type = IdempotentTypeEnum.SPEL,
            scene = IdempotentSceneEnum.RESTAPI,
            message = "正在修改乘车人，请稍后再试..."
    )
    @PostMapping("/api/user-service/passenger/update")
    public Result<Void> updatePassenger(@RequestBody PassengerReqDTO requestParam) {
        passengerService.updatePassenger(requestParam);
        return Results.success();
    }

    /**
     * 移除乘车人
     */
    @Idempotent(
            uniqueKeyPrefix = "railway-user:lock_passenger-alter:",
            key = "T(com.zzys.railway.frameworks.starter.user.core.UserContext).getUsername()",
            type = IdempotentTypeEnum.SPEL,
            scene = IdempotentSceneEnum.RESTAPI,
            message = "正在移除乘车人，请稍后再试..."
    )
    @PostMapping("/api/user-service/passenger/remove")
    public Result<Void> removePassenger(@RequestBody PassengerRemoveReqDTO requestParam) {
        passengerService.removePassenger(requestParam);
        return Results.success();
    }
}
