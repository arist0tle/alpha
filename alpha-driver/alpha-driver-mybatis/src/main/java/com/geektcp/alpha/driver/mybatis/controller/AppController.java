package com.geektcp.alpha.driver.mybatis.controller;

import com.geektcp.alpha.driver.mybatis.business.AppBusiness;
import com.geektcp.alpha.driver.mybatis.model.vo.PageResponse;
import com.geektcp.alpha.driver.mybatis.model.qo.UserQo;
import com.geektcp.alpha.driver.mybatis.model.vo.UserVo;
import com.geektcp.alpha.driver.mybatis.service.CityService;
import com.geektcp.alpha.driver.mybatis.service.StudentService;
import com.geektcp.alpha.driver.mybatis.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 测试
 *
 * @author tanghaiyang
 * @since 2018-09-01
 */
@RestController
@RequestMapping(value = "/app", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "App 测试示例")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AppController {

    private CityService cityService;

    private AppBusiness appBusiness;

    private StudentService studentService;

    private UserService userService;

    @ApiOperation(value = "分页查询公司的用户列表")
    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
    @PostMapping("/findPage")
    public PageResponse<UserVo> findPage(@RequestBody @Valid UserQo userQo) {
        return userService.findPage(userQo);
    }
}
