// Base Class Phone  -----------------------------
class Phone
{
   private  String areaCode;
   private  String number;
    
   public Phone()
   {
      setPhone();
   }
   public Phone(String ac, String num)
   {
      if ( !setPhone(ac, num) )
         setPhone();  // use defaults if bad values passed
   }
   public void setPhone()
   {
      setPhone("000", "0000000");
   }
   public boolean setPhone(String ac, String num)
   {
      if (ac.length() == 3 && isNumber(ac) 
            && num.length() == 7 && isNumber(num) )
         {
            number = num;
            areaCode = ac;
            return true;
         }
      return false;
   }
   public String getAreaCode()
   {
      return areaCode;
   }
   public String getNumber()
   {
      return number;
   }
   public String toString()
   {
      String result;
      result = "(" + areaCode + ")"
         + number.substring(0,3) + "-"
         + number.substring(3,7);
      return result;
  }
   public void showPhone()
   {
      System.out.println(toString());
   }      
   protected static boolean isNumber(String s)
   {
      for(int k = 0; k < s.length(); k++)
         if (!Character.isDigit(s.charAt(k)))
            return false;
      return true;
  }
}


//Derived class
class PhoneWX extends Phone 
{
   // additional member
   private String extension;
    
   public PhoneWX()
   {
      super();
      extension =  "00";
   }
   public PhoneWX(String ac, String num, String ext)
   {
      super(ac, num);
      
      // now the extension - let mutator do validation
      if ( !setExtension(ext) )
         extension = "00";
   }
   public boolean setPhoneWX(String ac, String num, String ext)
   {
      // first base class stuff
      if ( !setPhone(ac, num) )
         return false;  // don't change anything, return false

      // now the extension
      if ( !setExtension(ext) )
         return false;  // don't change anything, return false
      
      return true;

      // if ac/num were good, but ext was bad, we have a little weirdness -
      // ac/num get changed, but ext doesn't. I'll leave this weakness as
      // an exercise for the reader to fix.
   }
   public String getExtension()
   {
      return extension;
   }
   public boolean setExtension(String ext)
   {
      if (ext.length() == 2 && isNumber(ext) )
      {
         extension = ext;
         return true;
      }
      return false;
   }
   public String toString()
   {
      return "ext. " + extension;
   }
   public void showPhone()
   {
      System.out.println(super.toString()+ "  "  + toString() );
   }
}