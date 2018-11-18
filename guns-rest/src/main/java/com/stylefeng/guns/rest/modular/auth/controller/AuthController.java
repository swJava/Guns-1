package com.stylefeng.guns.rest.modular.auth.controller;

import com.stylefeng.guns.common.exception.ServiceException;
import com.stylefeng.guns.core.message.MessageConstant;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IUserService;
import com.stylefeng.guns.rest.modular.auth.controller.dto.AuthRequest;
import com.stylefeng.guns.rest.modular.auth.controller.dto.AuthResponse;
import com.stylefeng.guns.rest.modular.auth.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 请求验证的
 *
 * @author fengshuonan
 * @Date 2017/8/24 14:22
 */
@RestController
public class AuthController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "${application.auth.path}")
    public ResponseEntity<?> createAuthenticationToken(AuthRequest authRequest) {

        User currUser = userService.getByAccount(authRequest.getUserName());

        if (null == currUser){
            throw new ServiceException(MessageConstant.MessageCode.LOGIN_ACCOUNT_NOT_FOUND);
        }

        String password = currUser.getPassword();
        String salt = currUser.getSalt();

        //TODO 添加密码验证

        boolean validate = true;

        if (validate) {
            final String randomKey = jwtTokenUtil.getRandomKey();
            final String token = jwtTokenUtil.generateToken(authRequest.getUserName(), randomKey);
            return ResponseEntity.ok(new AuthResponse(token, randomKey));
        } else {
            throw new ServiceException(MessageConstant.MessageCode.LOGIN_FAILED);
        }
    }
}
