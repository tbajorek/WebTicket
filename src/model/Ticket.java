package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;
	@NotNull
	private String title;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="department_id")
	private Department department;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="author_id")
	private User author;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="recipient_id")
	private User recipient;
	
	private Double rate;
	
	@NotNull
	private Date created;
	
	@NotNull
	private Date lastModified;
	
	private Date closed;
	
	private Boolean newChanges;
	
	@OneToMany(mappedBy = "ticket")
	private List<Message> messages;
	
	@OneToMany(mappedBy = "ticket")
	private List<Milestone> milestones;

}
