package me.jimmyberg.springweb.infrastructure.config

import org.springframework.beans.factory.annotation.Configurable
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@Configurable
class ApplicationConfig