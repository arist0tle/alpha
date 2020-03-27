package com.geektcp.alpha.driver.mybatis.controller;

import com.fengwenyi.api_result.helper.ResultHelper;
import com.fengwenyi.api_result.model.ResultModel;
import com.geektcp.alpha.driver.mybatis.business.AppBusiness;
import com.geektcp.alpha.driver.mybatis.enums.GenderEnum;
import com.geektcp.alpha.driver.mybatis.model.po.CityPo;
import com.geektcp.alpha.driver.mybatis.model.vo.PageResponse;
import com.geektcp.alpha.driver.mybatis.model.po.CardPo;
import com.geektcp.alpha.driver.mybatis.model.po.StudentPo;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    // 查询所有城市
    @GetMapping("/queryCityAll")
    public ResultModel queryCityAll() {
        List<CityPo> cities = cityService.queryCityAll();
        return ResultHelper.success("Success", cities);
    }


    // 添加城市
    @PostMapping("/addCity")
    public ResultModel addCity(String name) {
        if (StringUtils.isEmpty(name))
            return ResultHelper.error("名称不能为空");
        boolean rs = cityService.addCity(new CityPo().setName(name));
        if (rs)
            return ResultHelper.success("Success", null);
        return ResultHelper.error("添加失败");
    }

    // 添加学生
    @PostMapping("/addStudent")
    public ResultModel addStudent(String name, Integer age, String gender, String info, String idCardCode, String cityName) {
        if (StringUtils.isEmpty(name)
                || age == null
                || StringUtils.isEmpty(gender)
                || StringUtils.isEmpty(info)
                || StringUtils.isEmpty(idCardCode)
                || StringUtils.isEmpty(cityName))
            return ResultHelper.error("参数不合法");

        // 获取GenderEnum
        GenderEnum genderEnum = GenderEnum.getEnumByDesc(gender);

        // studentPo
        StudentPo studentPo = new StudentPo()
                .setName(name)
                .setAge(age)
                .setGender(genderEnum)
                .setInfo(info);

        // city
        CityPo cityPo = new CityPo().setName(cityName);

        // idCard
        CardPo cardPo = new CardPo().setCode(idCardCode);

        // service
        boolean rs = appBusiness.addStudent(studentPo, cityPo, cardPo);
        if (rs)
            return ResultHelper.success("Success", null);
        return ResultHelper.error("添加失败");
    }


    @PostMapping("/findPage")
    public ResultModel findPage() {
        List<CityPo> cities = cityService.queryCityAll();
        return ResultHelper.success("Success", cities);
    }


    @ApiOperation(value = "分页查询公司的用户列表")
    @ApiResponses({@ApiResponse(code = 200, message = "成功")})
    @PostMapping("/findPage")
    public PageResponse<UserVo> findPage(@RequestBody @Valid UserQo userQo) {
        return userService.findPage(userQo);
    }
}
