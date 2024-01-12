package com.eomaxl.springbootblog.respository;

import com.eomaxl.springbootblog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category, Long> {
}
