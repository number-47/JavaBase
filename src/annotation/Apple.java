package annotation;

/**
 * @author number47
 * @date 2020/6/5 22:36
 * @description
 */
public class Apple {

	@FruitProvider(id = 1,name = "红富士集团",address = "深圳市宝安区")
	private String appProvider;

	public String getAppProvider() {
		return appProvider;
	}

	public void setAppProvider(String appProvider) {
		this.appProvider = appProvider;
	}
}
