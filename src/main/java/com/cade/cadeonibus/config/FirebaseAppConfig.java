package com.cade.cadeonibus.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FirebaseAppConfig {

  @Bean
  public void firebaseApp() throws IOException {
    FirebaseOptions options = new FirebaseOptions.Builder()
      .setCredentials(GoogleCredentials.getApplicationDefault())
      .build();
    FirebaseApp.initializeApp(options);
  }
}
