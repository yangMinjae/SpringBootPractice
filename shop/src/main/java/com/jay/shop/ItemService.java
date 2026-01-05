package com.jay.shop;

import com.jay.shop.entities.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional  //읽기 전용 해제 위해서
    public void saveItem(String title, Integer price){
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }
    @Transactional
    public void updateItem(Long id, String title, Integer price){
        itemRepository
                .findById(id)
                .map(item->{
                    item.setTitle(title);
                    item.setPrice(price);
                    return item;})
                .orElseThrow(() -> new IllegalArgumentException("수정 실패 : ID"+id+"번 아이템이 존재하지 않습니다."));
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

    @Transactional
    public saveMember(){

    }
}
