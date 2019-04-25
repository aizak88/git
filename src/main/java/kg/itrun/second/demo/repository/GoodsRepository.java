package kg.itrun.second.demo.repository;

import kg.itrun.second.demo.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {
}
