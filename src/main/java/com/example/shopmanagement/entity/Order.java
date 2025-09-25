package com.example.shopmanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_date", columnDefinition = "datetime(6)")
    private LocalDateTime orderDate = LocalDateTime.now();

    @Column(name = "receive_date", columnDefinition = "datetime(6)")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") // parse LocalDateTime chuẩn ISO
    private LocalDateTime receiveDate;

    @Column(length = 20)
    private String status = "PENDING";

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> details = new ArrayList<>();
}
