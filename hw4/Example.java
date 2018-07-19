public class Example
{
   public static void main (String[] args)
   {
      Phone me = new Phone("800", "1234567"), you = new Phone();
      
      you.setPhone("415", "5551234");
      me.showPhone();
      you.showPhone();
      
      PhoneWX test = new PhoneWX("808", "3886699", "77");
      test.showPhone();
      
      PhoneWX her = new PhoneWX();
      
      her.setPhoneWX("415", "5551234", "99");
      her.showPhone();
    
      System.out.println( "Separately: \n" 
         + her.getAreaCode() + "  "
         + her.getNumber() + "  "
         + her.getExtension() );
         
      //seeing how the derived class object uses a base function
      //her.setPhoneWX
      her.setPhone(); //should now be 000 0000000 99
      her.showPhone();
         

   }
 }



