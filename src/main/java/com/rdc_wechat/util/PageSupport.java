package com.rdc_wechat.util;

/**
 * 分页支持
 * @author 86178
 */
public class PageSupport {
	/**
	 * 当前页码-来自于用户输入
	 */
	private int currentPageNo = 1;

	/**
	 * 总数量（表）
	 */
	private int totalCount = 0;

	/**
	 * 页面容量
	 */
	private int pageSize = 0;

	/**
	 * 总页数-totalCount/pageSize（+1）
	 */
	private int totalPageCount = 1;

	/**
	 * 获取当前页码
	 * @return
	 */
	public int getCurrentPageNo() {
		return currentPageNo;
	}

	/**
	 * 设置当前页码
	 * @param currentPageNo
	 */
	public void setCurrentPageNo(int currentPageNo) {
		if(currentPageNo > 0){
			this.currentPageNo = currentPageNo;
		}
	}

	/**
	 * 获取总记录
	 * @return
	 */
	public int getTotalCount() {
		return totalPageCount;
	}

	/**
	 * 设置总记录
	 * @param totalCount
	 */
	public void setTotalCount(int totalCount) {
		if(totalCount > 0){
			//总记录数
			this.totalCount = totalCount;
		}
	}

	/**
	 * 获取页面大小
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置页面大小
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		if(pageSize > 0){
			this.pageSize = pageSize;
		}
	}

	/**
	 * 获取总页数
	 * @return
	 */
	public int getTotalPageCount() {
		return totalPageCount;
	}

	/**
	 * 设置总页数
	 * @param totalCount
	 */
	public void setTotalPageCount(int totalCount) {
		this.totalCount = totalCount;
		//设置总页数
		this.setTotalPageCountByRs();
	}

	/**
	 * 计算总页数大小的函数
	 */
	public void setTotalPageCountByRs(){
		if(this.totalCount % this.pageSize == 0){
			//如果总记录数%页面大小余数为零，则总页数为记录数/页数
			this.totalPageCount = this.totalCount / this.pageSize;
		}else if(this.totalCount % this.pageSize > 0){
			//如果总记录数%页面大小余数大于零，则总页数为
			this.totalPageCount = (this.totalCount / this.pageSize) + 1;
		}else{
			this.totalPageCount = 0;
		}
	}
}