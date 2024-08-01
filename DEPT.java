package onetomany;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

@Getter
@Setter
@ToString

@Entity
public class DEPT {
	
	@Id
	private long DEPTNO;
	
	@NonNull
	@Column(name="DNAME", length = 20)
	private String DNAME;
	
	@NonNull
	@Column(name="LOC", length = 20)
	private String LOC;

}
