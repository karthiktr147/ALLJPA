package com.mindtree.watchstore.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

public class StoreDTO {

	private long id;

	@NotNull(message = "Store Name Cannot Be Empty")
	private String name;

	private List<WatchDTO> watches;

	public StoreDTO() {
	}

	public StoreDTO(String name, List<WatchDTO> watches) {
		this.name = name;
		this.watches = watches;
	}

	public StoreDTO(long id, String name, List<WatchDTO> watches) {
		this.id = id;
		this.name = name;
		this.watches = watches;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<WatchDTO> getWatches() {
		return watches;
	}

	public void setWatches(List<WatchDTO> watches) {
		this.watches = watches;
	}

}
