package kg.itrun.second.demo.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "goods_id")
    private int id;
    private String goodsName;
    private float count;
    private float price;
    /*   private int is_active;
    private String info;

    @ManyToOne
    @JoinColumn(name = "price_id")
    private Prices price_id;

    @ManyToOne
    @JoinColumn(name = "good_type_id")
    private Goods_types goods_type_id;
    */
}