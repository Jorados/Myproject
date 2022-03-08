package jpabook.jpashop.domain.item;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B") //싱글테이블에서 db입장에서 알아볼수있게
@Getter
@Setter
public class Book extends Item{

   private String author;
   private String isbn;
}
