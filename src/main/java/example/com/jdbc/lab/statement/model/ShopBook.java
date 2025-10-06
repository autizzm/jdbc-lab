package example.com.jdbc.lab.statement.model;


public class ShopBook {
 private Long id;
 private Long shopId;
 private Long bookId;
 public ShopBook() {}
 public ShopBook(Long shopId, Long bookId) {
 this.shopId = shopId;
 this.bookId = bookId;
 }

 @Override
 public String toString() {
  return "ShopBook{" +
          "id=" + id +
          ", shopId=" + shopId +
          ", bookId=" + bookId +
          '}';
 }

 public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
