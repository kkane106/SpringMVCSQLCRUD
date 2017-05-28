package com.skilldistillery.data;

import java.time.LocalDate;
import java.util.List;

public class Concert {
	private int id;
	private String performer;
	private String venue;
	private String date;
	private String imageUrl;
	private LocalDate formattedDate;
	private List<Performer> performerList;
	
	
	public Concert(int id, String performer, String venue, String date, String imageUrl) {
		super();
		this.id = id;
		this.performer = performer;
		this.venue = venue;
		this.date = date;
		this.imageUrl = imageUrl;
	}

	public List<Performer> getPerformerList() {
		return performerList;
	}

	public void setPerformerlist(List<Performer> performerlist) {
		this.performerList = performerlist;
	}
	
	public void addToPerformerList(Performer performer) {
		performerList.add(performer);
	}

	public Concert(int id, String performer, String venue, String date) {
		super();
		this.id = id;
		this.performer = performer;
		this.venue = venue;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPerformerList(List<Performer> performerList) {
		this.performerList = performerList;
	}

	public Concert(String performer, String venue, String date, String imageUrl) {
		super();
		this.performer = performer;
		this.venue = venue;
		this.date = date;
		this.imageUrl = imageUrl;
	}
	
	public Concert(String performer, String venue, String date, String imageUrl, LocalDate formattedDate) {
		super();
		this.performer = performer;
		this.venue = venue;
		this.date = date;
		this.imageUrl = imageUrl;
		this.setFormattedDate(formattedDate);
	}
	
	public Concert(String performer, String venue, String date) {
		super();
		this.performer = performer;
		this.venue = venue;
		this.date = date;
	}

	public Concert() {
		// TODO Auto-generated constructor stub
	}

	public String getPerformer() {
		return performer;
	}

	public void setPerformer(String performer) {
		this.performer = performer;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "Concert [id=" + id + ", performer=" + performer + ", venue=" + venue + ", date=" + date + ", imageUrl="
				+ imageUrl + "]";
	}

	public LocalDate getFormattedDate() {
		return formattedDate;
	}

	public void setFormattedDate(LocalDate formattedDate) {
		this.formattedDate = formattedDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((performer == null) ? 0 : performer.hashCode());
		result = prime * result + ((venue == null) ? 0 : venue.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Concert other = (Concert) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (performer == null) {
			if (other.performer != null)
				return false;
		} else if (!performer.equals(other.performer))
			return false;
		if (venue == null) {
			if (other.venue != null)
				return false;
		} else if (!venue.equals(other.venue))
			return false;
		return true;
	}
	
	
	
}
