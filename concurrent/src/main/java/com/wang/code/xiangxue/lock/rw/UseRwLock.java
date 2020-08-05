package com.wang.code.xiangxue.lock.rw;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *类说明：
 */
public class UseRwLock implements GoodsService {
	
    private GoodsInfo goodsInfo;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock getLock = lock.readLock();//读锁
    private final Lock setLock = lock.writeLock();//写锁

    public UseRwLock(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

	@Override
	public GoodsInfo getNum() {
		getLock.lock();
		try {
			TimeUnit.MILLISECONDS.sleep(5);
			return this.goodsInfo;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			getLock.unlock();
		}
		return null;
	}

	@Override
	public void setNum(int number) {
		setLock.lock();
		try {
			TimeUnit.MILLISECONDS.sleep(5);
			goodsInfo.changeNumber(number);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			setLock.unlock();
		}
	}

}
