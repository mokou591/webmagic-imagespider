package com.example.imagespider.processor;

import java.util.HashSet;
import java.util.Set;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import com.example.imagespider.content.Content;

/**
 * 在一个网页上获取指定的图片网址，并记录下网址
 * 
 */
public class WallhavenSearchPageProcessor implements PageProcessor {

	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000)
			.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.1.6) Gecko/20091201 Firefox/3.5.6");

	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {
		// 用以储存图片网址的集合
		Set<String> picUrlSet = new HashSet<String>();
		for (String picUrl : page.getHtml().$("img", "data-src").all()) {
			if (picUrl.trim().length() == 0) {
				continue;
			}
			// 提取图片编号
			String picNum = picUrl.substring(picUrl.lastIndexOf("-") + 1, picUrl.lastIndexOf("."));
			// 组合出图片大图的下载地址
			String bigPicUrl = "https://wallpapers.wallhaven.cc/wallpapers/full/wallhaven-" + picNum + ".jpg";
			picUrlSet.add(bigPicUrl);
			System.out.println(bigPicUrl);
		}
		// 将集合保存起来以待处理
		page.putField(Content.PICTURE_URL.toString(), picUrlSet);
	}

}
