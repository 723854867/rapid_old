package org.rapid.util.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorService {
	
	private int minPoolSize;
	private int maxPoolSize;
	private int keepAliveTime;
	private int queueCapacity;
	private boolean allowCoreThreadTimeOut;
	private RejectedExecutionHandler rejectedHandler;

	private ThreadPoolExecutor executor;
	
	public void init() {
		 this.executor = new ThreadPoolExecutor(minPoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, 
				 								new ArrayBlockingQueue<Runnable>(queueCapacity),  
				 								new DefaultThreadFactory(ExecutorService.class), rejectedHandler);
		 this.executor.allowCoreThreadTimeOut(allowCoreThreadTimeOut);
	}
	
	public void execute(Runnable runnable) {
		this.executor.execute(runnable);
	}
	
	public void setMinPoolSize(int minPoolSize) {
		this.minPoolSize = minPoolSize;
	}
	
	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}
	
	public void setKeepAliveTime(int keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}
	
	public void setQueueCapacity(int queueCapacity) {
		this.queueCapacity = queueCapacity;
	}
	
	public void setAllowCoreThreadTimeOut(boolean allowCoreThreadTimeOut) {
		this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
	}
	
	public void setRejectedHandler(RejectedExecutionHandler rejectedHandler) {
		this.rejectedHandler = rejectedHandler;
	}
}
