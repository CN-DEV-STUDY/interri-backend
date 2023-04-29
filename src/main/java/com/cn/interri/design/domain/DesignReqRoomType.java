package com.cn.interri.design.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "design_req_room_type")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class DesignReqRoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "design_reg_room_type_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "design_reg_info_id")
    private DesignReqInfo designReqInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rood_type_id")
    private RoomType roomType;
}
