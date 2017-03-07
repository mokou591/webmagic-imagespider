package com.example.imagespider.processor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.imagespider.content.Content;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class HuabanSearchPageProcessor implements PageProcessor {

	private Site site = Site.me().setRetryTimes(10).setSleepTime(5000)
			.addHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.1.6) Gecko/20091201 Firefox/3.5.6");

	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {
		// 用以储存图片网址的集合
		Set<String> picUrlSet = new HashSet<String>();

		List<String> list = page.getHtml().regex("\"key\":\"([A-Za-z0-9-]{1,100})\",").all();

		System.out.println(list.size());
		for (String picKey : list) {
			if (picKey.trim().length() == 0) {
				continue;
			}
			System.out.println("http://img.hb.aicdn.com/" + picKey);
			picUrlSet.add("http://img.hb.aicdn.com/" + picKey);
		}
		// 将集合保存起来以待处理
		 page.putField(Content.PICTURE_URL.toString(), picUrlSet);

	}

}
