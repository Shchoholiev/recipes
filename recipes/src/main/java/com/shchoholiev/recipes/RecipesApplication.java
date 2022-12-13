package com.shchoholiev.recipes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class RecipesApplication {

	private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public static void startHealthCheck() {
		var healthCheck = new Runnable() {
			public void run() {
				var client = HttpClient.newHttpClient();

				var request = HttpRequest
						.newBuilder(URI.create("https://sh-recipes-api.azurewebsites.net/api/health/check"))
						.GET()
						.build();

				var response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
			}
		};

		scheduler.scheduleAtFixedRate(healthCheck, 60, 60 * 5, TimeUnit.SECONDS);
	}

	public static void main(String[] args) {
		var context = SpringApplication.run(RecipesApplication.class, args);
		startHealthCheck();
	}

}
