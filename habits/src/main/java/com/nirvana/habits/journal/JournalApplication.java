package com.nirvana.habits.journal;

import com.nirvana.habits.journal.todo.TodoCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
@Slf4j
public class JournalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
	public void handleTodoCreatedEvent(TodoCreatedEvent event) {
		log.info("todoCreationEventHandler" + event);
	}
//	@Bean
//	public RedisCacheConfiguration cacheConfiguration() {
//		// https://www.baeldung.com/spring-boot-redis-cache
//		// https://programmerfriend.com/ultimate-guide-to-redis-cache-with-spring-boot-2-and-spring-data-redis/
//		return RedisCacheConfiguration.defaultCacheConfig()
//				.entryTtl(Duration.ofMinutes(1))
//				.disableCachingNullValues()
//				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
//	}
}
