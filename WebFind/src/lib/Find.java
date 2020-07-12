package lib;
import javax.faces.bean.*;

@ManagedBean(name="findComp",eager=true)
@RequestScoped
public class Find {
   private String city;
   private String find;
   
   public String getCity(){return city;}
   public String getFind(){return find;}
   
   @ManagedProperty(value = "#{param.pageid}")
   private String pageid;
   public  String getPageid()          {return pageid;}
   public  void   setPageid(String id) {pageid=id;} 
   
   public String page(int n){
       String page="findCity";
       city="City";
       find="Find";
       
      if (n==2){
            city="Город";
            find="Поиск"; 
       }
       return page;
   }
   public String time() {
       return new java.util.Date().toString();
   }
}
