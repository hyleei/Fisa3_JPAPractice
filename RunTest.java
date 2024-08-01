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
