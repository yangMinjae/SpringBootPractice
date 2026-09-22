package com.jay.shop.services;

import com.jay.shop.DTOs.ItemDto;
import com.jay.shop.entities.Item;
import com.jay.shop.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;
    private final FileService fileService;
    @Transactional  //읽기 전용 해제 위해서
    public void saveItem(ItemDto.AddRequest dto){
        Item item = Item.builder()
                        .title(dto.title())
                        .price(dto.price())
                        .build();
        itemRepository.save(item);

        if(StringUtils.hasText(dto.fileKey())){
            fileService.confirmAndConnect(dto.fileKey(), item);
        }
    }
    @Transactional  //읽기 전용 해제 위해서
    public void saveItem(String title, int price){
        Item item = Item.builder()
                .title(title)
                .price(price)
                .build();
        itemRepository.save(item);
    }
    @Transactional
    public void updateItem(ItemDto.UpdateRequest dto){
        itemRepository
                .findById(dto.id())
                .map(item->{
                    item.update(dto.title(), dto.price());
                    return item;})
                .orElseThrow(() -> new IllegalArgumentException("수정 실패 : ID"+dto.id()+"번 아이템이 존재하지 않습니다."));
    }

    @Transactional
    public ResponseEntity<Void> deleteById(Long id){
        if(!itemRepository.existsById(id)){
            System.out.println("에러 발생!! ID "+id+"번 데이터가 없습니다.");
            return ResponseEntity.notFound().build();
        }

        itemRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
