package com.cade.cadeonibus;

import com.cade.cadeonibus.config.ApplicationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class CadeOnibusApplication {
  private static final Logger log = LoggerFactory.getLogger(CadeOnibusApplication.class);

  public static void main(String[] args) throws UnknownHostException {
    SpringApplication application = new SpringApplication(CadeOnibusApplication.class);
    Environment env = application.run(args).getEnvironment();

    log.info("\n----------------------------------------------------------" +
        "\n\t" + "Application '{}' is running! " +
        "Access URLs:\n\t " +
        "Local: \t{}://localhost:{}\n\t " +
        "External: \t{}://{}:{}\n\t " +
        "Profile: \t{}\n" +
        "----------------------------------------------------------",
      env.getProperty("spring.application.name"),
      env.getProperty("server.protocol"),
      env.getProperty("server.port"),
      env.getProperty("server.protocol"),
      InetAddress.getLocalHost().getHostAddress(),
      env.getProperty("server.port"),
      env.getActiveProfiles());
  }

}
