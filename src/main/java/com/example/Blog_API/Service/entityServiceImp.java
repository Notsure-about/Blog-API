package com.example.Blog_API.Service;

import com.example.Blog_API.DTO.entityDto;
import com.example.Blog_API.Entity.Category;
import com.example.Blog_API.Entity.entity;
import com.example.Blog_API.Exception.ResourceNotFoundException;
import com.example.Blog_API.Repository.CategoryRepository;
import com.example.Blog_API.Repository.entityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class entityServiceImp implements entityService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private entityRepository entityRepository;
    @Autowired
    private  ModelMapper modelMapper;
    private entityDto convertToDto(entity entity){
        return modelMapper.map(entity,entityDto.class);
    }
    private  entity converToentity(entityDto entityDto){
        return modelMapper.map(entityDto,entity.class);
    }

    @Override
    public entityDto CreatePost(entityDto entityDto ) {
//        entity entity = new entity();
//        entity.setTitle(entityDto.getTitle());
//        entity.setContent(entityDto.getContent());
//        entity saved = entityRepository.save(entity);
//        entityDto dto = new entityDto();
//        dto.setId(saved.getId());
//        dto.setTitle(saved.getTitle());
//        dto.setContent(saved.getContent());
//        return dto;
        //convert to entity
//        entity ent = converToentity(entityDto);
        // find Category
        Category category = categoryRepository.findById(entityDto.getCategoryId()).orElseThrow(()-> new RuntimeException("Category not found") );
        //attach category
        entity ent = new entity();
        ent.setCategory(category);
        entity saved = entityRepository.save(ent);
        return convertToDto(saved);
    }

    @Override
    public entityDto getPostById(Long id) {
//        entity ent =  entityRepository.findById(id).orElseThrow();
//      entityDto dto = new entityDto();
//      dto.setId(ent.getId());
//      dto.setTitle(ent.getTitle());
//      dto.setContent(ent.getContent());
//      return dto;
        entity ent = entityRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("entity","id", id));
        return convertToDto(ent);

    }

    @Override
    public List<entityDto> getAllPosts() {
//        return entityRepository.findAll()
//                .stream()
//                .map(p-> new entityDto(p.getId(),p.getTitle(),p.getContent()))
//                .collect(Collectors.toList());
     return entityRepository.findAll()
             .stream()
             .map(this::convertToDto)
             .collect(Collectors.toList());
    }

    @Override
    public entityDto updatePost(Long id, entityDto entityDto) {
//        entity tity = entityRepository.findById(id).orElseThrow();
//        tity.setTitle(entityDto.getTitle());
//        tity.setContent(entityDto.getContent());
//        entity updated = entityRepository.save(tity);
//        return new entityDto(updated.getId(), updated.getTitle(), updated.getContent());
       entity tity = entityRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("entity","id",id ));
       tity.setTitle(entityDto.getTitle());
       tity.setContent(entityDto.getContent());
        if (entityDto.getCategoryId() != null) {
            Category category = categoryRepository.findById(entityDto.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            tity.setCategory(category);
        }
       entity updated = entityRepository.save(tity);
       return convertToDto(updated);
 }

    @Override
    public void deletePost(Long id) {
      entityRepository.deleteById(id);
    }
}
