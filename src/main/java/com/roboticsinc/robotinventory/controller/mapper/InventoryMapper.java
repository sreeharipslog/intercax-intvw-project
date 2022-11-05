package com.roboticsinc.robotinventory.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Generic inventory mapper
 *
 * @param <D> Domain
 * @param <T> DTO
 * @author sreeharipslog
 */
public interface InventoryMapper<D, T> {
    /**
     * Convert DTO to Domain/Entity
     *
     * @param dto dto object
     * @return domain or entity to be persisted
     */
    D dtoToDomain(T dto);

    /**
     * Convert Domain/Entity to DTO
     *
     * @param domain dto object
     * @return DTO representation
     */
    T domainToDto(D domain);

    /**
     * Method to convert Domain list to DTO list
     *
     * @param domainList list of domain/entity objects
     * @return list of DTO objects
     */
    default List<T> domainListToDtoList(List<D> domainList) {
        return domainList.stream().map(this::domainToDto).collect(Collectors.toList());
    }

}