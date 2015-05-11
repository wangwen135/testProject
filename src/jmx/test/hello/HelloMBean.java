package jmx.test.hello;

public interface HelloMBean {

	public String sayHello(String who);

	/**
	 * <pre>
	 * 模拟计算
	 * </pre>
	 * 
	 * @param x
	 *            参数1
	 * @param y
	 *            参数2
	 * @return 结果
	 */
	public int add(int x, int y);

	public String getName();

	public void setName(String name);

	public int getSize();

	public void setSize(int size);
}
