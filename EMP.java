package onetomany;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

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
public class EMP {
	
	@Id
	private long EMPNO;
	
	@NonNull
	@Column(length = 20) 
	private String ENAME;
	
	@NonNull
	@Column(length = 20) 
	private String JOB;
	
	@NonNull
	@Column(nullable = true) 
	private long MGR;
	
	@NonNull
	private String HIREDATE;
	
	@NonNull
	private long SAL;
	
	@NonNull
	@Column(nullable = true) 
	private long COMM;

	@NonNull
	@ManyToOne(fetch = FetchType.LAZY) // Member 하나는 Team 하나에 소속
	@JoinColumn(name="DEPTNO")  
	private DEPT DEPTNO;

}
