package com.jay.shop.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Getter
@Table(name = "tb_img_file")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ImageFile extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String fileKey;

    private String originalName;
    private String contentType;
    private Long fileSize;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FileStatus status;
    public enum FileStatus{
        PENDING, ACTIVE
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    public void confirmUpload(Long actualSize, String actualContentType, Item item){
        this.fileSize = actualSize;
        this.contentType = actualContentType;
        this.status = FileStatus.ACTIVE;
        this.item = item;
    }
}

