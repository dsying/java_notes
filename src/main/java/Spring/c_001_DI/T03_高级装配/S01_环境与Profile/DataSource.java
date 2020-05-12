package Spring.c_001_DI.T03_高级装配.S01_环境与Profile;

public interface DataSource {
  String getEnvironment();
}

class DevDatasource implements DataSource{

  @Override
  public String getEnvironment() {
    return "dev";
  }
}

class ProdDatasource implements DataSource{

  @Override
  public String getEnvironment() {
    return "prod";
  }
}

class TestDatasource implements DataSource{

  @Override
  public String getEnvironment() {
    return "test";
  }
}
