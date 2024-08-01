# JPA 실습 🚀

JPA를 사용하여 두 개의 엔티티(`DEPT`와 `EMP`)를 정의하고, 이들 간의 관계를 설정한 후, 데이터를 조인하여 조회하는 방법 연습 

- **`DEPT` 엔티티** 🏢:
    - `DEPTNO`, `DNAME`, `LOC`
- **`EMP` 엔티티** 👤:
    - `EMPNO`, `ENAME`, `JOB`, `MGR`, `HIREDATE`, `SAL`, `COMM`, `DEPTNO`
- **실습 내용** 🔍:
    - **엔티티 관계**: `EMP` 엔티티는 `DEPT` 엔티티와 다대일(`ManyToOne`) 관계를 설정
    - **JPQL 쿼리**: `EMP`와 `DEPT` 엔티티를 조인하여 직원 이름, 직무, 부서 이름, 부서 위치를 조회하는 쿼리를 실행

DEPT.java

```java
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

```

EMP.java

```java
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

```

RunTest.java

```java
package onetomany;

import java.util.List;
import javax.persistence.EntityManager;
import org.junit.Test;
import util.DBUtil;

public class RunTest {
    
    @Test
    public void test() {
        EntityManager em = DBUtil.getEntityManager();
        
        try {

            List<Object[]> results = em.createQuery(
                "SELECT e.ENAME, e.JOB, d.DNAME, d.LOC FROM EMP e JOIN e.DEPTNO d", Object[].class)
                .getResultList();
            
            //results.stream().forEach(System.out::println); -> 주소값만 나옴
            
            results.stream().forEach(result -> System.out.println(
                    "EMP Name: " + result[0] + ", Job: " + result[1] +
                    ", Dept Name: " + result[2] + ", Location: " + result[3]
                ));
            
            
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            em.close();
        }
    }
}

```
