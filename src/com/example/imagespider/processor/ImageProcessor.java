package com.example.imagespider.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import com.example.imagespider.util.ImageDownloadUtil;

/**
 * 根据图片网址下载图片
 * 
 */
public class ImageProcessor implements PageProcessor {

	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000)
			.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.1.6) Gecko/20091201 Firefox/3.5.6");

	private String savePath;

	public ImageProcessor(String savePath) {
		this.savePath = savePath;
	}

	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {
		// 图片网址 完整文件目录
		String picUrl = page.getUrl().toString();
		new ImageDownloadUtil().downLoadImage(picUrl, savePath);
	}

}
