package com.neotechlabs.concurrency.indexer;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TimedInvokeAllIndexer {
	private Deque<WebLink> queue = new ArrayDeque<>();	

	// Executors
	ExecutorService downloadExecutor = Executors.newFixedThreadPool(2);
	ExecutorService indexerExecutor = Executors.newFixedThreadPool(2);
	
	private static final long TIME_FRAME = 3000000000L; // 3 seconds

	private static class WebLink {
		private long id;
		private String title;
		private String url;
		private String host;
		
		private volatile String htmlPage;
		
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}
		
		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
		
		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public String getHtmlPage() {
			return htmlPage;
		}

		public void setHtmlPage(String htmlPage) {
			this.htmlPage = htmlPage;
		}
	}
	
	private static class Downloader<T extends WebLink> implements Callable<T> {

		private T webLink;		
		public Downloader(T webLink) {
			this.webLink = webLink;
		}

		@Override
		public T call() {
			try {
				String htmlPage = HttpConnect.download(webLink.getUrl());
				webLink.setHtmlPage(htmlPage);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return webLink;
		}

	}
	
	private static class Indexer implements Runnable {
		private WebLink webLink;
		public Indexer(WebLink webLink) {
			this.webLink = webLink;
		}

		@Override
		public void run() {
			String htmlPage = webLink.getHtmlPage();
			index(htmlPage);
		}

		private void index(String text) {
			if (text != null)
				System.out.println("\nIndexed: " + webLink.getId() + "\n");
		}
		
	}

	public void go() {
		long endTime = System.nanoTime() + TIME_FRAME;
		List<Downloader<WebLink>> tasks = new ArrayList<>();
		List<Future<WebLink>> futures = new ArrayList<>();		

		while (queue.size() > 0) {
			WebLink weblink = queue.remove();
			tasks.add(new Downloader<WebLink>(weblink));
		}
		
		long timeLeft = endTime - System.nanoTime();
		try {
			futures = downloadExecutor.invokeAll(tasks, timeLeft, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (Future<WebLink> future : futures) {
			try {
				if (!future.isCancelled()) {
					indexerExecutor.execute(new Indexer(future.get()));
				} else {
					System.out.println("\n\nTask is cancelled --> " + Thread.currentThread().getName());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		downloadExecutor.shutdown();
		indexerExecutor.shutdown();
	}
	
	public void add(WebLink link) {
		queue.add(link);
	}
	
	public WebLink createWebLink(long id, String title, String url, String host) {
		WebLink weblink = new WebLink();
		weblink.setId(id);
		weblink.setTitle(title);
		weblink.setUrl(url);
		weblink.setHost(host);		
		return weblink;
	}
	
	public static void main(String[] args) {
		TimedInvokeAllIndexer timedInvokeAllIndexer = new TimedInvokeAllIndexer();
		timedInvokeAllIndexer.add(timedInvokeAllIndexer.createWebLink(2000, "Taming Tiger, Part 2",
				"https://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"https://www.javaworld.com"));
		timedInvokeAllIndexer.add(timedInvokeAllIndexer.createWebLink(2001,
				"How do I import a pre-existing Java project into Eclipse and get up and running?",
				"https://stackoverflow.com/questions/142863/how-do-i-import-a-pre-existing-java-project-into-eclipse-and-get-up-and-running",
				"https://www.stackoverflow.com"));
		timedInvokeAllIndexer.add(timedInvokeAllIndexer.createWebLink(2002, "Interface vs Abstract Class",
				"http://mindprod.com/jgloss/interfacevsabstract.html", "http://mindprod.com"));
		timedInvokeAllIndexer.add(timedInvokeAllIndexer.createWebLink(2004, "Virtual Hosting and Tomcat",
				"http://tomcat.apache.org/tomcat-6.0-doc/virtual-hosting-howto.html", "http://tomcat.apache.org"));
		timedInvokeAllIndexer.go();
	}
}
