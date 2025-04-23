package me.jimmyberg.springweb.infra.config

import org.springframework.beans.factory.annotation.Configurable
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@Configurable
class SpringWebApplicationConfig