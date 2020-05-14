package Spring.c_001_DI.T03_高级装配.S04_运行时注入值.解析属性占位符;

public class CD {
  private String title;
  private String artist;

  public CD(String title, String artist) {
    this.title = title;
    this.artist = artist;
  }

  public String getTitle() {
    return title;
  }

  public String getArtist() {
    return artist;
  }
}
