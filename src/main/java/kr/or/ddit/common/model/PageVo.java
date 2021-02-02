package kr.or.ddit.common.model;

public class PageVo {
	private int page;
	private int pagesize;
	
	public PageVo() {}
	
	public PageVo(int page, int pagesize) {
		this.page = page;
		this.pagesize = pagesize;
	}
	
	public int getPage() {
		return page == 0 ? 1 : page;
	} 
	public void setPage(int page) {
		this.page = page;
	}
	public int getPagesize() {
		return pagesize == 0 ? 5 : pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	@Override
	public String toString() {
		return "PageVo [page=" + page + ", pagesize=" + pagesize + "]";
	}
	
	
}
