package com.dyma.tennis;

import com.dyma.tennis.repository.HealthCheckRepository;
import com.dyma.tennis.service.HealthCheckService;
import com.dyma.tennis.web.HealthCheckController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class TennisApplicationTests {

	@Autowired
	private HealthCheckController healthCheckController;
	@Autowired
	private HealthCheckService healthCheckService;
	@Autowired
	private HealthCheckRepository healthCheckRepository;

	@Test
	void contextLoads() {
		assertThat(healthCheckController).isNotNull();
		assertThat(healthCheckService).isNotNull();
		assertThat(healthCheckRepository).isNotNull();
	}

}
