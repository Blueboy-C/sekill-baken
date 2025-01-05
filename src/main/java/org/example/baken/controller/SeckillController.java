package org.example.baken.controller;

import org.example.baken.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    @PostMapping("/participate")
    public String participateSeckill(@RequestParam Long userId, @RequestParam Long seckillActivityId,@RequestParam BigDecimal totalPrice) {
        boolean result = seckillService.participateSeckill(userId, seckillActivityId,totalPrice);
        return result ? "秒杀成功" : "秒杀失败";
    }
}
