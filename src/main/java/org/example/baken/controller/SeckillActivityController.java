package org.example.baken.controller;

import org.example.baken.entity.ApiResponse;
import org.example.baken.entity.PageResult;
import org.example.baken.entity.SeckillActivity;
import org.example.baken.service.SeckillActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/seckill-activities")
public class SeckillActivityController {
    @Autowired
    private SeckillActivityService seckillActivityService;

    @GetMapping("/{id}")
    public ApiResponse<SeckillActivity> getSeckillActivityById(@PathVariable Long id) {
        SeckillActivity activity = seckillActivityService.getSeckillActivityById(id);
        return ApiResponse.success(activity);
    }

    @GetMapping
    public ApiResponse<List<SeckillActivity>> getAllSeckillActivities() {
        List<SeckillActivity> activities = seckillActivityService.getAllSeckillActivities();
        return ApiResponse.success(activities);
    }

    @GetMapping("/page")
    public ApiResponse<PageResult<SeckillActivity>> getSeckillActivitiesByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageResult<SeckillActivity> result = seckillActivityService.getSeckillActivitiesByPage(pageNum, pageSize);
        return ApiResponse.success(result);
    }

    @GetMapping("/search")
    public ApiResponse<List<SeckillActivity>> searchSeckillActivities(@RequestParam String name) {
        List<SeckillActivity> activities = seckillActivityService.searchSeckillActivities(name);
        return ApiResponse.success(activities);
    }

    @PostMapping
    public ApiResponse<String> addSeckillActivity(@RequestBody SeckillActivity seckillActivity) {
        seckillActivityService.addSeckillActivity(seckillActivity);
        return ApiResponse.success("秒杀活动添加成功");
    }

    @PutMapping("/{id}")
    public ApiResponse<String> updateSeckillActivity(@PathVariable Long id, @RequestBody SeckillActivity seckillActivity) {
        seckillActivity.setId(id);
        seckillActivityService.updateSeckillActivity(seckillActivity);
        return ApiResponse.success("秒杀活动更新成功");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteSeckillActivityById(@PathVariable Long id) {
        seckillActivityService.deleteSeckillActivityById(id);
        return ApiResponse.success("秒杀活动删除成功");
    }

    // 新增接口：获取当前正在进行的秒杀活动
    @GetMapping("/current")
    public ApiResponse<PageResult<SeckillActivity>> getCurrentSeckillActivitiesByPage(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize) {
        PageResult<SeckillActivity> result = seckillActivityService.getCurrentSeckillActivitiesByPage(pageNum, pageSize);
        return ApiResponse.success(result);
    }

}