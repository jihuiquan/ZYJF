package com.dynamic.foundations.common.assist;


/**
 * 时间均值计算器,只能用于单线程计时。
 *
 * @author MaTianyu
 * 2013-12-12上午1:23:19
 */
public class TimeAverager {
	/**
	 * 计时器
	 */
	private TimeCounter tc = new TimeCounter();
	/**
	 * 均值器
	 */
	private Averager av = new Averager();

	/**
	 * 一个计时开始
	 * @return
	 */
	public long start() {
		return tc.go();
	}

	/**
	 * 一个计时结束
	 * @return
	 */
	public long end() {
		long time = tc.stop();
		av.add(time);
		return time;
	}

	/**
	 * 求全部计时均值
	 * @return
	 */
	public Number average() {
		return av.getAverage();
	}

	/**
	 * 打印全部时间值
	 */
	public void print() {
		av.print();
	}
	
	/**
	 * 清楚数据
	 */
	public void clear() {
		av.clear();
	}
}
