import ru.specialist.web.model.*;

import java.util.Collection;
import java.util.Scanner;

import javax.persistence.*;


public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Поиск курсов: ");
		String search = sc.nextLine().trim();
		
		
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("MyWebORM");
		
		EntityManager em = 
				emf.createEntityManager();
		
		// JPQL
		Query query = 
			em.createQuery("SELECT e FROM Course e WHERE e.title LIKE :search");
		
		query.setParameter("search", "%"+search+"%");
		
		
		Collection<Course> courses = (Collection<Course>)query.getResultList();
		
		for(Course course : courses) {
			System.out.printf("%-32s : %d\n", course.getTitle(), course.getLength());
			for(Teacher t : course.getTeachers())
				System.out.printf("\t%s\n", t.getName());
		}
		
		/*em.getTransaction().begin();
		Course c = new Course();
		c.setTitle("Spring Java");
		c.setLength(40);
		c.setDescription("Spring framework");
		em.persist(c);
		em.getTransaction().commit();*/
		
	}

}
