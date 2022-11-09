package com.github.liuyueyi.forum.web.admin.rest;

import com.github.liueyueyi.forum.api.model.enums.PushStatusEnum;
import com.github.liueyueyi.forum.api.model.vo.ResVo;
import com.github.liueyueyi.forum.api.model.vo.banner.ConfigReq;
import com.github.liueyueyi.forum.api.model.vo.constants.StatusEnum;
import com.github.liuyueyi.forum.service.config.service.impl.ConfigSettingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Banner后台
 *
 * @author LouZai
 * @date 2022/9/19
 */
@RestController
@RequestMapping(path = "admin/banner/")
public class BanneSettingrRestController {

    @Autowired
    private ConfigSettingServiceImpl bannerSettingService;

    @ResponseBody
    @PostMapping(path = "save")
    public ResVo<String> save(@RequestBody ConfigReq configReq) {
        bannerSettingService.saveConfig(configReq);
        return ResVo.ok("ok");
    }

    @ResponseBody
    @GetMapping(path = "delete")
    public ResVo<String> delete(@RequestParam(name = "bannerId") Integer bannerId) {
        bannerSettingService.deleteConfig(bannerId);
        return ResVo.ok("ok");
    }

    @ResponseBody
    @GetMapping(path = "operate")
    public ResVo<String> operate(@RequestParam(name = "bannerId") Integer bannerId,
                                 @RequestParam(name = "operateType") Integer operateType) {
        if (operateType != PushStatusEnum.OFFLINE.getCode() && operateType!= PushStatusEnum.ONLINE.getCode()) {
            return ResVo.fail(StatusEnum.ILLEGAL_ARGUMENTS);
        }
        bannerSettingService.operateConfig(bannerId, operateType);
        return ResVo.ok("ok");
    }
}