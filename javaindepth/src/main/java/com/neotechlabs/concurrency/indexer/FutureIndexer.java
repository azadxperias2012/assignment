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


public class FutureIndexer {
	
	private Deque<WebLink> queue = new ArrayDeque<>();	

	// Executors
	ExecutorService downloadExecutor = Executors.newFixedThreadPool(2);
	ExecutorService indexerExecutor = Executors.newFixedThreadPool(2);

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
			while (true) {
				String htmlPage = webLink.getHtmlPage();
				if (htmlPage != null) {
					index(htmlPage);
					break;
				} else {
					System.out.println(webLink.getId() + " not yet downloaded!");
				}
			}
		}

		private void index(String text) {
			if (text != null)
				System.out.println("\nIndexed: " + webLink.getId() + "\n");
		}
		
	}

	public void go() {
		List<Future<WebLink>> futures = new ArrayList<>();
		
		while (queue.size() > 0) {
			WebLink weblink = queue.remove();
			futures.add(downloadExecutor.submit(new Downloader<WebLink>(weblink)));
			
		}
		
		for (Future<WebLink> future : futures) {
			try {
				indexerExecutor.execute(new Indexer(future.get()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		downloadExecutor.shutdown();
		indexerExecutor.shutdown();
		
		// Can't submit new task after shutdown
//		downloadExecutor.submit(new Downloader<WebLink>(new FutureIndexer().createWebLink(2010, "Taming Tiger, Part 2",
//				"https://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
//				"https://www.javaworld.com")));
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
		FutureIndexer futureIndexer = new FutureIndexer();
		futureIndexer.add(futureIndexer.createWebLink(2000, "Taming Tiger, Part 2",
				"https://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"https://www.javaworld.com"));
		futureIndexer.add(futureIndexer.createWebLink(2001,
				"How do I import a pre-existing Java project into Eclipse and get up and running?",
				"https://stackoverflow.com/questions/142863/how-do-i-import-a-pre-existing-java-project-into-eclipse-and-get-up-and-running",
				"https://www.stackoverflow.com"));
		futureIndexer.add(futureIndexer.createWebLink(2002, "Interface vs Abstract Class",
				"http://mindprod.com/jgloss/interfacevsabstract.html", "http://mindprod.com"));
		futureIndexer.add(futureIndexer.createWebLink(2004, "Virtual Hosting and Tomcat",
				"http://tomcat.apache.org/tomcat-6.0-doc/virtual-hosting-howto.html", "http://tomcat.apache.org"));
		futureIndexer.go();
	}
}
