package com.cade.cadeonibus;

import com.cade.cadeonibus.config.ApplicationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class CadeOnibusApplication {
  private static final Logger log = LoggerFactory.getLogger(CadeOnibusApplication.class);

  public static void main(String[] args) throws UnknownHostException {
    File file = new File(CadeOnibusApplication.class.getClassLoader().getResource(".").getFile() + "/test.xml");
    FileOutputStream fos = null;
    try {
      Map<String, String> env = System.getenv();

      fos = new FileOutputStream(file);
      DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));
      outStream.writeUTF(env.get("GOOGLE_CREDENTIALS"));
      outStream.close();

      Field field = null;
      field = env.getClass().getDeclaredField("m");
      field.setAccessible(true);
      ((Map<String, String>) field.get(env)).put("GOOGLE_APPLICATION_CREDENTIALS", file.getAbsolutePath());
    } catch (NoSuchFieldException | IOException | IllegalAccessException e) {
      e.printStackTrace();
    }

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
