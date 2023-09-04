import java.io.Serializable;

public class Books implements Serializable {
    
public int id;
public String title ;
public String author ;


Books (int id , String title , String author) {

    this.id = id ;
    this. title = title ;
    this.author = author ;


}

public String toString() {

    return id+". "+title+", "+author ;
}


}
