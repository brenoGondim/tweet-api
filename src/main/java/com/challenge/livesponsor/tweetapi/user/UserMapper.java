package com.challenge.livesponsor.tweetapi.user;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserDTO toDTO(User entity);
    List<UserDTO> toDTOList(List<User> entity);
    @InheritInverseConfiguration(name = "toDTO")
    User toEntity(UserDTO dto);
    @InheritInverseConfiguration(name = "toDTOList")
    List<User> toEntityList(List<UserDTO> model);


}
