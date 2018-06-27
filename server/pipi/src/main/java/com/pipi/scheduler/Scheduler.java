package com.pipi.scheduler;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pipi.constant.OrderConstant;
import com.pipi.dao.pojo.OrderMain;
import com.pipi.service.DiskService;
import com.pipi.service.OrdeMainService;
import com.pipi.utils.PIPIUtils;

@Component
@EnableScheduling
@PropertySource(value = { "classpath:schedule.properties" })
public class Scheduler {

	private static final Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);

	@Value(value = "${NOPAY_EXPARE_INTERVAL}")
	private Long NOPAY_EXPARE_INTERVAL;

	@Value(value = "${NOACCEPT_EXPARE_INTERVAL}")
	private Long NOACCEPT_EXPARE_INTERVAL;

	@Autowired
	private OrdeMainService orderMainService;

	@Autowired
	private DiskService diskService;

	/**
	 * 轮训未支付的订单 如果超过30分钟就算过期 把status置位7交易取消
	 */
	@Scheduled(fixedRate = 1000 * 60 * 5) // 每5分钟执行一次
	public void noPayExpire() {
		expire(OrderConstant.NO_PAY, OrderConstant.CANCEL_NO_PAY, NOPAY_EXPARE_INTERVAL);
	}

	/**
	 * 轮训未接单付的订单 如果超过30分钟就算过期 把status置位8交易取消
	 */
	@Scheduled(fixedRate = 1000 * 60 * 5) // 每5分钟执行一次
	public void noAcceptExpire() {
		expire(OrderConstant.YES_PAY, OrderConstant.CANCEL_PAY, NOACCEPT_EXPARE_INTERVAL);
	}

	/**
	 * 每月1号陵城1点商品销售量清0
	 */
	@Scheduled(cron = "0 0 0 1 * ?") // 克龙表达式 秒 分钟 小时 日 月 星期 年
	public void resetSellNum() {
		diskService.updateAllDiskSellNum(0);
	}

	/**
	 * 提出来 取消订单
	 * 
	 * @param status
	 * @param changeStatus
	 */
	private void expire(Integer status, Integer changeStatus, Long minute) {
		try {
			List<OrderMain> list = orderMainService.getOrderByStutus(status);
			for (OrderMain orderMain : list) {
				// 应为得到的数据是已经排好序的 日期是从小到大 最小的都还没
				// 过期的话，就可以直接结束循环了
				if (PIPIUtils.isExpired(orderMain.getCreateTime(), minute)) {
					// 如果大于过期时间 关闭订单
					orderMain.setStatus(changeStatus);
					orderMain.setCloseTime(new Date());
					// 更新到数据库
					orderMainService.updateOrder(orderMain);
				} else {
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error(e.toString(), "任务调度器出现异常!");
			System.err.println("执行Quartz定时器任务：" + new Date());
		}
	}
}
