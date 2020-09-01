package com.example.jobportal.converter;

import com.example.jobportal.model.AbstractEntity;
import com.example.jobportal.dto.AbstractDto;

public interface EntityToDtoConverter<Entity extends AbstractEntity, Dto extends AbstractDto> {
	Entity toEntity(Dto dto);

	Dto toDto(Entity entity);
}
