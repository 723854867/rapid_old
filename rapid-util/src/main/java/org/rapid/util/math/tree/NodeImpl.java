package org.rapid.util.math.tree;

public class NodeImpl<ID> implements Node<ID> {

	private static final long serialVersionUID = -2741265914116041003L;
	
	protected ID id;
	protected String name;
	protected int layer;
	protected ID parentId;

	@Override
	public ID getId() {
		return this.id;
	}
	
	public void setId(ID id) {
		this.id = id;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int getLayer() {
		return this.layer;
	}
	
	public void setLayer(int layer) {
		this.layer = layer;
	}

	@Override
	public ID getParentId() {
		return this.parentId;
	}
	
	public void setParentId(ID parentId) {
		this.parentId = parentId;
	}
}
