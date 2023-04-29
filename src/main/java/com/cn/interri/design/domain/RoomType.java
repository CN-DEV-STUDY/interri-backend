package com.cn.interri.design.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room_type")
@Getter
@NoArgsConstructor
public class RoomType {
    @Id
    @Column(name = "rood_type_id")
    @Comment("공간 id")
    private int id;

    @Column(length = 10)
    @Comment("공간명")
    private String roomTypeNm;

    @OneToMany(mappedBy = "roomType")
    private List<DesignReqRoomType> designReqRoomTypeList = new ArrayList<>();
}