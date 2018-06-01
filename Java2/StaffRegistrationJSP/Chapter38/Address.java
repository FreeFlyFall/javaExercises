package Chapter38;

//Modified address class
public class Address {    
  private String id;
  private String lastName;
  private String firstName;
  private String mi;
  private String location;
  private String city;
  private String state;
  private String telephone;
  private String email;

  public String getId() {
      return this.id;
  }
  
  public void setId(String id) {
      this.id = id;
  }
    
  public String getLocation() {
      return this.location;
  }
  
  public void setLocation(String location) {
      this.location = location;
  }
  
  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMi() {
    return this.mi;
  }

  public void setMi(String mi) {
    this.mi = mi;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getTelephone() {
    return this.telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
            
  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return this.state;
  }

  public void setState(String state) {
    this.state = state;
  }
}