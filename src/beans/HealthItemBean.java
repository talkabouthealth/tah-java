package beans;

import java.util.Set;

/**
 * Bean for saving health items information - symptoms, treatments, etc.
 * Uses tree-like structure.
 *
 */
public class HealthItemBean implements Comparable<HealthItemBean> {
	
	private int id;
	private String name;
	private Set<HealthItemBean> children;
	
	public HealthItemBean(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public HealthItemBean(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<HealthItemBean> getChildren() {
		return children;
	}
	public void setChildren(Set<HealthItemBean> children) {
		this.children = children;
	}

	@Override
	public int compareTo(HealthItemBean o) {
		return name.compareTo(o.name);
	}

}
